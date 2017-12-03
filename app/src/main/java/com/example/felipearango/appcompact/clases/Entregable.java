package com.example.felipearango.appcompact.clases;

/**
 * Created by Sebastian Luna R on 11/30/2017.
 */

public class Entregable {

    /**
     * Fecha máxima del entregable
     */
    private String fecha;

    /**
     * Tipo de entregable
     */
    private String tipo;

    ////////////////////////////////////////
    /////Constructor
    ////////////////////////////////////////

    public Entregable(String fecha, String tipo) {
        this.fecha = fecha;
        this.tipo = tipo;
    }

    public String toString(){
        return "fecha: " + getFecha() + "tipo entrga: " + getTipo();
    }


    ////////////////////////////////
    ///////Getter and Setters
    /////////////////////////////////
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
