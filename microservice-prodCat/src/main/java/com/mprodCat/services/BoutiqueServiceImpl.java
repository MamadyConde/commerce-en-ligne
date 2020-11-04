package com.mprodCat.services;

import com.mprodCat.configuration.ApplicationPropertiesConfiguration;
import com.mprodCat.dao.CategoryDao;
import com.mprodCat.dao.ProductDao;
import com.mprodCat.entity.Category;
import com.mprodCat.entity.Product;
import com.mprodCat.exceptions.CategoryNotFoundException;
import com.mprodCat.exceptions.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoutiqueServiceImpl implements IboutiqueService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ApplicationPropertiesConfiguration appConfiguration;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean addProduit(Product product) {
        Product productExist = productDao.findBydesignationAndCategory(product.getDesignation(),product.getCategory());
        if (productExist != null) throw new ProductNotFoundException("Ce produit avec l'Id "+productExist.getId()+" existe dejà");
        productDao.save(product);
        return true;
    }

    @Override
    public void updateProduct(int id, Product product) {
        Optional<Product> productGet = productDao.findById(id);
        if (!productGet.isPresent()) throw new ProductNotFoundException("le produit avec l'id "+id+" n'existe pas");
        productGet.get().setDesignation(product.getDesignation());
        productGet.get().setDescription(product.getDescription());
        productGet.get().setPrix(product.getPrix());
        productGet.get().setQuantity(product.getQuantity());
        productGet.get().setPhoto(product.getPhoto());
        productDao.save(productGet.get());
    }

    @Override
    public void deleteProduct(int id) {
        Optional<Product> product = productDao.findById(id);
        if (!product.isPresent()) throw new ProductNotFoundException("le produit avec l'id "+id+" n'existe pas");
        productDao.delete(product.get());
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> listProducts = productDao.findAll();
        if (listProducts.isEmpty()) throw new ProductNotFoundException("Il n'existe aucun produit");
        log.info("Recupérer la liste des produits");
        return listProducts.subList(0, appConfiguration.getLimitProducts());
    }

    @Override
    public Product getOneProduct(int id) {
        Optional<Product> productGet = productDao.findById(id);
        if (!productGet.isPresent()) throw new ProductNotFoundException("le produit avec l'id "+id+" n'existe pas");
        return productGet.get();
    }

    @Override
    public List<Product> productByCategory(Category category) {
        List<Product> listProductByCategory = productDao.findBycategory(category);
        if (listProductByCategory.isEmpty()) throw new ProductNotFoundException("Il n'existe un produit " +
                "de la categorie d'id "+category.getId());
        return listProductByCategory;
    }


    @Override
    public boolean addCategory(Category category) {
        Category categoryExist = categoryDao.findByname(category.getName());
        if (categoryExist != null) throw new CategoryNotFoundException("La categorie avec le nom "+ category.getName()+ "existe déjà");
        categoryDao.save(category);
        return true;
    }

    @Override
    public void updateCategory(int id, Category category) {
        Optional<Category> categoryGet = categoryDao.findById(id);
        if (!categoryGet.isPresent()) throw new CategoryNotFoundException("la categorie avec l'id "+id+" n'existe pas");
        categoryGet.get().setDescription(category.getDescription());
        categoryGet.get().setName(category.getName());
        categoryGet.get().setProductsList(category.getProductsList());
        categoryDao.save(categoryGet.get());
    }

    @Override
    public void deleteCategory(int id) {
        Optional<Category> categoryGet = categoryDao.findById(id);
        if (!categoryGet.isPresent()) throw new ProductNotFoundException("la categorie avec l'id "+id+" n'existe pas");
        categoryDao.delete(categoryGet.get());
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> listCategories = categoryDao.findAll();
        if (listCategories.isEmpty()) throw new CategoryNotFoundException("Il n'existe aucune categorie");
        return listCategories;
    }

    @Override
    public Category getOneCategory(int id) {
        Optional<Category> categoryGet = categoryDao.findById(id);
        if (!categoryGet.isPresent()) throw new CategoryNotFoundException("la categorie avec l'id "+id+" n'existe pas");
        return categoryGet.get();
    }
}
