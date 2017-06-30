/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *Este modelo es nos recoje las medidas necesarias para generar un informe
 * @author agg
 */
public class MedidasReport {
    private String fecha;
    private float peso;
    private float imc;
    private float pecho;
    private float cintura;
    private float cadera;
    private float muslo;

    public MedidasReport() {
    }

    public MedidasReport(String fecha,float peso, float imc, float pecho, float cintura, float cadera, float muslo) {
        this.fecha=fecha;
        this.peso = peso;
        this.imc = imc;
        this.pecho = pecho;
        this.cintura = cintura;
        this.cadera = cadera;
        this.muslo = muslo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
    
    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getImc() {
        return imc;
    }

    public void setImc(float imc) {
        this.imc = imc;
    }

   

    public float getPecho() {
        return pecho;
    }

    public void setPecho(float pecho) {
        this.pecho = pecho;
    }

    public float getCintura() {
        return cintura;
    }

    public void setCintura(float cintura) {
        this.cintura = cintura;
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
    
    
    
}
