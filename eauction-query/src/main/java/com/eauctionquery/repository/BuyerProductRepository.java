package com.eauctionquery.repository;


import com.eauctionquery.model.dto.BidProduct;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerProductRepository extends MongoRepository<BidProduct, Long> {

    @Query("{ 'buyer.email': ?0 , 'product._id':?1}")
    BidProduct findByEmailAndProductId(String email,long productId);
}
