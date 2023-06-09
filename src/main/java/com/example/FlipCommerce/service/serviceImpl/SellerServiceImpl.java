package com.example.FlipCommerce.service.serviceImpl;

import com.example.FlipCommerce.Enum.Category;
import com.example.FlipCommerce.Exception.SellerNotFoundException;
import com.example.FlipCommerce.dtos.RequestDTO.SellerRequestDto;
import com.example.FlipCommerce.dtos.ResponseDTO.ProductResponseDto;
import com.example.FlipCommerce.dtos.ResponseDTO.SellerResponseDto;
import com.example.FlipCommerce.model.Product;
import com.example.FlipCommerce.model.Seller;
import com.example.FlipCommerce.repository.SellerRepository;
import com.example.FlipCommerce.service.SellerService;
import com.example.FlipCommerce.transformer.ProductTransformer;
import com.example.FlipCommerce.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl  implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto){
        //request dto to entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        //saved the seller
        Seller savedSeller = sellerRepository.save(seller);

        //seller to seller response dto
        SellerResponseDto sellerResponseDto = SellerTransformer.SellerToSellerResponseDto(savedSeller);

        return sellerResponseDto;
    }

    @Override
    public String updateMobNo(String emailId, String newMobNo) {

        Seller seller = sellerRepository.findByEmailId(emailId);
        if (seller== null){
            throw new SellerNotFoundException("Seller does not exist");
        }
        seller.setMobNo(newMobNo);
        return "Mobile number is updated successfully";
    }

    @Override
    public List<SellerResponseDto> getByParticularCategory(Category category) {

        List<Seller> sellerList = sellerRepository.findAll();
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for (Seller seller : sellerList){
            List<Product> productList = seller.getProducts();
            for (Product product : productList){
                if (product.getCategory().equals(category)){
                    sellerResponseDtoList.add(SellerTransformer.SellerToSellerResponseDto(seller));
                    break;
                }
            }
        }

        return sellerResponseDtoList;
    }

    @Override
    public List<ProductResponseDto> getAllProducts(String emailId) {

        Seller seller = sellerRepository.findByEmailId(emailId);
        if (seller == null){
            throw new SellerNotFoundException("Seller does not exist");
        }
        List<ProductResponseDto> productResponseDtoList = new ArrayList<>();
        for (Product product : seller.getProducts()){
            productResponseDtoList.add(ProductTransformer.ProductToProductResponseDto(product));
        }
        return productResponseDtoList;
    }

    @Override
    public List<SellerResponseDto> getSellersWithHighestNumberOfProducts() {
        List<Seller> sellerList = sellerRepository.findAll();
        int max = -1;
        for (Seller seller : sellerList){
            int size  = seller.getProducts().size();
            if(size > max){
                max = size;
            }
        }
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for (Seller seller : sellerList){
            if(seller.getProducts().size() == max){
                sellerResponseDtoList.add(SellerTransformer.SellerToSellerResponseDto(seller));
            }
        }
       return sellerResponseDtoList;
    }

    @Override
    public List<SellerResponseDto> getSellersWithLowestNumberOfProducts() {
        List<Seller> sellerList = sellerRepository.findAll();
        int min = Integer.MAX_VALUE;
        for (Seller seller : sellerList){
            int size  = seller.getProducts().size();
            if(size < min){
                min = size;
            }
        }
        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for (Seller seller : sellerList){
            if(seller.getProducts().size() == min){
                sellerResponseDtoList.add(SellerTransformer.SellerToSellerResponseDto(seller));
            }
        }
        return sellerResponseDtoList;
    }

    @Override
    public List<SellerResponseDto> getSellersWithCostliestProducts() {
        List<Seller> sellerList = sellerRepository.findAll();
        int maxPrice = 0;
        for (Seller seller : sellerList){
            List<Product> productList = seller.getProducts();
            for (Product product : productList){
                if (maxPrice < product.getPrice()){
                    maxPrice = product.getPrice();
                }
            }
        }

        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for (Seller seller : sellerList){
            List<Product> productList = seller.getProducts();
            for (Product product : productList){
                if (product.getPrice() == maxPrice){
                    sellerResponseDtoList.add(SellerTransformer.SellerToSellerResponseDto(seller));
                    break;
                }
            }
        }

        return sellerResponseDtoList;
    }

    @Override
    public List<SellerResponseDto> getSellersWithCheapestProducts() {
        List<Seller> sellerList = sellerRepository.findAll();
        int minPrice = Integer.MAX_VALUE;
        for (Seller seller : sellerList){
            List<Product> productList = seller.getProducts();
            for (Product product : productList){
                if (minPrice > product.getPrice()){
                    minPrice = product.getPrice();
                }
            }
        }
      //  System.out.println(minPrice);

        List<SellerResponseDto> sellerResponseDtoList = new ArrayList<>();
        for (Seller seller : sellerList){
            List<Product> productList = seller.getProducts();
            for (Product product : productList){
                if (product.getPrice() == minPrice){
                    sellerResponseDtoList.add(SellerTransformer.SellerToSellerResponseDto(seller));
                    break;
                }
            }
        }

        return sellerResponseDtoList;
    }
}
