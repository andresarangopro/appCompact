package com.example.felipearango.appcompact.clases;

import java.util.ArrayList;

/**
 * Created by Usuario on 29/11/2017.
 */

public class Reto {
    String nombre;
    String descripcion;
    String tipoReto;
    ArrayList<String> fechasEntrega;
    String numIntegrante;
    String tipoEntrega;
    String privacidad;
    String individualOGrupo;

    public Reto() {

    }

    public Reto(String nombre, String descripcion, String tipoReto, ArrayList<String> fechasEntrega, String numIntegrante, String tipoEntrega, String privacidad, String individualOGrupo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoReto = tipoReto;
        this.fechasEntrega = fechasEntrega;
        this.numIntegrante = numIntegrante;
        this.tipoEntrega = tipoEntrega;
        this.privacidad = privacidad;
        this.individualOGrupo = individualOGrupo;
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

    public ArrayList<String> getFechasEntrega() {
        return fechasEntrega;
    }

    public void setFechasEntrega(ArrayList<String> fechasEntrega) {
        this.fechasEntrega = fechasEntrega;
    }

    public String getNumIntegrante() {
        return numIntegrante;
    }

    public void setNumIntegrante(String numIntegrante) {
        this.numIntegrante = numIntegrante;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getPrivacidad() {
        return privacidad;
    }

    public void setPrivacidad(String privacidad) {
        this.privacidad = privacidad;
    }

    public String getIndividualOGrupo() {
        return individualOGrupo;
    }

    public void setIndividualOGrupo(String individualOGrupo) {
        this.individualOGrupo = individualOGrupo;
    }
}