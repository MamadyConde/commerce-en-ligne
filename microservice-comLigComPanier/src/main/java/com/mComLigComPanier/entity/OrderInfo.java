package com.mComLigComPanier.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
public class OrderInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,
            generator="native")
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    private int id;
    private Date dateOrder;
    private double amount;
    @OneToMany(mappedBy = "order")
    //@JsonBackReference
    private List<OrderLine> listOrderLine = new ArrayList<>();
    private int idUser;


    public OrderInfo() {
    }

    public OrderInfo(int id) {
        this.id = id;
    }

    public OrderInfo(Date dateOrder, List<OrderLine> listOrderLine, double amount, int idUser) {
        this.dateOrder = dateOrder;
        this.listOrderLine = listOrderLine;
        this.amount = amount;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public List<OrderLine> getListOrderLine() {
        return listOrderLine;
    }

    public void setListOrderLine(List<OrderLine> listOrderLine) {
        this.listOrderLine = listOrderLine;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateOrder=" + dateOrder +
                '}';
    }
}
