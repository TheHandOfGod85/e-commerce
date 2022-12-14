package com.delpiano.ecommerce.dao;

import com.delpiano.ecommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product,Long> {
  // method to find the category by id
  Page<Product> findByCategoryId(@Param("id") Long id, Pageable pageable);
  // method to find the product by name
  Page<Product> findByNameContaining(@Param("name") String name, Pageable page);

}
