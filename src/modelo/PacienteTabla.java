/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.IOException;
import javax.swing.JLabel;

/**
 *Este modelo recoje lo campos necesarios que necesita una tabla de pacientes
 * @author agg
 */
public class PacienteTabla {
    private byte[] foto;
    private String nombre;
    private String apellidos;
    private String dni;

    public PacienteTabla(byte[] foto, String nombre, String apellidos, String dni) {
        this.foto = foto;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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
    
    /**
     * Este metodo nos recoje los campos foto nombre y apellidos y los transforma
     * a un array de bits
     * @return array de bits con una fotos, nombre y apellidos
     * @throws IOException 
     */
    public Object[] toObject() throws IOException{
        
        return new Object[]{new JLabel(staticas.MetodosUtiles.getImagen(foto)),nombre,apellidos};
        
    }
}
