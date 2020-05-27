package com.mprodCat.dao;

import com.mprodCat.entity.Category;
import com.mprodCat.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {
    Category findByname(String name);
}
