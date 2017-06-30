/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticas;

import baseDatos.OperacionesBaseDatos;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import modelo.MedidaPliegue;
import modelo.MedidasNormales;
import modelo.Paciente;
import static staticas.Graficas.*;
import static staticas.Graficas.getMes;
import vistas.MedidasPliegueDialogo;

/**
 *
 * @author agg
 */
public class MetodosUtiles {
    
    ///// partes fechas////////////////////////////////
     public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
     
     /**
      * Metodo que  toma una fecha en string y nos la convierte a Date
      * @param dateStr
      * @return Date fecha
      */
      public static Date stringAfecha(String dateStr) {
        try {
            return DATE_FORMAT.parse(dateStr);
        } catch (ParseException e) {
            return null;
        }
    }
     /**
      * Metodo que toma una fecha en formato Date y nos la transforma a string
      * @param date
      * @return fecha en formato string
      */
       public static String fechaAstring(Date date) {
        if (date == null) {
            return "";
        } else {
            return DATE_FORMAT.format(date);
            }
       }
     /**
      * Metodo que toma una fecha Date y la convierte a el formato dd-MMM-yyyy
      * @param date 
      * @return Date en formato dd-MMM-yyyy
      * @throws ParseException 
      */  
      public static Date convertirFechaModeloSpinner(Date date) throws ParseException{
          
          Date aux = DATE_FORMAT.parse(fechaAstring(date));
          DATE_FORMAT.applyPattern("dd-MMM-yyyy");
          return aux;
      }
    
      
      ////////////////////Parte validacion formularios/////////////////////////////////
      
      
      /**
       * Nos valida un correo electronico que le pasamos como string
       * @param correo
       * @return  boolean
       */
      public static boolean isEmail(String correo){
          Pattern pat=null;
          Matcher mat=null;
          pat=Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
          mat=pat.matcher(correo);
          if(mat.find()){
              return true;
          }else{
              return false;
          }
      }
      
      /**
       * Nos valida un string de un dni
       * @param dni
       * @return boolean
       */
      public static boolean isDni(String dni){
          
          
           Pattern pat=null;
          Matcher mat=null;
          pat=Pattern.compile("(([X-Z]{1})([-]?)(\\d{7})([-]?)([A-Z]{1}))|((\\d{8})([-]?)([A-Z]{1}))");
          mat=pat.matcher(dni);
          if(mat.find()){
              return true;
          }else{
              return false;
          }
      }
      
      
      
      ////////Metodos imagenes//////
      /**
       * Nos convierte una imagen   en un array de bits
       * @param fichero
       * @return array de bits con la imagen
       * @throws IOException 
       */
      public static byte[] getArrayImagen(File fichero) throws IOException{
          
          byte[] imagen=null;
         
        return  imagen=Files.readAllBytes(fichero.toPath());
          
      }
    /**
     * Nos convierte un InputStream a un array de bits
     * @param is
     * @return array de bits con los datos del input stream
     * @throws IOException 
     */ 
    public static byte[] toByteArrayUsingJava(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = is.read();
        while (reads != -1) {
            baos.write(reads);
            reads = is.read();
        }
        return baos.toByteArray();
    }



