package com.eauction.repository;

import com.eauction.model.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long>{
    Buyer getByEmail(String email);
}
