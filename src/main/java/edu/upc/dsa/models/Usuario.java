package edu.upc.dsa.models;

import edu.upc.dsa.util.RandomUtils;

import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private String id;
    private String nombre;
    private String apellidos;

    private List<Objeto> objetos = new LinkedList<Objeto>();

    public Usuario(String id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Usuario(){
        this.id= RandomUtils.getId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void addObjeto(String name, int cantidad){
        Objeto objeto = new Objeto(name, cantidad);
        this.objetos.add(objeto);
    }

    public List<Objeto> getObjetos() {
        return objetos;
    }

    public int size() {
        int ret = this.objetos.size();
        return ret;
    }


    public int numeroObjetos(){
        int numObjetos=0;
        for (Objeto objeto: objetos) {
            numObjetos = numObjetos + objeto.getCantidad();
        }
        return numObjetos;
    }

    @Override
    public String toString() {
        return "Usuario [id="+id+", nombre=" + nombre + ", apellidos=" + apellidos +"]";
    }
}

