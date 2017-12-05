package com.example.felipearango.appcompact.clases;

import java.util.ArrayList;

/**
 * Created by Usuario_estudiante on 29/11/2017.
 */

public class Reto {

    ///////////////////////////////////////////////
    //Variables
    ///////////////////////////////////////////////

    /**
     * Email del profesor o empresa que publica el reto
     */
    String emailResponsable;

    /**
     * Nombre del reto
     */
    String nombre;

    /**
     * Descripcion del reto
     */
    String descripcion;

    /**
     * El tipo del reto puede ser: reto elite, de aula o rally
     */
    String tipoReto;

    /**
     * Fechas asignadas a entregas del reto
     */
    ArrayList<String> fechasEntrega;

    /**
     * Numero de integrantes que pueden participar en el reto
     */
    String numIntegrante;

    /**
     * El tipo de entrega para cada fecha, puede ser: video, documentos o imagenes
     */
    ArrayList<String> tipoEntrega;

    /**
     * Un reto puede ser publico o privado
     */
    String privacidad;

    /**
     * Un reto puede realizarse individual o en grupo
     */
    String individualOGrupo;

    ///////////////////////////////////////////////
    //Constructor por defecto
    ///////////////////////////////////////////////
    public Reto() {

    }

    ///////////////////////////////////////////////
    //Constructor con parametros
    ///////////////////////////////////////////////
    public Reto(String emailResponsable, String nombre, String descripcion, String tipoReto,
                ArrayList<String> fechasEntrega, String numIntegrante, ArrayList<String> tipoEntrega,
                String privacidad, String individualOGrupo) {

        this.emailResponsable = emailResponsable;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoReto = tipoReto;
        this.fechasEntrega = fechasEntrega;
        this.numIntegrante = numIntegrante;
        this.tipoEntrega = tipoEntrega;
        this.privacidad = privacidad;
        this.individualOGrupo = individualOGrupo;
    }

    ///////////////////////////////////////////////
    //Getters & Setters
    ///////////////////////////////////////////////


    public String getEmailResponsable() {
        return emailResponsable;
    }

    public void setEmailResponsable(String emailResponsable) {
        this.emailResponsable = emailResponsable;
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

    public ArrayList<String> getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(ArrayList<String> tipoEntrega) {
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