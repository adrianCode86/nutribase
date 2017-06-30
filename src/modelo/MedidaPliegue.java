/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


/**
 *Este modelo contiene las medidas necesarias solo para un paciente deportista
 * @author agg
 */
@Entity
@Table(name="MedidasPliegues")
@IdClass(MedidaPliegueId.class)
public class MedidaPliegue implements Serializable{
    @Id
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    private float tricipital;
    private float medidaPlieguePierna;
    private float abdominal;
    private float subescapular;
    private float suprailiaco;
    private float muslo;
    @Id
    @ManyToOne
    private Paciente paciente;
    
    
    public MedidaPliegue() {
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTricipital() {
        return tricipital;
    }

    public void setTricipital(float tricipital) {
        this.tricipital = tricipital;
    }

    public float getMedidaPlieguePierna() {
        return medidaPlieguePierna;
    }

    public void setMedidaPlieguePierna(float bicipital) {
        this.medidaPlieguePierna = bicipital;
    }

    public float getAbdominal() {
        return abdominal;
    }

    public void setAbdominal(float abdominal) {
        this.abdominal = abdominal;
    }

    public float getSubescapular() {
        return subescapular;
    }

    public void setSubescapular(float subescapular) {
        this.subescapular = subescapular;
    }

    public float getSuprailiaco() {
        return suprailiaco;
    }

    public void setSuprailiaco(float suprailiaco) {
        this.suprailiaco = suprailiaco;
    }

    public float getMuslo() {
        return muslo;
    }

    public void setMuslo(float muslo) {
        this.muslo = muslo;
    }
    /**
     * Este metodo nos devuelve un array de bits conel porncetaje de grasa y la fecha
     * @param sexo
     * @return array de bits con fecha y porcentaje de grasa
     */
    public Object[] getGrasaTabla(String sexo){
        return new Object[]{staticas.MetodosUtiles.fechaAstring(fecha),porcentajeGrasa(sexo)};
    }
   /**
    * Este metodo nos genera un array de bits con todos los campos del objeto necesarios para
    * una tabla medidasPliegue
    * @return fecha y todos los campos en un array de bits
    */ 
   public Object[] toObject(){
       return new Object[]{staticas.MetodosUtiles.fechaAstring(fecha),tricipital,suprailiaco,muslo,subescapular,medidaPlieguePierna,abdominal};
   }
    
    /////// Metodo propios que se calculan con formulas/////////
    
    
    /**
     * Metodo que me calcula el porcentaje de grasa, por medio de el parametro Hombre o Mujer
     * y los pliegues necesarios para sus formulas.
     * @param sexo
     * @return porcentaje de grasa en float
     */
    public float porcentajeGrasa(String sexo){
        
        if(sexo.equalsIgnoreCase("Hombre")){
            return (tricipital+subescapular+suprailiaco+abdominal+muslo+medidaPlieguePierna)*0.143f+4.56f;
        }else{
            return (tricipital+subescapular+suprailiaco+abdominal+muslo+medidaPlieguePierna)*0.097f+3.64f;
        }
        
    }
  
    
}
