package com.mprodCat.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    ) // pour permettre que la clé primaire de commencer à 1 avec Hibernate 5
    private int id;
    private String designation;
    private String description;
    private Double prix;
    private int quantity;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "IdCategory")
    //@JsonManagedReference // evite de faire la boucle utilisée pour parent
    private Category category;

    public Product() {
    }

    public Product(String designation, String description, Double prix, int quantity, String photo, Category category) {
        this.designation = designation;
        this.description = description;
        this.prix = prix;
        this.quantity = quantity;
        this.photo = photo;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                ", prix=" + prix +
                ", quantity=" + quantity +
                ", photo='" + photo + '\'' +
                '}';
    }
}
