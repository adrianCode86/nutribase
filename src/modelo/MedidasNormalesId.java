/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *Clase necesaria para generar la clave primaria compuesta en una medidaNormal
 * @author agg
 */
public class MedidasNormalesId implements Serializable{

 private Date fecha;
 private Paciente paciente;

    public MedidasNormalesId() {
    }

    public MedidasNormalesId(Date fecha, Paciente paciente) {
        this.fecha = fecha;
        this.paciente = paciente;
    }

    public MedidasNormalesId(Date fecha, String dni) {
       this.fecha = fecha;
        this.paciente = paciente;
    }

 
 
 
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.fecha);
        hash = 71 * hash + Objects.hashCode(this.paciente);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MedidasNormalesId other = (MedidasNormalesId) obj;
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.paciente, other.paciente)) {
            return false;
        }
        return true;
    }
 
 
 
    
}
