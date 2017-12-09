package com.example.felipearango.appcompact.clases;

import java.util.ArrayList;

/**
 * Created by Usuario on 8/12/2017.
 */

public class Aula {

    private String profesor;
    private String nombre;
    private String descripcion;
    private ArrayList<String> lstIntegrantes;
    private ArrayList<String> lstRetos;


    public Aula() {
    }

    public Aula(String profesor, String nombre, String descripcion, ArrayList<String> lstIntegrantes) {
        this.profesor = profesor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lstIntegrantes = lstIntegrantes;
    }

    public Aula(String profesor, String nombre, String descripcion, ArrayList<String> lstIntegrantes, ArrayList<String> lstRetos) {
        this.profesor = profesor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lstIntegrantes = lstIntegrantes;
        this.lstRetos = lstRetos;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
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

    public ArrayList<String> getLstIntegrantes() {
        return lstIntegrantes;
    }

    public void setLstIntegrantes(ArrayList<String> lstIntegrantes) {
        this.lstIntegrantes = lstIntegrantes;
    }

    public ArrayList<String> getLstRetos() {
        return lstRetos;
    }

    public void setLstRetos(ArrayList<String> lstRetos) {
        this.lstRetos = lstRetos;
    }
}