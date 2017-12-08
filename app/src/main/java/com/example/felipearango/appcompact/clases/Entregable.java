package com.example.felipearango.appcompact.clases;

/**
 * Created by Sebastian Luna R on 11/30/2017.
 */

public class Entregable {

    /////////////////////////
    //Variables
    ///////////////////////

    private String id;
    private String fecha;
    private String tipoEntrega;
    private int numeroEntrega;


    /////////////////////////
    //Constructor
    ///////////////////////

    public Entregable(String id, String fecha, String tipoEntrega, int numeroEntrega) {
        this.id = id;
        this.fecha = fecha;
        this.tipoEntrega = tipoEntrega;
        this.numeroEntrega = numeroEntrega;
    }

    public Entregable(){}

    /////////////////////////
    //Getters and Setters
    ///////////////////////

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public int getNumeroEntrega() {
        return numeroEntrega;
    }

    public void setNumeroEntrega(int numeroEntrega) {
        this.numeroEntrega = numeroEntrega;
    }
}
