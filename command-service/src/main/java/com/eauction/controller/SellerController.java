package com.eauction.controller;

import com.eauction.exception.ProductCategoryException;
import com.eauction.exception.ResourceNotFoundException;
import com.eauction.model.Product;
import com.eauction.model.Seller;
import com.eauction.repository.BuyerProductRepository;
import com.eauction.repository.ProductRepository;
import com.eauction.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@RestController
public class SellerController {
    @Autowired
    private SellerRepository repo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private BuyerProductRepository bidRepo;
    @PostMapping("/e-auction/v1/seller/add-product")
    public ResponseEntity<?> addProduct(@Valid @RequestBody Seller seller) {
        Set<String> category=new HashSet<String>();
        category.add("Painting");
        category.add("Ornament");
        category.add("Sculptor");
        if (seller.getProduct().stream().anyMatch(p -> !category.contains(p.getPrdCategory()))) {
            throw new ProductCategoryException("Invalid Product Category");
        }
        Seller s = repo.save(seller);
       return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @DeleteMapping("/e-auction/v1/seller/{id}")
    public void deleteProduct(@PathVariable long id) {
       if(!productRepo.existsById(id)) {
            throw new ResourceNotFoundException("id " + id + " not found");
        }
       if( bidRepo.findByProductId(id).size()<=0){
           Product p=productRepo.findById(id).get();
           LocalDate today = LocalDate.now();
           if (!p.getBidEndDate().isBefore(today)) {
               productRepo.deleteById(id);
           }else{
               throw new ProductCategoryException("Product can not be deleted bcoz bid End dated");
           }
       }else{
           throw new ProductCategoryException("Product can not be deleted");
       }
    }
}
