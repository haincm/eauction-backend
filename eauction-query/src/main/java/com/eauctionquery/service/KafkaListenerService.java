package com.eauctionquery.service;

import com.eauctionquery.model.dto.BidProduct;
import com.eauctionquery.model.dto.Info;
import com.eauctionquery.repository.BuyerProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaListenerService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    @Autowired
    private BuyerProductRepository repo;

    @KafkaListener(topics = "bidProduct",groupId = "group_id")
    public void consume(String productStr) {
        try{
            OBJECT_MAPPER.registerModule(new JavaTimeModule());
            OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            BidProduct product = OBJECT_MAPPER.readValue(productStr, BidProduct.class);
            repo.save(product);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "bidProductUpdate",groupId = "group_id")
    public void consumeUpdate(String updateTxt) {
        try{
            Info info = OBJECT_MAPPER.readValue(updateTxt, Info.class);
            BidProduct bp=repo.findByEmailAndProductId(info.getEmail(),info.getProductId());
            OBJECT_MAPPER.registerModule(new JavaTimeModule());
            OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            bp.setBidAmount(info.getBidAmount());
            repo.save(bp);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
