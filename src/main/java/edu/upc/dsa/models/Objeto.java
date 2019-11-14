package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

public class Objeto {
    private String name;

    public Objeto(String name) {
        this.name = name;
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
}
