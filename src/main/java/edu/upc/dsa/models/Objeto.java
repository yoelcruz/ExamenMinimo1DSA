package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Objeto {
    private String name;
    int cantidad;

    public Objeto(String name, int cantidad) {

        this.name = name;
        this.cantidad =cantidad;
    }
    public Objeto(){
        this.name = RandomUtils.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}