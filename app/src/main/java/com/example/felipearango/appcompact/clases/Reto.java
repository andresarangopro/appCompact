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
    private String emailResponsable;

    /**
     * Nombre del reto
     */
    private String nombre;

    /**
     * Descripcion del reto
     */
    private String descripcion;

    /**
     * El tipo del reto puede ser: reto elite, de aula o rally
     */
    private String tipoReto;

    /**
     * Fechas asignadas a entregas del reto

    ArrayList<String> fechasEntrega;   */

    /**
     * Numero de integrantes que pueden participar en el reto
     */
    private String numIntegrante;

    /**
     * El tipo de entrega para cada fecha, puede ser: video, documentos o imagenes
     *
    ArrayList<String> tipoEntrega;*/

    /**
     * Un reto puede ser publico o privado
     */
    private String privacidad;

    /**
     * Un reto puede realizarse individual o en grupo
     */
    private String individualOGrupo;

    private String id;

    private ArrayList<String> lstIntegrantes;

    ///////////////////////////////////////////////
    //Constructor por defecto
    ///////////////////////////////////////////////
    public Reto() {

    }

    ///////////////////////////////////////////////
    //Constructor con parametros
    ///////////////////////////////////////////////

    /*public Reto(String emailResponsable, String nombre, String descripcion, String tipoReto, String numIntegrante, String privacidad, String individualOGrupo, String id) {
        this.emailResponsable = emailResponsable;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoReto = tipoReto;
        this.numIntegrante = numIntegrante;
        this.privacidad = privacidad;
        this.individualOGrupo = individualOGrupo;
        this.id = id;
    }*/

    public Reto(String emailResponsable, String nombre, String descripcion, String tipoReto, String numIntegrante, String privacidad, String individualOGrupo, String id, ArrayList<String> lstIntegrantes) {
        this.emailResponsable = emailResponsable;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoReto = tipoReto;
        this.numIntegrante = numIntegrante;
        this.privacidad = privacidad;
        this.individualOGrupo = individualOGrupo;
        this.id = id;
        this.lstIntegrantes = lstIntegrantes;
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

    public String getNumIntegrante() {
        return numIntegrante;
    }

    public void setNumIntegrante(String numIntegrante) {
        this.numIntegrante = numIntegrante;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getLstIntegrantes() {
        return lstIntegrantes;
    }

    public void setLstIntegrantes(ArrayList<String> lstIntegrantes) {
        this.lstIntegrantes = lstIntegrantes;
    }

    public String toString(){
        return "nombre: "+ getNombre() +" emailResponsable: "+getEmailResponsable()+ " descripcion: "+getDescripcion()+
                " tipoReto: "+getTipoReto() +" numeroIntegrante: "+getNumIntegrante() +" privacidad: "+getPrivacidad()+
                " individual O Grupo: "+getIndividualOGrupo();
    }
}