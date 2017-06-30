/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.*;


/**
 *Este  modelo tiene todas las medidas necesarias para un paciente normal, y parte de las necesarias
 * para un paciente deportista.
 * @author agg
 */
@Entity
@Table(name="MedidasNormales")
@IdClass(MedidasNormalesId.class)
public class MedidasNormales implements Serializable {
    @Id
    @Temporal(TemporalType.DATE)
    private Date fecha;
    private float peso;
    private float medidaCintura;
    private float medidaMuslo;
    private float medidaBrazo;
    private float medidaAbdomen;
    private float medidaCadera;
    private float medidaPecho;
    private float altura;
    private float medidaMunyeca;
    private float porcentajeAgua;
    private float porcentajeGrasa;
    private float porcentajeMasaMuscular;
    private float medidaGemelo;
    
    @Id
    @ManyToOne
    private Paciente paciente;
    public MedidasNormales() {
    }

    

    
    public Paciente getPaciente() {
        return paciente;
    }

    
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public float getMedidaGemelo() {
        return medidaGemelo;
    }

    public void setMedidaGemelo(float medidaGemelo) {
        this.medidaGemelo = medidaGemelo;
    }

    
    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getMedidaCintura() {
        return medidaCintura;
    }

    public void setMedidaCintura(float medidaCintura) {
        this.medidaCintura = medidaCintura;
    }

    public float getMedidaMuslo() {
        return medidaMuslo;
    }

    public void setMedidaMuslo(float medidaMuslo) {
        this.medidaMuslo = medidaMuslo;
    }

    public float getMedidaBrazo() {
        return medidaBrazo;
    }

    public void setMedidaBrazo(float medidaBrazo) {
        this.medidaBrazo = medidaBrazo;
    }

    public float getMedidaAbdomen() {
        return medidaAbdomen;
    }

    public void setMedidaAbdomen(float medidaAbdomen) {
        this.medidaAbdomen = medidaAbdomen;
    }

    public float getMedidaCadera() {
        return medidaCadera;
    }

    public void setMedidaCadera(float medidaCadera) {
        this.medidaCadera = medidaCadera;
    }

    public float getMedidaPecho() {
        return medidaPecho;
    }

    public void setMedidaPecho(float medidaPecho) {
        this.medidaPecho = medidaPecho;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getMedidaMunyeca() {
        return medidaMunyeca;
    }

    public void setMedidaMunyeca(float medidaMunyeca) {
        this.medidaMunyeca = medidaMunyeca;
    }

    public float getPorcentajeAgua() {
        return porcentajeAgua;
    }

    public void setPorcentajeAgua(float porcentajeAgua) {
        this.porcentajeAgua = porcentajeAgua;
    }

    public float getPorcentajeGrasa() {
        return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(float porcentajeGrasa) {
        this.porcentajeGrasa = porcentajeGrasa;
    }

    public float getPorcentajeMasaMuscular() {
        return porcentajeMasaMuscular;
    }

    public void setPorcentajeMasaMuscular(float porcentajeMasaMuscular) {
        this.porcentajeMasaMuscular = porcentajeMasaMuscular;
    }
    
    
    ///parte metodos necesarios para recargar tablaPaciente
    
    
    /**
     * Metodo necesarios para obtener el peso en una tabla con el peso
     * @return array de bits con fecha y peso
     */
    public Object[] getPesoTabla(){
        return new  Object[]{staticas.MetodosUtiles.fechaAstring(fecha),peso};
    }
    /**
     * Metodo necesario para obtener la medida de la cintura para una tabla
     * @return  array de bits con fecha y medida cintura
     */
    public Object[] getCinturaTabla(){
        return new Object[]{staticas.MetodosUtiles.fechaAstring(fecha),medidaCintura};
    }
    /**
     * Metodo necesario para obtener el porcentaje de grasa para una tabla con la grasa
     * @return array de bits con fecha y porcentaje de grasa
     */
    public Object[] getGrasaTabla(){
        return new Object[]{staticas.MetodosUtiles.fechaAstring(fecha),porcentajeGrasa};
    }
    /**
     * Metodo necesario para obtener el indice masa corporal para una tabla con dicha medida
     * @return array de bits con fecha y indice de masa corporal
     */
     public Object[] getImcTabla() {
        return new Object[]{staticas.MetodosUtiles.fechaAstring(fecha),calcularImc()};
    }
     
   

    @Override
    public String toString() {
        return "MedidasNormales{" + "fecha=" + fecha + ", peso=" + peso + ", medidaCintura=" + medidaCintura + ", medidaMuslo=" + medidaMuslo + ", medidaBrazo=" + medidaBrazo + ", medidaAbdomen=" + medidaAbdomen + ", medidaCadera=" + medidaCadera + ", medidaPecho=" + medidaPecho + ", altura=" + altura + ", medidaMunyeca=" + medidaMunyeca + ", porcentajeAgua=" + porcentajeAgua + ", porcentajeGrasa=" + porcentajeGrasa + ", porcentajeMasaMuscular=" + porcentajeMasaMuscular + ", paciente=" + paciente + '}';
    }
    
    /////parte formulas propias del objeto////
    
    /**
     * Formula que calcula el imc del paciente
     * @return imc
     */
    public float calcularImc(){
        return peso /(altura*altura);
    }

    /**
     * Metodo que nos devuelve el estado del paciente segun el imc
     * @return 
     */
   public String categoriaImc(){
       float imc=calcularImc();
       if(imc <18.5f){
           return "Infrapeso";
       }else if(imc >=18.5f && imc < 25.0f){
           return "Normopeso";
       }else if(imc >= 25.0f && imc < 27.0f){
           return "Sobrepeso grado 1";
       }else if(imc >= 27.0f && imc < 30.0f){
           return "Sobrepeso grado 2";
       }else if(imc >= 30.0f && imc < 35.0f){
           return "Obesidad tipo 1";
       }else if(imc >= 35.0f && imc < 40.0f){
           return "Obesidad tipo 2";
       }else if(imc >= 40.0f && imc < 50.0f){
           return "Obesidad mórbida";
       }else{
           return "Obesidad extrema";
       }
       
   }
   
   /**
    * Metodo que nos calcula el indice de cintura talla
    * @return indice cintura talla
    */
   public String getIndiceCinturaTalla(){
       float indiceCintura =  medidaCintura/(altura*100);
       if(indiceCintura <= 0.5f){
           return "Aceptable";
       }else{
           return "Excesivo";
       }
   }
   
   /**
    * Metodo que nos calcula en indice cintura cadera
    * para clasificar en funcion del tipo de obesidad 
    * del paciente
    * @param sexo
    * @return indice cintura cadera
    */
   public String getIndiceCinturaCadera(String sexo){
       
       float indiceCintura = medidaCintura/medidaCadera;
       
       if(sexo.equalsIgnoreCase("Hombre")){
           if(indiceCintura < 0.78f){
               return "Obesidad de tipo ginoide";
           }else if(indiceCintura >=0.78f && indiceCintura < 0.94f){
               return "Indice cintura cadera correcto";
           }else{
               return "Obesidad de tipo androide";
           }
           
       }else{
            if(indiceCintura < 0.71f){
               return "Obesidad de tipo ginoide";
           }else if(indiceCintura >=0.71f && indiceCintura < 0.84f){
               return "Indice cintura cadera correcto";
           }else{
               return "Obesidad de tipo androide";
           }
       }
   }
    
}
