package com.example.FlipCommerce.service.serviceImpl;

import com.example.FlipCommerce.Enum.ProductStatus;
import com.example.FlipCommerce.Exception.CustomerNotFoundException;
import com.example.FlipCommerce.Exception.InsufficientQuantityException;
import com.example.FlipCommerce.Exception.InvalidCardException;
import com.example.FlipCommerce.Exception.ProductNotFoundException;
import com.example.FlipCommerce.dtos.RequestDTO.OrderRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.OrderResponseDto;
import com.example.FlipCommerce.model.*;
import com.example.FlipCommerce.repository.*;
import com.example.FlipCommerce.service.OrderService;
import com.example.FlipCommerce.transformer.ItemTransformer;
import com.example.FlipCommerce.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {

        Customer customer = customerRepository.findByEmailId(orderRequestDto.getEmailId());
        if (customer == null){
            throw new CustomerNotFoundException("Customer Does not exist");
        }


        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());

        if (optionalProduct.isEmpty()){
            throw  new ProductNotFoundException("Sorry! Product does not exist");
        }

        Product product = optionalProduct.get();
        //check quantity

        if (product.getQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Required quantity is not available");
        }
//card check
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date date = new Date();
        if (card == null || card.getCvv() != orderRequestDto.getCvv()){// change the after to before here  || date.before(card.getValidTill())
            throw new InvalidCardException("Card is expired");
        }

        int newQuantity = product.getQuantity()-orderRequestDto.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if (newQuantity == 0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        //Create the item

        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);

        // create order
        OrderEntity orderEntity = OrderTransformer.OrderRequestDtoToOrder(item,customer);
        String maskedCard = generateMaskedCardNO(card);
        orderEntity.setCardUsed(maskedCard);
        orderEntity.getItems().add(item);

        item.setOrderEntity(orderEntity);
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        product.getItems().add(savedOrder.getItems().get(0));



        //send email
        String text = "Congrats !"+ customer.getName()+ " You have Book " + product.getName() +" with Order number "+ orderEntity.getOrderNo() +". Your order will arrive soon";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("akashlkog22@gmail.com");
        message.setTo(customer.getEmailId());
        message.setSubject("Order Placed !!!!");
        message.setText(text);
        emailSender.send(message);




        return OrderTransformer.OrderToOrderResponseDto(orderEntity);
    }

    private String generateMaskedCardNO(Card card){
        String cardNo = "";
        String originalCardNo = card.getCardNo();
        for(int i = 0 ; i < card.getCardNo().length() ; i++){
            cardNo += "*";
        }
        cardNo += originalCardNo.substring(originalCardNo.length()-4);
        return cardNo;
    }

    public OrderEntity placeOrder(Cart cart , Card card){

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(generateMaskedCardNO(card));


        int totalValue = 0;
        for(Item item : cart.getItems()){
            Product product = item.getProduct();
            if (item.getRequiredQuantity() > product.getQuantity()){
                throw new InsufficientQuantityException("Required quantity not present!!");
            }
            totalValue += item.getRequiredQuantity()*product.getPrice();
            int newQuantity = product.getQuantity() -item.getRequiredQuantity();;
            product.setQuantity(newQuantity);
            if (newQuantity == 0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }
            item.setOrderEntity(orderEntity);
        }
        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;
    }



    @Override
    public List<OrderResponseDto> getTopKOrdersMaxOrderValue(int k) {
//        int max = orderRepository.getMaxOrderValue();

        List<OrderEntity> orderEntityList = orderRepository.getAllOrderDescendingByOrderValue();
        int size = orderEntityList.size();
        if(size > k){
            for (int i = k ; i < size ; i++){
                orderEntityList.remove(k);// nice concept here
            }
        }
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (OrderEntity order : orderEntityList){
            orderResponseDtoList.add(OrderTransformer.OrderToOrderResponseDto(order));
        }
        return orderResponseDtoList;
    }

    @Override
    public List<OrderResponseDto> getOrderOfACustomer(String emailId) {
        Customer customer = customerRepository.findByEmailId(emailId);
        if (customer == null){
            throw new CustomerNotFoundException("Customer does not exist");
        }
        List<OrderEntity> orderEntityList = customer.getOrders();

        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (OrderEntity order : orderEntityList){
            orderResponseDtoList.add(OrderTransformer.OrderToOrderResponseDto(order));
        }
        return orderResponseDtoList;
    }

    @Override
    public List<OrderResponseDto> getTopKOrderOfACustomer(String emailId, int k) {

        Customer customer = customerRepository.findByEmailId(emailId);
        if (customer == null){
            throw new CustomerNotFoundException("Customer does not exist");
        }
        List<OrderEntity> orderEntityList = customer.getOrders();

        Collections.sort(orderEntityList,(x,y) -> y.getTotalValue() - x.getTotalValue());
        int size = orderEntityList.size();
        if(size > k){
            for (int i = k ; i < size ; i++){
                orderEntityList.remove(k);// notice it too
            }
        }
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (OrderEntity order : orderEntityList){
            orderResponseDtoList.add(OrderTransformer.OrderToOrderResponseDto(order));
        }

        return orderResponseDtoList;
    }

    @Override
    public List<OrderResponseDto> getKRecentlyOrderCustomer(String emailId, int k) {

        Customer customer = customerRepository.findByEmailId(emailId);
        if (customer == null){
            throw new CustomerNotFoundException("Customer does not exist");
        }
        List<OrderEntity> orderEntityList = customer.getOrders();

       Collections.reverse(orderEntityList);

        int size = orderEntityList.size();
        if(size > k){
            for (int i = k ; i < size ; i++){
                orderEntityList.remove(k);// notice it too
            }
        }
        List<OrderResponseDto> orderResponseDtoList = new ArrayList<>();
        for (OrderEntity order : orderEntityList){
            orderResponseDtoList.add(OrderTransformer.OrderToOrderResponseDto(order));
        }

        return orderResponseDtoList;
    }

}
