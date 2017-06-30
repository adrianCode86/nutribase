/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 *Modelo con los datos clinicos de interes del paciente
 * @author agg
 */
@Entity
@Table(name="DatosClinicos")
public class DatosClinicos implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String actividadFisica;
    private boolean deportista;
    private String patologias;
    private String medicacion;
    private String gustosBuenos;
    private String gustosMalos;
    private String otrosDatosInteres;
    private String ocupacion;
    private String consumoAlcohol;
    private String consumoTabaco;
    private boolean cocinaEncasa;
    
    @OneToOne
    private Paciente paciente;

    public DatosClinicos() {
    }

    
    
    public int getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCocinaEncasa() {
        return cocinaEncasa;
    }

    public void setCocinaEncasa(boolean cocinaEncasa) {
        this.cocinaEncasa = cocinaEncasa;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    

    public String getConsumoAlcohol() {
        return consumoAlcohol;
    }

    public void setConsumoAlcohol(String consumoAlcohol) {
        this.consumoAlcohol = consumoAlcohol;
    }

    public String getConsumoTabaco() {
        return consumoTabaco;
    }

    public void setConsumoTabaco(String consumoTabaco) {
        this.consumoTabaco = consumoTabaco;
    }

    

    
    public String getActividadFisica() {
        return actividadFisica;
    }

    public void setActividadFisica(String actividadFisica) {
        this.actividadFisica = actividadFisica;
    }

    public boolean isDeportista() {
        return deportista;
    }

    public void setDeportista(boolean deportista) {
        this.deportista = deportista;
    }

    public String getPatologias() {
        return patologias;
    }

    public void setPatologias(String patologias) {
        this.patologias = patologias;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public String getGustosBuenos() {
        return gustosBuenos;
    }

    public void setGustosBuenos(String gustosBuenos) {
        this.gustosBuenos = gustosBuenos;
    }

    public String getGustosMalos() {
        return gustosMalos;
    }

    public void setGustosMalos(String gustosMalos) {
        this.gustosMalos = gustosMalos;
    }

    public String getOtrosDatosInteres() {
        return otrosDatosInteres;
    }

    public void setOtrosDatosInteres(String otrosDatosInteres) {
        this.otrosDatosInteres = otrosDatosInteres;
    }
    
    
    
}
