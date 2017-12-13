package com.example.felipearango.appcompact.clases;

/**
 * Created by Felipe Arango on 11/12/2017.
 */

public class DatosC {

    private String name;
    private String comentario;
    private String foto;

    public DatosC(String name, String comentario, String foto) {
        this.name = name;
        this.comentario = comentario;
        this.foto = foto;
    }

    public DatosC(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
