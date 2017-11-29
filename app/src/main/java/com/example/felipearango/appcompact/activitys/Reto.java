package com.example.felipearango.appcompact.activitys;

/**
 * Created by Usuario on 29/11/2017.
 */

public class Reto {
    String nombre;
    String descripcion;
    String tipoReto;
    String fechaEntrega;
    String integrante;
    String tipoEntrega;
    String privacidad;

    public Reto(){

    }

    public Reto(String nombre, String descripcion, String tipoReto, String fechaEntrega, String integrante, String tipoEntrega, String privacidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoReto = tipoReto;
        this.fechaEntrega = fechaEntrega;
        this.integrante = integrante;
        this.tipoEntrega = tipoEntrega;
        this.privacidad = privacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoReto() {
        return tipoReto;
    }

    public void setTipoReto(String tipoReto) {
        this.tipoReto = tipoReto;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getIntegrante() {
        return integrante;
    }

    public void setIntegrante(String integrante) {
        this.integrante = integrante;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }
}
