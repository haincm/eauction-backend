package com.eauction.repository;

import com.eauction.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long>{
   // Long deleteByStudyId(long studyId);
}
