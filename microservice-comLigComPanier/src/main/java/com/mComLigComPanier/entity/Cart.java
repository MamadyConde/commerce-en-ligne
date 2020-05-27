package com.mComLigComPanier.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToMany(mappedBy = "cart")
    //@JsonBackReference
    private List<CartLine> listCartLine = new ArrayList<>();
    private int idUser;


    public Cart() {
    }

    public Cart(int id) {
        this.id = id;
    }

    public Cart(List<CartLine> listCartLine, int idUser) {
        this.listCartLine = listCartLine;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartLine> getListCartLine() {
        return listCartLine;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setListCardLine(List<CartLine> listCardLine) {
        this.listCartLine = listCardLine;
    }
    public int getQuantityTotal(){
        int quantity = 0;
        for (CartLine line : this.listCartLine){
            quantity+=line.getQuantity();
        }
            return quantity;
    }

    public double getAmountTotal() {
        double total = 0;
        for (CartLine line : this.listCartLine) {
            total += line.getAmount()*line.getQuantity();
        }
        return total;
    }
}
