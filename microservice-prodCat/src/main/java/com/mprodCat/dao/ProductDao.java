package com.mprodCat.dao;

import com.mprodCat.entity.Category;
import com.mprodCat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

   public List<Product> findBycategory(Category IdCategory);
   public Product findBydesignationAndCategory(String designation, Category category);
}
