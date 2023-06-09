package com.example.FlipCommerce.service.serviceImpl;

import com.example.FlipCommerce.Exception.CustomerNotFoundException;
import com.example.FlipCommerce.Exception.InsufficientQuantityException;
import com.example.FlipCommerce.Exception.ProductNotFoundException;
import com.example.FlipCommerce.Exception.ProductOutOfStockException;
import com.example.FlipCommerce.dtos.RequestDTO.ItemRequestDto;
import com.example.FlipCommerce.model.Customer;
import com.example.FlipCommerce.model.Item;
import com.example.FlipCommerce.model.Product;
import com.example.FlipCommerce.repository.CustomerRepository;
import com.example.FlipCommerce.repository.ProductRepository;
import com.example.FlipCommerce.service.ItemService;
import com.example.FlipCommerce.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Item createItem(ItemRequestDto itemRequestDto) {

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product does not exist");
        }


        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());

        if(customer == null){
            throw new CustomerNotFoundException("Customer does not exist");
        }

        Product product = productOptional.get();

        if(product.getQuantity() == 0){
            throw  new ProductOutOfStockException("Product is out of stock bro!");
        }

        if(product.getQuantity()< itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not available");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());

//        product.getItems().add(item);

        return item;

    }
}
