package com.eauction.repository;

import com.eauction.model.Product;
import com.eauction.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
   // Long deleteByStudyId(long studyId);
}
