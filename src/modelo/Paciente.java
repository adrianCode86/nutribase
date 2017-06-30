/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;


/**
 *Este modelo recoje los datos mas basicos de un paciente
 * @author agg
 */
@Entity
@Table(name="Paciente")
public class Paciente implements Serializable{
    
    @Lob
    private byte[] foto;
    private String nombre;
    private String apellidos;
    @Id
    private String dni;
    private String direccion;
    private String email;
    private int numeroTelefono;
    private Date fechaNacimiento;
    private String sexo;
    private boolean embarazada;
    @OneToOne(cascade={CascadeType.ALL})
    private DatosClinicos datosClinicos;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="paciente")
    private List<MedidaPliegue> medidasPliegues=new ArrayList<>();
    
     
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="paciente")
    private List<MedidasNormales> MedidasNormales=new ArrayList<>();
    
    public Paciente() {
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    
    
    public DatosClinicos getDatosClinicos() {
        return datosClinicos;
    }

    public boolean isEmbarazada() {
        return embarazada;
    }

    public void setEmbarazada(boolean embarazada) {
        this.embarazada = embarazada;
    }

    
    
    public void setDatosClinicos(DatosClinicos datosClinicos) {
        this.datosClinicos = datosClinicos;
    }
    
    

    public void addPliegue(MedidaPliegue pliegue){
        medidasPliegues.add(pliegue);
    }
    
    public void addNormales(MedidasNormales normal){
        MedidasNormales.add(normal);
    }

    public List<MedidaPliegue> getPliegues() {
        return medidasPliegues;
    }

    public void setPliegues(List<MedidaPliegue> pliegues) {
        this.medidasPliegues = pliegues;
    }

    public List<MedidaPliegue> getMedidasPliegues() {
        return medidasPliegues;
    }

    public void setMedidasPliegues(List<MedidaPliegue> medidasPliegues) {
        this.medidasPliegues = medidasPliegues;
    }

    public List<MedidasNormales> getMedidasNormales() {
        return MedidasNormales;
    }

    public void setMedidasNormales(List<MedidasNormales> MedidasNormales) {
        this.MedidasNormales = MedidasNormales;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(int numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    /**
     * Este metodo calcula la edad segun su fecha de nacimiento
     * @return edad
     */
    public int getEdad() {

        Date aux = new Date();

        long edadMilisegundos = (aux.getTime() - fechaNacimiento.getTime());
        return (int) ((edadMilisegundos / (3600 * 24 * 1000)) / 365);
    }
    
}
