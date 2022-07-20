package com.eauctionquery.controller;

import com.eauctionquery.model.dto.BidProduct;
import com.eauctionquery.model.dto.Product;
import com.eauctionquery.repository.BuyerProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QueryController {
    @Autowired
    private BuyerProductRepository repo;

    @Autowired
    MongoTemplate mongoTemplate;

    @GetMapping("/e-auction/v1/seller/show-bids/{productId}")
    public ResponseEntity<?> getProdcutsBid(@PathVariable long productId) throws JsonProcessingException {
      List<BidProduct> allProduct = findProductById(productId);
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @GetMapping("/e-auction/v1/seller/show-products")
    public ResponseEntity<?> getAllProducts() throws JsonProcessingException {
        return new ResponseEntity<>(getallPrd(), HttpStatus.OK);
    }

    public List<BidProduct> findProductById(long id) {
        Query query = new Query(Criteria.where("product.id").is(id));
        query.with(Sort.by(Sort.Direction.DESC,"bidAmount"));
        return mongoTemplate.find(query, BidProduct.class);
    }
    public List<Product> getallPrd(){
        List<Object> object = mongoTemplate.query(BidProduct.class).distinct("product").all();
        List<Product> plist=new ArrayList<>();
        for (Object object2 : object) {
            Product p = (Product) object2;
            System.out.println(p.toString());
            plist.add(p);
        }
        return plist;
    }

}
