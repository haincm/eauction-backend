package com.eauction.repository;


import com.eauction.model.BuyerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerProductRepository extends JpaRepository<BuyerProduct, Long> {
    //Buyer getByEmail(String email);
    @Query(value="SELECT * FROM  buyer_product WHERE product_id = ?1 AND buyer_id = ?2", nativeQuery = true)
    BuyerProduct findBid(long productId,long buyerId);
    List<BuyerProduct> findByProductId(long id);
    @Query(value="SELECT * FROM  buyer_product WHERE product_id = ?1 AND buyer_id = ?2", nativeQuery = true)
    List<BuyerProduct> findByProductIdAndBuyerId(long productId,long buyerId);
}
