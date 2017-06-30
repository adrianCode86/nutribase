/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *Este modelo tiene los campos necesarios para generar un informe del paciente
 * @author agg
 */
public class PacienteReport {
    private String dni;
    private String nombre;
    private String apellidos;
    private String direccion;
    private int telefono;
    private String email;
    private int edad;
    private List<MedidasReport> medidas= new ArrayList<>();

    public PacienteReport() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public List<MedidasReport> getMedidas() {
        return medidas;
    }

    public void setMedidas(List<MedidasReport> medidas) {
        this.medidas = medidas;
    }
    
    
    public void addMedida(MedidasReport medida){
        medidas.add(medida);
    }
    
    
}
