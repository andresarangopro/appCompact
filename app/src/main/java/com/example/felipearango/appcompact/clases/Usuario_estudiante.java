package com.example.felipearango.appcompact.clases;

/**
 * Created by Felipe Arango on 5/12/2017.
 */

public class Usuario_estudiante {

    private int tipoUser ;
    private String correo;
    private String uid;

    public Usuario_estudiante(int tipoUser, String correo, String uid) {
        this.tipoUser = tipoUser;
        this.correo = correo;
        this.uid = uid;
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
}
