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
    private String key;


    public Aula() { }

    public Aula(String profesor, String nombre, String descripcion, ArrayList<String> lstIntegrantes, String key) {
        this.profesor = profesor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lstIntegrantes = lstIntegrantes;
        this.key = key;
    }

    public Aula(String profesor, String nombre, String descripcion, ArrayList<String> lstIntegrantes, ArrayList<String> lstRetos, String key) {
        this.profesor = profesor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lstIntegrantes = lstIntegrantes;
        this.lstRetos = lstRetos;
        this.key = key;
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}