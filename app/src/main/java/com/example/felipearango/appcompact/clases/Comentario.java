package com.example.felipearango.appcompact.clases;

/**
 * Created by Felipe Arango on 11/12/2017.
 */

public class Comentario {

    /////////////////
    //Variables
    ///////////////

    private String comentario;
    private String uidUser;

    /////////////////
    //Constructores
    ///////////////

    public Comentario(String comentario, String uidUser) {
        this.comentario = comentario;
        this.uidUser = uidUser;
    }

    public Comentario(){}

    //////////////////////
    //Getters and Setters
    /////////////////////

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUidUser() {
        return uidUser;
    }

    public void setUidUser(String uidUser) {
        this.uidUser = uidUser;
    }
}

