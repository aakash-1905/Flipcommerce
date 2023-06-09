package com.example.FlipCommerce.service.serviceImpl;

import com.example.FlipCommerce.dtos.RequestDTO.CustomerRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.CustomerResponseDto;
import com.example.FlipCommerce.model.Card;
import com.example.FlipCommerce.model.Cart;
import com.example.FlipCommerce.model.Customer;
import com.example.FlipCommerce.repository.CustomerRepository;
import com.example.FlipCommerce.service.CustomerService;
import com.example.FlipCommerce.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        //dto to entity

        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        //from cart transformer
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        customerRepository.save(customer);//save both parent and child  i.e. customer and cart

        //prepare response dto

        CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);

        return customerResponseDto;
    }

    @Override
    public List<CustomerResponseDto> customerKOrders(int k) {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
        for (Customer customer : customers){
            if (customer.getOrders().size() == k){
                customerResponseDtoList.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
            }
        }
        return customerResponseDtoList;
    }

//    @Override
//    public List<CustomerResponseDto> getFemaleCustomerAgeBetween(int fromAge, int toAge) {
//        List<Customer> customers = customerRepository.getFemaleCustomerAgeBetween(fromAge, toAge);
//        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();
//        for (Customer customer : customers){
//            customerResponseDtoList.add(CustomerTransformer.CustomerToCustomerResponseDto(customer));
//        }
//        return customerResponseDtoList;
//    }
}
