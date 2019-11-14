package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class Product {
    private String id;
    private int amount;
    private double price;

    public Product(String id, int amount, double price) {
        this.id = id;
        this.amount = amount;
        this.price = price;
    }
    public Product(){
        this.id = RandomUtils.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    @Override
    public String toString() {
        return "Product [id="+id+", amount=" + amount + ", price=" + price +"]";
    }
}
