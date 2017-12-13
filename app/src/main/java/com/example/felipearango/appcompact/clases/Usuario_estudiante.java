package com.example.felipearango.appcompact.clases;

import java.util.ArrayList;

/**
 * Created by Felipe Arango on 5/12/2017.
 */

public class Usuario_estudiante {

    private int tipoUser ;
    private String correo;
    private String uid;
    private String foto;
    private ArrayList<String> lstKeyAulas;

    public Usuario_estudiante(int tipoUser, String correo, String uid) {
        this.tipoUser = tipoUser;
        this.correo = correo;
        this.uid = uid;
    }

    public Usuario_estudiante(int tipoUser, String correo, String uid, String foto,ArrayList<String> lstKeyAulas) {
        this.tipoUser = tipoUser;
        this.correo = correo;
        this.uid = uid;
        this.foto = foto;
        this.lstKeyAulas = lstKeyAulas;
    }

    public Usuario_estudiante(){}

    public int getTipoUser() {
        return tipoUser;
    }

    public String getCorreo() {
        return correo;
    }

    public String getUid() {
        return uid;
    }

    public ArrayList<String> getLstKeyAulas() {
        return lstKeyAulas;
    }

    public void setLstKeyAulas(ArrayList<String> lstKeyAulas) {
        this.lstKeyAulas = lstKeyAulas;
    }

    public String getFoto() {
        return foto;
    }
}
