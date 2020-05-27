package com.mprodCat.services;

import com.mprodCat.entity.Category;
import com.mprodCat.entity.Product;

import java.util.List;

public interface IboutiqueService {
    boolean addProduit(Product product);
    void updateProduct(int id, Product product);
    void deleteProduct(int id);
    List<Product> getAllProducts();
    Product getOneProduct(int id);
    List<Product> productByCategory(Category idCategory);

    boolean addCategory(Category category);
    void updateCategory(int id, Category category);
    void deleteCategory(int id);
    List<Category> getAllCategories();
    Category getOneCategory(int id);
}