      /**
       * Nos devuelve un ImageIcon con una imagen que le pasamos como
       * array de bits
       * @param array
       * @return ImageIcon con una imagen
       * @throws IOException 
       */
      public static ImageIcon getImagen(byte[] array) throws IOException{
         
            BufferedImage image = null;
            InputStream in = new ByteArrayInputStream(array);
            image = ImageIO.read(in);

            ImageIcon icono = new ImageIcon(image);
            
            if(icono.getIconWidth() > 90) { 

               return new ImageIcon( icono.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT)); 

            } else { 
                return icono;
            } 
          
      }
      
      /**
       * Devuelve  un ImageIcon en un tamaño grande por medio de un array de bits
       * @param array
       * @return ImageIcon
       * @throws IOException 
       */
      public static ImageIcon getImagenGrande(byte[] array) throws IOException{
         
            BufferedImage image = null;
            InputStream in = new ByteArrayInputStream(array);
            image = ImageIO.read(in);

            ImageIcon icono = new ImageIcon(image);
            
            if(icono.getIconWidth() > 250) { 

               return new ImageIcon( icono.getImage().getScaledInstance(250, -1, Image.SCALE_DEFAULT)); 

            } else { 
                return icono;
            } 
          
      }
      
      /**
       * Devuelve un ImageIcon con una imagen que le pasamos como URL
       * @param ruta
       * @return ImageICon
       */
      public static ImageIcon getImagenFichero(URL ruta){
          
          ImageIcon imagen = new ImageIcon(ruta);
           if(imagen.getIconWidth() > 90) { 

               return new ImageIcon( imagen.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT)); 

            } else { 
                return imagen;
            } 
      }
      
      /////// metodos necesario para la tabla del paciente////
      
      /**
       * Metodo que nos devuelve una Lista object con las medidas de un paciente segun el tipo
       * que le pasamos como parametro
       * @param tipoMedidas
       * @param paciente
       * @return lista object con las medidas
       */
      public static List<Object[]>getMedidasTablaAnyo(String tipoMedidas, Paciente paciente){
          OperacionesBaseDatos op=new OperacionesBaseDatos();
          List<MedidasNormales> aux=elimarCerosIntercalados(op.getMedidasNormalesPaciente(paciente.getDni()));
          List<Object[]> medidas=new ArrayList<>();
          
         
              for(MedidasNormales medida:aux){
                 switch(tipoMedidas){
                     case "Peso":
                         medidas.add(medida.getPesoTabla());
                         break;
                     case "Cintura":
                         medidas.add(medida.getCinturaTabla());
                         break;
                     case "Grasa %":
                         medidas.add(medida.getGrasaTabla());
                         break;
                     case "Imc":
                         medidas.add(medida.getImcTabla());
                         break;
                 }
          }
          return medidas;
      }
      /**
       * Metodo que nos devuelve una Lista object con las medidas de un paciente segun el tipo, en un periodo 
       * de los últimos tres meses, que le pasamos como parametro
       * @param tipoMedidas
       * @param paciente
       * @return 
       */
      public static List<Object[]>getMedidasNormalesTablaTrimestre(String tipoMedidas,Paciente paciente){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidasNormales>> aux = getMedidasNormalesOrdenadasMeses(elimarCerosIntercalados(op.getMedidasNormalesPaciente(paciente.getDni())));
          List<Object[]> medidas=new ArrayList<>();
          
          Date fechaActual = new Date();
          Calendar actual = new GregorianCalendar();
          actual.setTime(fechaActual);
          if (actual.get(Calendar.MONTH) < 2) {
              for (int i = 0; i <= actual.get(Calendar.MONTH); i++) {
                  if (aux.get(i).size() > 0) {
                      for (int x = 0; x < aux.get(i).size(); x++) {
                          switch(tipoMedidas){
                     case "Peso":
                         medidas.add(aux.get(i).get(x).getPesoTabla());
                         break;
                     case "Cintura":
                         medidas.add(aux.get(i).get(x).getCinturaTabla());
                         break;
                     case "Grasa %":
                         medidas.add(aux.get(i).get(x).getGrasaTabla());
                         break;
                     case "Imc":
                         medidas.add(aux.get(i).get(x).getImcTabla());
                         break;
                 }
                      }
                  } 
              }
          } else {
              for (int i = actual.get(Calendar.MONTH) - 2; i <= actual.get(Calendar.MONTH); i++) {
                  if (aux.get(i).size() > 0) {
                      for (int x = 0; x < aux.get(i).size(); x++) {
                           switch(tipoMedidas){
                     case "Peso":
                         medidas.add(aux.get(i).get(x).getPesoTabla());
                         break;
                     case "Cintura":
                         medidas.add(aux.get(i).get(x).getCinturaTabla());
                         break;
                     case "Grasa %":
                         medidas.add(aux.get(i).get(x).getGrasaTabla());
                         break;
                     case "Imc":
                         medidas.add(aux.get(i).get(x).getImcTabla());
                         break;
                      }
                  } 
              }
            }
          }
          return medidas;
      
      }
       /////// metodos necesario para la tabla del paciente////
      
      /**
       * Metodo que nos devuelve una lista Object con todas los porcentajes de grasa del año
       * para un paciente deportista
       * @param paciente
       * @return Lista object con los porcentajes de grasa
       */
      public static List<Object[]>getGrasaPliegueTablaAnyo( Paciente paciente){
          OperacionesBaseDatos op=new OperacionesBaseDatos();
          List<MedidaPliegue> aux=elimarCerosIntercaladosPliegue(op.getMedidasPlieguePaciente(paciente.getDni()));
          List<Object[]> medidas=new ArrayList<>();
          
         
              for(MedidaPliegue medida:aux){
                medidas.add(medida.getGrasaTabla(paciente.getSexo()));
          }
          return medidas;
      }
      /**
       * Metodo que nos devuelve una lista Object con todas los porcentajes de grasa de los
       * últimos tres meses, para un paciente deportista
       * @param paciente
       * @return Lista con los porcetajes de grasa como object
       */
      public static List<Object[]>getGrasaPliegueTablaTrimestre(Paciente paciente){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidaPliegue>> aux = getMedidasPliegueOrdenadasMeses(elimarCerosIntercaladosPliegue(op.getMedidasPlieguePaciente(paciente.getDni())));
          List<Object[]> medidas=new ArrayList<>();
          
          Date fechaActual = new Date();
          Calendar actual = new GregorianCalendar();
          actual.setTime(fechaActual);
          if (actual.get(Calendar.MONTH) < 2) {
              for (int i = 0; i <= actual.get(Calendar.MONTH); i++) {
                  if (aux.get(i).size() > 0) {
                      for (int x = 0; x < aux.get(i).size(); x++) {
                          medidas.add(aux.get(i).get(x).getGrasaTabla(paciente.getSexo()));
                      }
                  } 
              }
          } else {
              for (int i = actual.get(Calendar.MONTH) - 2; i <= actual.get(Calendar.MONTH); i++) {
                  if (aux.get(i).size() > 0) {
                      for (int x = 0; x < aux.get(i).size(); x++) {
                           medidas.add(aux.get(i).get(x).getGrasaTabla(paciente.getSexo()));
                      }
                  } 
              }
          }
          return medidas;
      }
      
      //// parte de ordenacion medidas////
      
    
     /**
      * Metodo  que sustituye los valores cero , por el último valor anterior en un arrayMedidas.
      * Este metodo es necesario para que no alterar los resultados de las gráficas
      * @param normales
      * @return Lista con medidas normales sin ceros por el medio
      */
    public static List<MedidasNormales> elimarCerosIntercalados(List<MedidasNormales> normales){
        
        

        for (int i = 0; i < normales.size(); i++) {
            
            if (normales.get(i).getAltura() == 0.0f && i > 0 && (i - 1) < normales.size()) {
                normales.get(i).setAltura(normales.get(i - 1).getAltura());
            }
            if (normales.get(i).getMedidaAbdomen() == 0.0 && i > 0 & (i - 1) < normales.size()) {
                normales.get(i).setMedidaAbdomen(normales.get(i - 1).getMedidaAbdomen());
            }
            if (normales.get(i).getMedidaBrazo() == 0.0 && i > 0 & (i - 1) < normales.size()) {
                normales.get(i).setMedidaBrazo(normales.get(i - 1).getMedidaBrazo());
            }
            if (normales.get(i).getMedidaCadera() == 0.0 && i > 0 & (i - 1) < normales.size()) {
                normales.get(i).setMedidaCadera(normales.get(i - 1).getMedidaCadera());
            }
            if (normales.get(i).getMedidaCintura() == 0.0 && i > 0 && (i - 1) < normales.size()) {
                
                normales.get(i).setMedidaCintura(normales.get(i - 1).getMedidaCintura());
            }
            if (normales.get(i).getMedidaMunyeca() == 0.0 && i > 0 & (i - 1) < normales.size()) {
                normales.get(i).setMedidaMunyeca(normales.get(i - 1).getMedidaMunyeca());
            }
            if (normales.get(i).getMedidaMuslo() == 0.0 && i > 0 & (i - 1) < normales.size()) {
                normales.get(i).setMedidaMuslo(normales.get(i - 1).getMedidaMuslo());
            }
            if (normales.get(i).getPeso() == 0.0 && i > 0 && (i - 1) < normales.size()) {
                normales.get(i).setPeso(normales.get(i - 1).getPeso());
            }if (normales.get(i).getMedidaPecho() == 0.0 && i > 0 && (i - 1) < normales.size()) {
                normales.get(i).setMedidaPecho(normales.get(i - 1).getMedidaPecho());
            }
            
            if (normales.get(i).getPorcentajeAgua() == 0.0 && i > 0 & (i - 1) < normales.size()) {
                normales.get(i).setPorcentajeAgua(normales.get(i - 1).getPorcentajeAgua());
            }
            if (normales.get(i).getPorcentajeGrasa() == 0.0 && i > 0 & (i - 1) < normales.size()) {
                normales.get(i).setPorcentajeGrasa(normales.get(i - 1).getPorcentajeGrasa());
            }
            if (normales.get(i).getPorcentajeMasaMuscular() == 0.0 && i > 0 & (i - 1) < normales.size()) {
                normales.get(i).setPorcentajeMasaMuscular(normales.get(i - 1).getPorcentajeMasaMuscular());
            }

        }
        
       
        return normales;
    }
    
    
    
    
    
    
     
    /**
     * Metodo privado que sustituye los valores cero , por el último valor anterior en un arrayMedidas
     * Este metodo es necesario para que no alterar los resultados de las gráficas
     * @param pliegue
     * @return Lista con las medidas pligue sin ceros por el medio.
     */
    public static List<MedidaPliegue> elimarCerosIntercaladosPliegue(List<MedidaPliegue> pliegue){
        
        

        for (int i = 0; i < pliegue.size(); i++) {
            
            if (pliegue.get(i).getAbdominal() == 0.0f && i > 0 && (i - 1) < pliegue.size()) {
                pliegue.get(i).setAbdominal(pliegue.get(i - 1).getAbdominal());
            }
            if (pliegue.get(i).getMedidaPlieguePierna() == 0.0 && i > 0 & (i - 1) < pliegue.size()) {
                pliegue.get(i).setMedidaPlieguePierna(pliegue.get(i - 1).getMedidaPlieguePierna());
            }
            if (pliegue.get(i).getMuslo() == 0.0 && i > 0 & (i - 1) < pliegue.size()) {
                pliegue.get(i).setMuslo(pliegue.get(i - 1).getMuslo());
            }
            if (pliegue.get(i).getSubescapular() == 0.0 && i > 0 & (i - 1) < pliegue.size()) {
                pliegue.get(i).setSubescapular(pliegue.get(i - 1).getSubescapular());
            }
            if (pliegue.get(i).getSuprailiaco() == 0.0 && i > 0 & (i - 1) < pliegue.size()) {
                
                pliegue.get(i).setSuprailiaco(pliegue.get(i - 1).getSuprailiaco());
            }
            if (pliegue.get(i).getTricipital() == 0.0 && i > 0 & (i - 1) < pliegue.size()) {
                pliegue.get(i).setTricipital(pliegue.get(i - 1).getTricipital());
            }
            
        }
        
       
        return pliegue;
    }
    
    
   
}
