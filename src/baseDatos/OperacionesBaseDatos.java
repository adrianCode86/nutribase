/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDatos;

;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.DatosClinicos;
import modelo.MedidaPliegue;
import modelo.MedidasNormales;
import modelo.MedidasNormalesId;
import modelo.MedidasNormalesTabla;
import modelo.Paciente;
import modelo.PacienteTabla;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import vistas.MedidasPliegueDialogo;

/**
 *
 * @author agg
 */
public class OperacionesBaseDatos {
    
    
    private Session sesion;
    
    /**
     * Metodo que inicia la operacion de guardado
     * @throws HibernateException 
     */
    public void iniciar() {
        try{
        sesion = HibernateHutil.getSessionFactory().openSession();
        sesion.beginTransaction();
        }catch(HibernateException e){
            JOptionPane.showMessageDialog(null, e.getMessage()," Problema inicio conexion",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Ciera la operacion pero no termina la session
     * @throws HibernateException 
     */
    public void cerrar(){
        try{
       sesion.getTransaction().commit();
        sesion.close();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    /**
     * Metodo que cierra la conexion con la base de datos
     * @throws HibernateException 
     */
    public void terminar() {
        
        try{
        sesion.getSessionFactory().openSession().close();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    /**
     * Metodo privado interno que hace un rollback en caso de error
     * @param he
     * @throws HibernateException 
     */
    private void manejaExcepcion(HibernateException he) {
        sesion.getTransaction().rollback();
        JOptionPane.showMessageDialog(null, he.getMessage()," Problema base datos",JOptionPane.ERROR_MESSAGE);
    }
    
    public  Session sesion(){
        
        return sesion;
    }
    
    
   /////// ///parte metodos paciente////////////////
    
    /**
     * Metodo que devuelve un paciente por medio de su clave primaria
     * @param dni
     * @return Paciente
     */
    public Paciente getPaciente(String dni){
        Paciente paciente=null;
        try{
            iniciar();
            paciente=(Paciente) sesion.createQuery("FROM Paciente where dni = '"+dni+"'").uniqueResult();
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
             JOptionPane.showMessageDialog(null, e.getMessage()," Problema base datos",JOptionPane.ERROR_MESSAGE);
        }
        
        return paciente;
    }
    
    /**
     * Metodo que guarda un paciente pasado como parametro
     * @param paciente 
     */
    public void insertarPaciente(Paciente paciente){
        try{
            iniciar();
            sesion.persist(paciente);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    /**
     * Metodo que actuliza un paciente que le pasamos como parametro
     * @param paciente 
     */
    public void actulizarPaciente(Paciente paciente){
        
        try{
            iniciar();
            sesion.update(paciente);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    /**
     * Metodo que borra un paciente, que le pasamos como parametro
     * @param paciente 
     */
    public void borrarPaciente(Paciente paciente){
        
        try{
            iniciar();
            sesion.delete(paciente);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    
    ///////////metodos datosClinicos//////////
    
   
    /**
     * Metodo que retorna un objeto DatosClinicos por medio de su clave foranea
     * @param dni
     * @return DatosClinicos
     */
    public DatosClinicos getDatosClinicos(String dni){
        
         DatosClinicos datosClinicos=null;
        try{
            iniciar();
            datosClinicos=(DatosClinicos) sesion.createQuery("FROM DatosClinicos where paciente_dni = '"+dni+"'").uniqueResult();
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
             JOptionPane.showMessageDialog(null, e.getMessage()," Problema base datos",JOptionPane.ERROR_MESSAGE);
        }
        
        return datosClinicos;
        
    }
    
    /**
     * Metodo que inserta un objeto DatosClinicos
     * @param datos 
     */
    public void insertarDatosClinicos(DatosClinicos datos){
        
         try{
            iniciar();
            sesion.persist(datos);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    
    /**
     * Metodo que actualiza un objeto DatosClinicos que le pasamos como parametro
     * @param datos 
     */
    public void actualizarDatosClinicos(DatosClinicos datos){
         try{
            iniciar();
            sesion.update(datos);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    /**
     * Metodo que borra un objeto DatosClinicos, que le pasamos como parametro
     * @param datos 
     */
    public void borrarDatosClinicos(DatosClinicos datos){
        try{
            iniciar();
            sesion.delete(datos);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    
    //////////////// metodos MedidasNormales//////////
    
    
    /**
     * Metodo que me devuelve un objeto , por medio de la fecha
     * @param fecha
     * @return MedidasNormales
     */
    public MedidasNormales getMedidasNormales(Date fecha,String dni){
        
        MedidasNormales normales=null;
        
        try{
            iniciar();
            Query query=sesion.createQuery("select m FROM MedidasNormales m where m.fecha  =:fecha  and m.paciente.dni =:dni ");
            query.setParameter("fecha", fecha);
            query.setParameter("dni", dni);
            
            normales=(MedidasNormales) query.uniqueResult();
            
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
             JOptionPane.showMessageDialog(null, e.getMessage()," Problema base datos",JOptionPane.ERROR_MESSAGE);
        }
        return normales;
    }
    
    /**
     * Metodo que me devuelve todas las medidas de un paciente, del cual le pasamos su dni 
     * como parametro
     * @param dni
     * @return 
     */
    public List<MedidasNormales> getMedidasNormalesPaciente(String dni){
        List<MedidasNormales> medidas=null;
         
        try{
            iniciar();
            medidas=(List<MedidasNormales>)  sesion.createQuery("FROM MedidasNormales where paciente_dni = '"+dni+"' order by fecha ").list();
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
             JOptionPane.showMessageDialog(null, e.getMessage()," Problema base datos",JOptionPane.ERROR_MESSAGE);
        }
        
       
        return staticas.MetodosUtiles.elimarCerosIntercalados(medidas);
    }
    
    /**
     * Metodo que inserta un objeto MedidasNormales, que le pasamos como parametro
     * @param normales 
     */
    public void insertarMedidasNormales(MedidasNormales normales){
        
        try{
            iniciar();
            sesion.save(normales);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    
    /**
     * Metodo que actualiza un objeto MedidasNormales, que le pasamos como parametro
     * @param normales 
     */
    public void actualizarMedidasNormales(MedidasNormales normales){
        try{
            iniciar();
            sesion.update(normales);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    /**
     * Metodo que borrar un objeto MedidasNormales, que le pasamos como parametro
     * @param normales 
     */
    public void borrarMedidasNormales(MedidasNormales normales){
        try{
            iniciar();
            sesion.delete(normales);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    } 
    
    /////// metodos de MedidasPliegueDialogo/////
    
    
    /**
     * Metodo que me devuelve todas las medidas de un paciente, del cual le pasamos su dni 
     * como parametro
     * @param dni
     * @return 
     */
    public List<MedidaPliegue> getMedidasPlieguePaciente(String dni){
        List<MedidaPliegue> medidas=null;
         
        try{
            iniciar();
            medidas=(List<MedidaPliegue>)  sesion.createQuery("FROM MedidaPliegue where paciente_dni = '"+dni+"' order by fecha ").list();
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
             JOptionPane.showMessageDialog(null, e.getMessage()," Problema base datos",JOptionPane.ERROR_MESSAGE);
        }
        
       
        return medidas;
    }
    
    public MedidaPliegue getMedidaPliegue(Date fecha, String dni){
        
         MedidaPliegue pliegue=null;
        
        try{
            iniciar();
            pliegue=(MedidaPliegue) sesion.createQuery("FROM MedidaPliegue where fecha = '"+staticas.MetodosUtiles.fechaAstring(fecha)+"' and paciente_dni = '"+dni+"'").uniqueResult();
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
             JOptionPane.showMessageDialog(null, e.getMessage()," Problema base datos",JOptionPane.ERROR_MESSAGE);
        }
        return pliegue;
    }
    
    /**
     * Metodo que inserta un objetoMedidaPliegue, que le pasamos como parametro
     * @param pliegue 
     */
    public void insertarMedidasPliegue(MedidaPliegue pliegue){
        try{
            iniciar();
            sesion.save(pliegue);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
        
    }
    
    /**
     * Metodo que actualiza un objeto MedidasPliegue, que le pasamos como parametro
     * @param pliegue 
     */
    public void actulizarMedidasPliegue(MedidaPliegue pliegue){
        
        try{
            iniciar();
            sesion.update(pliegue);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    /**
     * Metodo que borra un objeto MedidasPliegue, que le pasamos como parametro
     * @param pliegue 
     */
    public void borrarMedidasPliegue(MedidaPliegue pliegue){
        
        try{
            iniciar();
            sesion.delete(pliegue);
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
    }
    
    
    /**
     * Metodo que nos devuelve una lista de PacienteTabla
     * que son necesarios para rellenear un jTable con pacientes
     * @return Lista PacienteTabla
     */
    
    public List<PacienteTabla> obtenerPacienteTabla(){
        List<Object[]> aux=null;
        List<PacienteTabla> pacientes=new ArrayList<>();
        
        try{
            iniciar();
            Query query=sesion.createQuery("SELECT p.foto,p.nombre,p.apellidos,p.dni from Paciente as p ORDER BY p.apellidos");
            aux=query.list();
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
        for(int i=0;i<aux.size();i++){
            pacientes.add(new PacienteTabla((byte [])aux.get(i)[0], (String)aux.get(i)[1],(String) aux.get(i)[2],(String) aux.get(i)[3]));
        }
        return pacientes;
    }
    
   /**
    * Retorna una lista de MeidasNormalesTabla que son necesarios para una tabla de medidas
    * @param dni
    * @return Lista de  MedidasNormalesTabla
    */
    public List<MedidasNormalesTabla> obtenerMedidasNormalesTabla(String dni){
        List<Object[]> aux=null;
        List<MedidasNormalesTabla> medidas=new ArrayList<>();
        try{
            iniciar();
            Query query=sesion.createQuery("SELECT m.fecha,m.paciente.dni,m.peso,m.porcentajeGrasa,m.medidaCintura,m.medidaAbdomen,m.medidaCadera,m.medidaMuslo from MedidasNormales as m where m.paciente.dni =:dni ORDER BY m.fecha");
            query.setParameter("dni", dni);
            aux=query.list();
            cerrar();
        }catch(HibernateException e){
            manejaExcepcion(e);
        }
        for(int i=0;i<aux.size();i++){
            medidas.add(new MedidasNormalesTabla((Date) aux.get(i)[0],(String) aux.get(i)[1], (float)aux.get(i)[2], (float)aux.get(i)[3], (float)aux.get(i)[4], (float)aux.get(i)[5], (float)aux.get(i)[6], (float)aux.get(i)[7]));
        }
        
        return  medidas;
    }
}
