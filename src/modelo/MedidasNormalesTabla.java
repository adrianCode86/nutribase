/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *Modelo necesario para obtener los campos necesarios para una tabla de medidasNormales
 * @author agg
 */
public class MedidasNormalesTabla {
    private Date fecha;
    private String dni;
    private float peso;
    private float porcentajeGrasa;
    private float cintura;
    private float abdomen;
    private float cadera;
    private float muslo;

    public MedidasNormalesTabla(Date fecha, String dni, float peso, float porcentajeGrasa, float cintura, float abdomen, float cadera, float muslo) {
        this.fecha = fecha;
        this.dni = dni;
        this.peso = peso;
        this.porcentajeGrasa = porcentajeGrasa;
        this.cintura = cintura;
        this.abdomen = abdomen;
        this.cadera = cadera;
        this.muslo = muslo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getPorcentajeGrasa() {
        return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(float porcentajeGrasa) {
        this.porcentajeGrasa = porcentajeGrasa;
    }

    public float getCintura() {
        return cintura;
    }

    public void setCintura(float cintura) {
        this.cintura = cintura;
    }

    public float getAbdomen() {
        return abdomen;
    }

    public void setAbdomen(float abdomen) {
        this.abdomen = abdomen;
    }

    public float getCadera() {
        return cadera;
    }

    public void setCadera(float cadera) {
        this.cadera = cadera;
    }

    public float getMuslo() {
        return muslo;
    }

    public void setMuslo(float muslo) {
        this.muslo = muslo;
    }
    
    /**
     * Este metodo devuelve todos los campos como un array de bits
     * @return 
     */
    public Object[] toObject(){
        return new Object[]{staticas.MetodosUtiles.fechaAstring(fecha),peso,porcentajeGrasa,cintura,abdomen,cadera,muslo};
    }
}
