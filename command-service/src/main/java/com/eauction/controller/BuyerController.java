package com.eauction.controller;

import com.eauction.dto.BidProduct;
import com.eauction.dto.Info;
import com.eauction.exception.ProductCategoryException;
import com.eauction.exception.ResourceNotFoundException;
import com.eauction.model.Buyer;
import com.eauction.model.BuyerProduct;
import com.eauction.model.Product;
import com.eauction.repository.BuyerProductRepository;
import com.eauction.repository.BuyerRepository;
import com.eauction.repository.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;


@RestController
public class BuyerController {

    @Autowired
    private BuyerRepository buyerRepo;
    @Autowired
    private ProductRepository productRepo;
    @Autowired
    private BuyerProductRepository buyerProductRepo;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;

    @PostMapping("/eauction/v1/buyer/place-bid")
    public ResponseEntity<?> placeBid(@RequestBody Buyer buy) throws JsonProcessingException {
        buy.getBuyers().stream()
                .forEach(p -> {
                    if (!productRepo.existsById(p.getProductId())) {
                       throw new ProductCategoryException("Invalid Product id :"+p.getProductId());
                    }
                    Product product=productRepo.findById(p.getProductId()).get();
                    LocalDate today = LocalDate.now();
                    if (product.getBidEndDate().isBefore(today)) {
                        throw new ProductCategoryException("Product can not be bid bcoz bid End dated");
                    }
                    BuyerProduct bp=buyerProductRepo.findBid(p.getProductId(),buy.getId());
                    if(bp!=null){
                        throw new ProductCategoryException("Product already bid by you");
                    }
                });
        Buyer buyer = buyerRepo.save(buy);
        for (BuyerProduct p : buyer.getBuyers()) {
            BidProduct bp = new BidProduct();
            bp.setBidAmount(p.getBidAmount());
            OBJECT_MAPPER.registerModule(new JavaTimeModule());
            OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            Product pp = productRepo.findById(p.getProductId()).get();
            String product = OBJECT_MAPPER.writeValueAsString(pp);
            com.eauction.dto.Product finalP = OBJECT_MAPPER.readValue(product, com.eauction.dto.Product.class);
            String buyerStr = OBJECT_MAPPER.writeValueAsString(buy);
            com.eauction.dto.Buyer finalB = OBJECT_MAPPER.readValue(buyerStr, com.eauction.dto.Buyer.class);
            bp.setProduct(finalP);
            bp.setBuyer(finalB);
            String buyerStr12 = OBJECT_MAPPER.writeValueAsString(bp);
            raiseEvent(buyerStr12,finalB.getId());
        }
        return new ResponseEntity<>(buyer, HttpStatus.OK);
    }

    @PutMapping ("/eauction/v1/buyer/update-bid/{productId}/{emailId}/{bidAmt}")
    public ResponseEntity<?> updateBid(@PathVariable long productId,@PathVariable String emailId,@PathVariable double bidAmt) throws JsonProcessingException {
        Buyer buyer = buyerRepo.getByEmail(emailId);
        if(buyer==null){
            throw new ResourceNotFoundException("Buyer Not Found");
        }
        if (!productRepo.existsById(productId)) {
            throw new ProductCategoryException("Invalid Product id :"+productId);
        }
        BuyerProduct bp=  buyerProductRepo.findBid(productId,buyer.getId());
        bp.setBidAmount(bidAmt);
        buyerProductRepo.save(bp);
        Info in=new Info(emailId,bidAmt,productId);
        String updateTxt = OBJECT_MAPPER.writeValueAsString(in);
        updateEvent(updateTxt,emailId);
        return new ResponseEntity<>(bp, HttpStatus.OK);

    }
    private void raiseEvent(String bidPrd,long buyerId){
        try{
            kafkaTemplate.send("bidProduct", bidPrd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void updateEvent(String bidPrd,String email){
        try{
            kafkaTemplate.send("bidProductUpdate", bidPrd);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
