/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package staticas;

import baseDatos.OperacionesBaseDatos;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.MedidaPliegue;
import modelo.MedidasNormales;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import vistas.MedidasPliegueDialogo;

/**
 *
 * @author agg
 */
public class Graficas {
    
      /**
       * Metodo que le pasamos una lista con todas las medidasNormales de un paciente y nos las ordena por meses
       * @param medidas
       * @return 
       */
      public static List<List<MedidasNormales>> getMedidasNormalesOrdenadasMeses(List<MedidasNormales> medidas){
          
          List<List<MedidasNormales>> meses = new ArrayList<>();
          List<MedidasNormales> enero = new ArrayList<MedidasNormales>();
          List<MedidasNormales> febrero = new ArrayList<MedidasNormales>();
          List<MedidasNormales> marzo = new ArrayList<MedidasNormales>();
          List<MedidasNormales> abril = new ArrayList<MedidasNormales>();
          List<MedidasNormales> mayo = new ArrayList<MedidasNormales>();
          List<MedidasNormales> junio = new ArrayList<MedidasNormales>();
          List<MedidasNormales> julio = new ArrayList<MedidasNormales>();
          List<MedidasNormales> agosto = new ArrayList<MedidasNormales>();
          List<MedidasNormales> septiembre = new ArrayList<MedidasNormales>();
          List<MedidasNormales> octubre = new ArrayList<MedidasNormales>();
          List<MedidasNormales> noviembre = new ArrayList<MedidasNormales>();
          List<MedidasNormales> diciembre = new ArrayList<MedidasNormales>();

          for (MedidasNormales medida : medidas) {
              Calendar calendario = new GregorianCalendar();
              calendario.setTime(medida.getFecha());
              switch (calendario.get(Calendar.MONTH)) {
                  case Calendar.JANUARY:
                      enero.add(medida);
                      break;
                  case Calendar.FEBRUARY:
                      febrero.add(medida);
                      break;
                  case Calendar.MARCH:
                      marzo.add(medida);
                      break;
                  case Calendar.APRIL:
                      abril.add(medida);
                      break;
                  case Calendar.MAY:
                      mayo.add(medida);
                      break;
                  case Calendar.JUNE:
                      junio.add(medida);
                      break;
                  case Calendar.JULY:
                      julio.add(medida);
                      break;
                  case Calendar.AUGUST:
                      agosto.add(medida);
                      break;
                  case Calendar.SEPTEMBER:
                      septiembre.add(medida);
                      break;
                  case Calendar.OCTOBER:
                      octubre.add(medida);
                      break;
                  case Calendar.NOVEMBER:
                      noviembre.add(medida);
                      break;
                  case Calendar.DECEMBER:
                      diciembre.add(medida);
                      break;

              }
          }
          meses.add(enero);
          meses.add(febrero);
          meses.add(marzo);
          meses.add(abril);
          meses.add(mayo);
          meses.add(junio);
          meses.add(julio);
          meses.add(agosto);
          meses.add(septiembre);
          meses.add(octubre);
          meses.add(noviembre);
          meses.add(diciembre);
                   
        
          
        return meses;
          
      }
     /**
      * Metodo que nos borra los meses nulos de un lista con listas de meidas normales
      * @param medidas
      * @return Lista con listas de medidas normales
      */
       public static List<List<MedidasNormales>> borrarMesesNull(List<List<MedidasNormales>> medidas){
           List<List<MedidasNormales>> aux= new ArrayList<>();
           
           for(int i=0; i< medidas.size();i++){
               if(medidas.get(i).size()==0&&i>0){
                   aux.add(medidas.get(i-1));
               }else{
                   aux.add(medidas.get(i));
               }
           }
           
           return aux;
       }     
      
      /**
       * Nos inserta en La label que le pasemos una grafica con el primer peso del año, y el ultimo peso de cada mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void GraficaMedidasNormalesTotalPeso(JPanel panel,JLabel label,String dni){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidasNormales>> medidas =  borrarMesesNull(getMedidasNormalesOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercalados(op.getMedidasNormalesPaciente(dni))));

          //// da error xk no tengo datos de cada mes, solo funciona si tengo todos los datos para llenarla
          /// debo comproar si cada medida tiene el mes si no ponerlo a cero, par ello creo un campo flot por cada mes si esta null el mes lo pongo a cero.
        
          
          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
          if(medidas.get(0).size()>0){
          data.addValue(medidas.get(0).get(0).getPeso(), "Paciente", "Ene");
          }else if(medidas.get(0).size()<=0 && medidas.get(1).size()>0){
              data.addValue(0.0, "Paciente", "Ene");
          }
          else{
              data.addValue(null, "Paciente", "Ene");
          }
          if(medidas.get(1).size()>0){
          data.addValue(medidas.get(1).get(medidas.get(1).size() - 1).getPeso(), "Paciente", "Feb");
          }else if(medidas.get(1).size()<=0 && medidas.get(2).size()>0){
              data.addValue(0.0, "Paciente", "Feb");
          }
          else{
               data.addValue(null, "Paciente", "Feb");
          }
          if(medidas.get(2).size()>0){
          data.addValue(medidas.get(2).get(medidas.get(2).size() - 1).getPeso(), "Paciente", "Mar");
          }else if(medidas.get(2).size()<=0 && medidas.get(3).size()>0){
              data.addValue(0.0, "Paciente", "Mar");
          }else{
              data.addValue(null, "Paciente", "Mar");
          }
          if(medidas.get(3).size()>0){
          data.addValue(medidas.get(3).get(medidas.get(3).size() - 1).getPeso(), "Paciente", "Abr");
          }else if(medidas.get(3).size()<=0 && medidas.get(4).size()>0){
              data.addValue(0.0, "Paciente", "Abr");
          }else{
              data.addValue(null, "Paciente", "Abr");
          }
          if(medidas.get(4).size()>0){
          data.addValue(medidas.get(4).get(medidas.get(4).size() - 1).getPeso(), "Paciente", "May");
          }else if(medidas.get(4).size()<=0 && medidas.get(5).size()>0){
              data.addValue(0.0, "Paciente", "May");
          }
          else{
              data.addValue(null, "Paciente", "May");
          }
          if(medidas.get(5).size()>0){
          data.addValue(medidas.get(5).get(medidas.get(5).size() - 1).getPeso(), "Paciente", "Jun");
          }else if(medidas.get(5).size()<=0 && medidas.get(6).size()>0){
              data.addValue(0.0, "Paciente", "Jun");
          }else{
              data.addValue(null, "Paciente", "Jun");
          }
          if(medidas.get(6).size()>0){
          data.addValue(medidas.get(6).get(medidas.get(6).size() - 1).getPeso(), "Paciente", "Jul");
          }else if(medidas.get(6).size()<=0 && medidas.get(7).size()>0){
              data.addValue(0.0, "Paciente", "Jul");
          }else{
              data.addValue(null, "Paciente", "Jul");
          }
          if(medidas.get(7).size()>0){
          data.addValue(medidas.get(7).get(medidas.get(7).size() - 1).getPeso(), "Paciente", "Ago");
          }else if(medidas.get(7).size()<=0 && medidas.get(8).size()>0){
              data.addValue(0.0, "Paciente", "Ago");
          }else{
              data.addValue(null, "Paciente", "Ago");
          }if(medidas.get(8).size()>0){
          data.addValue(medidas.get(8).get(medidas.get(8).size() - 1).getPeso(), "Paciente", "Sep");
          }else if(medidas.get(8).size()<=0 && medidas.get(9).size()>0){
              data.addValue(0.0, "Paciente", "Sep");
          }else{
              data.addValue(null, "Paciente", "Sep");
          }
          if(medidas.get(9).size()>0){
          data.addValue(medidas.get(9).get(medidas.get(9).size() - 1).getPeso(), "Paciente", "Oct");
          }else if(medidas.get(9).size()<=0 && medidas.get(10).size()>0){
              data.addValue(0.0, "Paciente", "Oct");
          }else{
              data.addValue(null, "Paciente", "Oct");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(10).get(medidas.get(10).size() - 1).getPeso(), "Paciente", "Nov");
          }else if(medidas.get(10).size()<=0 && medidas.get(11).size()>0){
              data.addValue(0.0, "Paciente", "Nov");
          }else{
              data.addValue(null, "Paciente", "Nov");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(11).get(medidas.get(11).size() - 1).getPeso(), "Paciente", "Dec");
          }else{
               data.addValue(null, "Paciente", "Dec");
          }
          linea = ChartFactory.createLineChart("Gráfico peso paciente en un año", "Meses", "Peso", data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }
      
      /**
       * Nos inserta en la label que le pasemos, como parametro, una grafrica con los peso del ultimo trimestre
       * Donde prima el ultimo peso del mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void graficaMedidasNormalesUltimoTrimestrePeso(JPanel panel,JLabel label,String dni){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
         List<List<MedidasNormales>> medidas =  borrarMesesNull(getMedidasNormalesOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercalados(op.getMedidasNormalesPaciente(dni))));
          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
          Date fechaActual=new Date();
          Calendar actual=new GregorianCalendar();
          actual.setTime(fechaActual);
          if(actual.get(Calendar.MONTH)<2){
              for(int i=0;i<=actual.get(Calendar.MONTH);i++){
                  if(medidas.get(i).size()>0){
                  for(int x=0;x<medidas.get(i).size();x++){
                      data.addValue(medidas.get(i).get(x).getPeso(), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                  }
                  }else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  }else{
                       data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          }else{
              for(int i=actual.get(Calendar.MONTH)-2;i<=actual.get(Calendar.MONTH);i++){
                  if(medidas.get(i).size()>0){
                  for(int x=0;x<medidas.get(i).size();x++){
                      Calendar aux=new GregorianCalendar();
                      aux.setTime(medidas.get(i).get(x).getFecha());
                      
                      data.addValue(medidas.get(i).get(x).getPeso(), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                      
                    }
                  }else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  }else{
                     data.addValue(0.0, "Paciente", obtenerMesPosicion(i)); 
                  }
              }
          }
           linea = ChartFactory.createLineChart("Gráfico peso paciente último trimestre", "Meses", "Peso", data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }


      /**
       * Metodo al cual le damos una fecha y nos devuelve un string con su mes
       * @param fecha
       * @return string con el mes
       */
      public static String getMes(Date fecha){
          
          Calendar calendario = new GregorianCalendar();
              calendario.setTime(fecha);
              switch (calendario.get(Calendar.MONTH)) {
                  case Calendar.JANUARY:
                      return "Ene";
                      
                  case Calendar.FEBRUARY:
                      return "Feb";
                  case Calendar.MARCH:
                      return "Mar";
                  case Calendar.APRIL:
                      return "Abr";
                      
                  case Calendar.MAY:
                      return "May";
                  case Calendar.JUNE:
                      return "Jun";
                  case Calendar.JULY:
                      return "Jul";
                  case Calendar.AUGUST:
                     return "Ago";
                  case Calendar.SEPTEMBER:
                      return "Sep";
                  case Calendar.OCTOBER:
                      return "Oct";
                  case Calendar.NOVEMBER:
                      return "Nov";
                  case Calendar.DECEMBER:
                      return "Dic";
              }
              return null;
      }
      
      
       /**
       * Nos inserta en la label que le pasemos, como parametro, una grafrica con la medidas de la cintura del ultimo trimestre
       * Donde prima el ultimo peso del mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void graficaMedidasNormalesUltimoTrimestreCintura(JPanel panel,JLabel label,String dni){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidasNormales>> medidas =  borrarMesesNull(getMedidasNormalesOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercalados(op.getMedidasNormalesPaciente(dni))));
          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
          Date fechaActual = new Date();
          Calendar actual = new GregorianCalendar();
          actual.setTime(fechaActual);
          if (actual.get(Calendar.MONTH) < 2) {
              for (int i = 0; i <= actual.get(Calendar.MONTH); i++) {
                  if (medidas.get(i).size() > 0) {
                      for (int x = 0; x < medidas.get(i).size(); x++) {
                          data.addValue(medidas.get(i).get(x).getMedidaCintura(), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                      }
                      
                  } else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  }else {
                      data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          } else {
              for (int i = actual.get(Calendar.MONTH) - 2; i <= actual.get(Calendar.MONTH); i++) {
                  if (medidas.get(i).size() > 0) {
                      for (int x = 0; x < medidas.get(i).size(); x++) {
                          Calendar aux = new GregorianCalendar();
                          aux.setTime(medidas.get(i).get(x).getFecha());
                          
                          data.addValue(medidas.get(i).get(x).getMedidaCintura(), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                          
                      }
                  }else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  } else {
                      data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          }
          linea = ChartFactory.createLineChart("Gráfico medida cintura del paciente, último","Meses", "Medida cintura",  data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }

      /**
       * Nos inserta en La label que le pasemos una grafica con la primera medida de la cintura del año, y la ultima medida  de cada mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void GraficaMedidasNormalesTotalCintura(JPanel panel,JLabel label,String dni){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
         List<List<MedidasNormales>> medidas =  borrarMesesNull(getMedidasNormalesOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercalados(op.getMedidasNormalesPaciente(dni))));

          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
         if(medidas.get(0).size()>0){
          data.addValue(medidas.get(0).get(0).getMedidaCintura(), "Paciente", "Ene");
          }else if(medidas.get(0).size()<=0 && medidas.get(1).size()>0){
              data.addValue(0.0, "Paciente", "Ene");
          }
          else{
              data.addValue(null, "Paciente", "Ene");
          }
          if(medidas.get(1).size()>0){
          data.addValue(medidas.get(1).get(medidas.get(1).size() - 1).getMedidaCintura(), "Paciente", "Feb");
          }else if(medidas.get(1).size()<=0 && medidas.get(2).size()>0){
              data.addValue(0.0, "Paciente", "Feb");
          }
          else{
               data.addValue(null, "Paciente", "Feb");
          }
          if(medidas.get(2).size()>0){
          data.addValue(medidas.get(2).get(medidas.get(2).size() - 1).getMedidaCintura(), "Paciente", "Mar");
          }else if(medidas.get(2).size()<=0 && medidas.get(3).size()>0){
              data.addValue(0.0, "Paciente", "Mar");
          }else{
              data.addValue(null, "Paciente", "Mar");
          }
          if(medidas.get(3).size()>0){
          data.addValue(medidas.get(3).get(medidas.get(3).size() - 1).getMedidaCintura(), "Paciente", "Abr");
          }else if(medidas.get(3).size()<=0 && medidas.get(4).size()>0){
              data.addValue(0.0, "Paciente", "Abr");
          }else{
              data.addValue(null, "Paciente", "Abr");
          }
          if(medidas.get(4).size()>0){
          data.addValue(medidas.get(4).get(medidas.get(4).size() - 1).getMedidaCintura(), "Paciente", "May");
          }else if(medidas.get(4).size()<=0 && medidas.get(5).size()>0){
              data.addValue(0.0, "Paciente", "May");
          }
          else{
              data.addValue(null, "Paciente", "May");
          }
          if(medidas.get(5).size()>0){
          data.addValue(medidas.get(5).get(medidas.get(5).size() - 1).getMedidaCintura(), "Paciente", "Jun");
          }else if(medidas.get(5).size()<=0 && medidas.get(6).size()>0){
              data.addValue(0.0, "Paciente", "Jun");
          }else{
              data.addValue(null, "Paciente", "Jun");
          }
          if(medidas.get(6).size()>0){
          data.addValue(medidas.get(6).get(medidas.get(6).size() - 1).getMedidaCintura(), "Paciente", "Jul");
          }else if(medidas.get(6).size()<=0 && medidas.get(7).size()>0){
              data.addValue(0.0, "Paciente", "Jul");
          }else{
              data.addValue(null, "Paciente", "Jul");
          }
          if(medidas.get(7).size()>0){
          data.addValue(medidas.get(7).get(medidas.get(7).size() - 1).getMedidaCintura(), "Paciente", "Ago");
          }else if(medidas.get(7).size()<=0 && medidas.get(8).size()>0){
              data.addValue(0.0, "Paciente", "Ago");
          }else{
              data.addValue(null, "Paciente", "Ago");
          }if(medidas.get(8).size()>0){
          data.addValue(medidas.get(8).get(medidas.get(8).size() - 1).getMedidaCintura(), "Paciente", "Sep");
          }else if(medidas.get(8).size()<=0 && medidas.get(9).size()>0){
              data.addValue(0.0, "Paciente", "Sep");
          }else{
              data.addValue(null, "Paciente", "Sep");
          }
          if(medidas.get(9).size()>0){
          data.addValue(medidas.get(9).get(medidas.get(9).size() - 1).getMedidaCintura(), "Paciente", "Oct");
          }else if(medidas.get(9).size()<=0 && medidas.get(10).size()>0){
              data.addValue(0.0, "Paciente", "Oct");
          }else{
              data.addValue(null, "Paciente", "Oct");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(10).get(medidas.get(10).size() - 1).getMedidaCintura(), "Paciente", "Nov");
          }else if(medidas.get(10).size()<=0 && medidas.get(11).size()>0){
              data.addValue(0.0, "Paciente", "Nov");
          }else{
              data.addValue(null, "Paciente", "Nov");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(11).get(medidas.get(11).size() - 1).getMedidaCintura(), "Paciente", "Dec");
          }else{
               data.addValue(null, "Paciente", "Dec");
          }
          linea = ChartFactory.createLineChart("Gráfico medida cintura del paciente en un año","Meses", "Medida cintura",  data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }

      /**
       * Nos inserta en La label que le pasemos una grafica con el primer porcentaje de grasa  del año, y la último  de cada mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void GraficaMedidasNormalesTotalGrasa(JPanel panel,JLabel label,String dni){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidasNormales>> medidas =  borrarMesesNull(getMedidasNormalesOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercalados(op.getMedidasNormalesPaciente(dni))));

          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
         if(medidas.get(0).size()>0){
          data.addValue(medidas.get(0).get(0).getPorcentajeGrasa(), "Paciente", "Ene");
          }else if(medidas.get(0).size()<=0 && medidas.get(1).size()>0){
              data.addValue(0.0, "Paciente", "Ene");
          }
          else{
              data.addValue(null, "Paciente", "Ene");
          }
          if(medidas.get(1).size()>0){
          data.addValue(medidas.get(1).get(medidas.get(1).size() - 1).getPorcentajeGrasa(), "Paciente", "Feb");
          }else if(medidas.get(1).size()<=0 && medidas.get(2).size()>0){
              data.addValue(0.0, "Paciente", "Feb");
          }
          else{
               data.addValue(null, "Paciente", "Feb");
          }
          if(medidas.get(2).size()>0){
          data.addValue(medidas.get(2).get(medidas.get(2).size() - 1).getPorcentajeGrasa(), "Paciente", "Mar");
          }else if(medidas.get(2).size()<=0 && medidas.get(3).size()>0){
              data.addValue(0.0, "Paciente", "Mar");
          }else{
              data.addValue(null, "Paciente", "Mar");
          }
          if(medidas.get(3).size()>0){
          data.addValue(medidas.get(3).get(medidas.get(3).size() - 1).getPorcentajeGrasa(), "Paciente", "Abr");
          }else if(medidas.get(3).size()<=0 && medidas.get(4).size()>0){
              data.addValue(0.0, "Paciente", "Abr");
          }else{
              data.addValue(null, "Paciente", "Abr");
          }
          if(medidas.get(4).size()>0){
          data.addValue(medidas.get(4).get(medidas.get(4).size() - 1).getPorcentajeGrasa(), "Paciente", "May");
          }else if(medidas.get(4).size()<=0 && medidas.get(5).size()>0){
              data.addValue(0.0, "Paciente", "May");
          }
          else{
              data.addValue(null, "Paciente", "May");
          }
          if(medidas.get(5).size()>0){
          data.addValue(medidas.get(5).get(medidas.get(5).size() - 1).getPorcentajeGrasa(), "Paciente", "Jun");
          }else if(medidas.get(5).size()<=0 && medidas.get(6).size()>0){
              data.addValue(0.0, "Paciente", "Jun");
          }else{
              data.addValue(null, "Paciente", "Jun");
          }
          if(medidas.get(6).size()>0){
          data.addValue(medidas.get(6).get(medidas.get(6).size() - 1).getPorcentajeGrasa(), "Paciente", "Jul");
          }else if(medidas.get(6).size()<=0 && medidas.get(7).size()>0){
              data.addValue(0.0, "Paciente", "Jul");
          }else{
              data.addValue(null, "Paciente", "Jul");
          }
          if(medidas.get(7).size()>0){
          data.addValue(medidas.get(7).get(medidas.get(7).size() - 1).getPorcentajeGrasa(), "Paciente", "Ago");
          }else if(medidas.get(7).size()<=0 && medidas.get(8).size()>0){
              data.addValue(0.0, "Paciente", "Ago");
          }else{
              data.addValue(null, "Paciente", "Ago");
          }if(medidas.get(8).size()>0){
          data.addValue(medidas.get(8).get(medidas.get(8).size() - 1).getPorcentajeGrasa(), "Paciente", "Sep");
          }else if(medidas.get(8).size()<=0 && medidas.get(9).size()>0){
              data.addValue(0.0, "Paciente", "Sep");
          }else{
              data.addValue(null, "Paciente", "Sep");
          }
          if(medidas.get(9).size()>0){
          data.addValue(medidas.get(9).get(medidas.get(9).size() - 1).getPorcentajeGrasa(), "Paciente", "Oct");
          }else if(medidas.get(9).size()<=0 && medidas.get(10).size()>0){
              data.addValue(0.0, "Paciente", "Oct");
          }else{
              data.addValue(null, "Paciente", "Oct");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(10).get(medidas.get(10).size() - 1).getPorcentajeGrasa(), "Paciente", "Nov");
          }else if(medidas.get(10).size()<=0 && medidas.get(11).size()>0){
              data.addValue(0.0, "Paciente", "Nov");
          }else{
              data.addValue(null, "Paciente", "Nov");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(11).get(medidas.get(11).size() - 1).getPorcentajeGrasa(), "Paciente", "Dec");
          }else{
               data.addValue(null, "Paciente", "Dec");
          }
          linea = ChartFactory.createLineChart("Gráfico porcentaje de grasa del paciente en un año","Meses", "Porcentaje de grasa", data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }
      
       /**
       * Nos inserta en la label que le pasemos, como parametro, una grafrica con la medidas de la cintura del ultimo trimestre
       * Donde prima el ultimo peso del mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void graficaMedidasNormalesUltimoTrimestreGrasa(JPanel panel,JLabel label,String dni){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidasNormales>> medidas =  borrarMesesNull(getMedidasNormalesOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercalados(op.getMedidasNormalesPaciente(dni))));
          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
          Date fechaActual = new Date();
          Calendar actual = new GregorianCalendar();
          actual.setTime(fechaActual);
          if (actual.get(Calendar.MONTH) < 2) {
              for (int i = 0; i <= actual.get(Calendar.MONTH); i++) {
                  if (medidas.get(i).size() > 0) {
                      for (int x = 0; x < medidas.get(i).size(); x++) {
                          data.addValue(medidas.get(i).get(x).getPorcentajeGrasa(), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                      }
                  }else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  } else {
                      data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          } else {
              for (int i = actual.get(Calendar.MONTH) - 2; i <= actual.get(Calendar.MONTH); i++) {
                  if (medidas.get(i).size() > 0) {
                      for (int x = 0; x < medidas.get(i).size(); x++) {
                          Calendar aux = new GregorianCalendar();
                          aux.setTime(medidas.get(i).get(x).getFecha());
                          
                          data.addValue(medidas.get(i).get(x).getPorcentajeGrasa(), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                          
                      }
                  } else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  }else {
                      data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          }
          linea = ChartFactory.createLineChart("Gráfico procentaje de grasa último trimestre","Meses", "Porcentaje de grasa", data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }
      
      /**
       * Metodo que nos inserta en una grafica todas las medidas del imc del año del paciente 
       * @param panel
       * @param label
       * @param dni 
       */
       public static void GraficaMedidasNormalesTotalImc(JPanel panel,JLabel label,String dni){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidasNormales>> medidas =  borrarMesesNull(getMedidasNormalesOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercalados(op.getMedidasNormalesPaciente(dni))));

          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
          if(medidas.get(0).size()>0){
          data.addValue(medidas.get(0).get(0).calcularImc(), "Paciente", "Ene");
          }else if(medidas.get(0).size()<=0 && medidas.get(1).size()>0){
              data.addValue(0.0, "Paciente", "Ene");
          }
          else{
              data.addValue(null, "Paciente", "Ene");
          }
          if(medidas.get(1).size()>0){
          data.addValue(medidas.get(1).get(medidas.get(1).size() - 1).calcularImc(), "Paciente", "Feb");
          }else if(medidas.get(1).size()<=0 && medidas.get(2).size()>0){
              data.addValue(0.0, "Paciente", "Feb");
          }
          else{
               data.addValue(null, "Paciente", "Feb");
          }
          if(medidas.get(2).size()>0){
          data.addValue(medidas.get(2).get(medidas.get(2).size() - 1).calcularImc(), "Paciente", "Mar");
          }else if(medidas.get(2).size()<=0 && medidas.get(3).size()>0){
              data.addValue(0.0, "Paciente", "Mar");
          }else{
              data.addValue(null, "Paciente", "Mar");
          }
          if(medidas.get(3).size()>0){
          data.addValue(medidas.get(3).get(medidas.get(3).size() - 1).calcularImc(), "Paciente", "Abr");
          }else if(medidas.get(3).size()<=0 && medidas.get(4).size()>0){
              data.addValue(0.0, "Paciente", "Abr");
          }else{
              data.addValue(null, "Paciente", "Abr");
          }
          if(medidas.get(4).size()>0){
          data.addValue(medidas.get(4).get(medidas.get(4).size() - 1).calcularImc(), "Paciente", "May");
          }else if(medidas.get(4).size()<=0 && medidas.get(5).size()>0){
              data.addValue(0.0, "Paciente", "May");
          }
          else{
              data.addValue(null, "Paciente", "May");
          }
          if(medidas.get(5).size()>0){
          data.addValue(medidas.get(5).get(medidas.get(5).size() - 1).calcularImc(), "Paciente", "Jun");
          }else if(medidas.get(5).size()<=0 && medidas.get(6).size()>0){
              data.addValue(0.0, "Paciente", "Jun");
          }else{
              data.addValue(null, "Paciente", "Jun");
          }
          if(medidas.get(6).size()>0){
          data.addValue(medidas.get(6).get(medidas.get(6).size() - 1).calcularImc(), "Paciente", "Jul");
          }else if(medidas.get(6).size()<=0 && medidas.get(7).size()>0){
              data.addValue(0.0, "Paciente", "Jul");
          }else{
              data.addValue(null, "Paciente", "Jul");
          }
          if(medidas.get(7).size()>0){
          data.addValue(medidas.get(7).get(medidas.get(7).size() - 1).calcularImc(), "Paciente", "Ago");
          }else if(medidas.get(7).size()<=0 && medidas.get(8).size()>0){
              data.addValue(0.0, "Paciente", "Ago");
          }else{
              data.addValue(null, "Paciente", "Ago");
          }if(medidas.get(8).size()>0){
          data.addValue(medidas.get(8).get(medidas.get(8).size() - 1).calcularImc(), "Paciente", "Sep");
          }else if(medidas.get(8).size()<=0 && medidas.get(9).size()>0){
              data.addValue(0.0, "Paciente", "Sep");
          }else{
              data.addValue(null, "Paciente", "Sep");
          }
          if(medidas.get(9).size()>0){
          data.addValue(medidas.get(9).get(medidas.get(9).size() - 1).calcularImc(), "Paciente", "Oct");
          }else if(medidas.get(9).size()<=0 && medidas.get(10).size()>0){
              data.addValue(0.0, "Paciente", "Oct");
          }else{
              data.addValue(null, "Paciente", "Oct");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(10).get(medidas.get(10).size() - 1).calcularImc(), "Paciente", "Nov");
          }else if(medidas.get(10).size()<=0 && medidas.get(11).size()>0){
              data.addValue(0.0, "Paciente", "Nov");
          }else{
              data.addValue(null, "Paciente", "Nov");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(11).get(medidas.get(11).size() - 1).calcularImc(), "Paciente", "Dec");
          }else{
               data.addValue(null, "Paciente", "Dec");
          }
          linea = ChartFactory.createLineChart("Gráfico que muestra el IMC del paciente en un año","Meses", "Imc", data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }
       
       
        /**
       * Nos inserta en la label que le pasemos, como parametro, una grafrica con el imc del ultimo trimestre
       * Donde prima el ultimo peso del mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void graficaImcsUltimoTrimestreGrasa(JPanel panel,JLabel label,String dni){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidasNormales>> medidas =  borrarMesesNull(getMedidasNormalesOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercalados(op.getMedidasNormalesPaciente(dni))));
          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
          Date fechaActual = new Date();
          Calendar actual = new GregorianCalendar();
          actual.setTime(fechaActual);
          if (actual.get(Calendar.MONTH) < 2) {
              for (int i = 0; i <= actual.get(Calendar.MONTH); i++) {
                  if (medidas.get(i).size() > 0) {
                      for (int x = 0; x < medidas.get(i).size(); x++) {
                          data.addValue(medidas.get(i).get(x).calcularImc(), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                      }
                  } else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  }else {
                      data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          } else {
              for (int i = actual.get(Calendar.MONTH) - 2; i <= actual.get(Calendar.MONTH); i++) {
                  if (medidas.get(i).size() > 0) {
                      for (int x = 0; x < medidas.get(i).size(); x++) {
                          Calendar aux = new GregorianCalendar();
                          aux.setTime(medidas.get(i).get(x).getFecha());
                          
                          data.addValue(medidas.get(i).get(x).calcularImc(), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                          
                      }
                  } else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  }else {
                      data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          }
          linea = ChartFactory.createLineChart("Gráfico IMC último trimestre","Meses", "Imc",  data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }
       
       /*Metodo privado que usamos para sacar el mes por medio de la posición de el array de meses*/
       private static String obtenerMesPosicion(int posicion){
           switch(posicion){
               case 0:
                   return "Ene";
               case 1:
                   return "Feb";
               case 2:
                   return "Mar";
               case 3:
                   return "Abr";
               case 4:
                   return "May";
               case 5:
                   return "Jun";
               case 6:
                   return  "Jul";
               case 7:
                   return "Ago";
               case 8:
                   return "Sep";
               case 9:
                   return "Oct";
               case 10:
                   return "Nov";
               case 11:
                   return "Dec";
                   
           }
           return null;
       }
       
       
       ///Parte metodos grafica Medidas pliegue
       
       
        /**
       * Metodo que le pasamos una lista con todas las medidaPliegue de un paciente y nos las ordena por meses
       * @param medidas
       * @return 
       */
      public static List<List<MedidaPliegue>> getMedidasPliegueOrdenadasMeses(List<MedidaPliegue> medidas){
          
          List<List<MedidaPliegue>> meses = new ArrayList<>();
          List<MedidaPliegue> enero = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> febrero = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> marzo = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> abril = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> mayo = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> junio = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> julio = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> agosto = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> septiembre = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> octubre = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> noviembre = new ArrayList<MedidaPliegue>();
          List<MedidaPliegue> diciembre = new ArrayList<MedidaPliegue>();

          for (MedidaPliegue medida : medidas) {
              Calendar calendario = new GregorianCalendar();
              calendario.setTime(medida.getFecha());
              switch (calendario.get(Calendar.MONTH)) {
                  case Calendar.JANUARY:
                      enero.add(medida);
                      break;
                  case Calendar.FEBRUARY:
                      febrero.add(medida);
                      break;
                  case Calendar.MARCH:
                      marzo.add(medida);
                      break;
                  case Calendar.APRIL:
                      abril.add(medida);
                      break;
                  case Calendar.MAY:
                      mayo.add(medida);
                      break;
                  case Calendar.JUNE:
                      junio.add(medida);
                      break;
                  case Calendar.JULY:
                      julio.add(medida);
                      break;
                  case Calendar.AUGUST:
                      agosto.add(medida);
                      break;
                  case Calendar.SEPTEMBER:
                      septiembre.add(medida);
                      break;
                  case Calendar.OCTOBER:
                      octubre.add(medida);
                      break;
                  case Calendar.NOVEMBER:
                      noviembre.add(medida);
                      break;
                  case Calendar.DECEMBER:
                      diciembre.add(medida);
                      break;

              }
          }
          meses.add(enero);
          meses.add(febrero);
          meses.add(marzo);
          meses.add(abril);
          meses.add(mayo);
          meses.add(junio);
          meses.add(julio);
          meses.add(agosto);
          meses.add(septiembre);
          meses.add(octubre);
          meses.add(noviembre);
          meses.add(diciembre);
         
        return meses;
          
      }
      /**
       * Metodo que nos borra los meses null de una lista con listas de medidas de pliegue
       * @param medidas
       * @return Lista con listas de los meses con medidas pliegue
       */
      public static List<List<MedidaPliegue>> borrarMesesNullPliegue(List<List<MedidaPliegue>> medidas){
           List<List<MedidaPliegue>> aux= new ArrayList<>();
           
           for(int i=0; i< medidas.size();i++){
               if(medidas.get(i).size()==0&&i>0){
                   aux.add(medidas.get(i-1));
               }else{
                   aux.add(medidas.get(i));
               }
           }
           
           return aux;
       }     
      
      /**
       * Nos inserta en La label que le pasemos una grafica con el primer porcentaje de grasa del año, y el ultimo porcenajte de gras del año de cada mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void GraficaMedidasPligueGrasa(JPanel panel,JLabel label,String dni,String sexo){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
          List<List<MedidaPliegue>> medidas = borrarMesesNullPliegue(getMedidasPliegueOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercaladosPliegue(op.getMedidasPlieguePaciente(dni))));

         
        
          
          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
          if(medidas.get(0).size()>0){
          data.addValue(medidas.get(0).get(0).porcentajeGrasa(sexo), "Paciente", "Ene");
          }else if(medidas.get(0).size()<=0 && medidas.get(1).size()>0){
              data.addValue(0.0, "Paciente", "Ene");
          }
          else{
              data.addValue(null, "Paciente", "Ene");
          }
          if(medidas.get(1).size()>0){
          data.addValue(medidas.get(1).get(medidas.get(1).size() - 1).porcentajeGrasa(sexo), "Paciente", "Feb");
          }else if(medidas.get(1).size()<=0 && medidas.get(2).size()>0){
              data.addValue(0.0, "Paciente", "Feb");
          }
          else{
               data.addValue(null, "Paciente", "Feb");
          }
          if(medidas.get(2).size()>0){
          data.addValue(medidas.get(2).get(medidas.get(2).size() - 1).porcentajeGrasa(sexo), "Paciente", "Mar");
          }else if(medidas.get(2).size()<=0 && medidas.get(3).size()>0){
              data.addValue(0.0, "Paciente", "Mar");
          }else{
              data.addValue(null, "Paciente", "Mar");
          }
          if(medidas.get(3).size()>0){
          data.addValue(medidas.get(3).get(medidas.get(3).size() - 1).porcentajeGrasa(sexo), "Paciente", "Abr");
          }else if(medidas.get(3).size()<=0 && medidas.get(4).size()>0){
              data.addValue(0.0, "Paciente", "Abr");
          }else{
              data.addValue(null, "Paciente", "Abr");
          }
          if(medidas.get(4).size()>0){
          data.addValue(medidas.get(4).get(medidas.get(4).size() - 1).porcentajeGrasa(sexo), "Paciente", "May");
          }else if(medidas.get(4).size()<=0 && medidas.get(5).size()>0){
              data.addValue(0.0, "Paciente", "May");
          }
          else{
              data.addValue(null, "Paciente", "May");
          }
          if(medidas.get(5).size()>0){
          data.addValue(medidas.get(5).get(medidas.get(5).size() - 1).porcentajeGrasa(sexo), "Paciente", "Jun");
          }else if(medidas.get(5).size()<=0 && medidas.get(6).size()>0){
              data.addValue(0.0, "Paciente", "Jun");
          }else{
              data.addValue(null, "Paciente", "Jun");
          }
          if(medidas.get(6).size()>0){
          data.addValue(medidas.get(6).get(medidas.get(6).size() - 1).porcentajeGrasa(sexo), "Paciente", "Jul");
          }else if(medidas.get(6).size()<=0 && medidas.get(7).size()>0){
              data.addValue(0.0, "Paciente", "Jul");
          }else{
              data.addValue(null, "Paciente", "Jul");
          }
          if(medidas.get(7).size()>0){
          data.addValue(medidas.get(7).get(medidas.get(7).size() - 1).porcentajeGrasa(sexo), "Paciente", "Ago");
          }else if(medidas.get(7).size()<=0 && medidas.get(8).size()>0){
              data.addValue(0.0, "Paciente", "Ago");
          }else{
              data.addValue(null, "Paciente", "Ago");
          }if(medidas.get(8).size()>0){
          data.addValue(medidas.get(8).get(medidas.get(8).size() - 1).porcentajeGrasa(sexo), "Paciente", "Sep");
          }else if(medidas.get(8).size()<=0 && medidas.get(9).size()>0){
              data.addValue(0.0, "Paciente", "Sep");
          }else{
              data.addValue(null, "Paciente", "Sep");
          }
          if(medidas.get(9).size()>0){
          data.addValue(medidas.get(9).get(medidas.get(9).size() - 1).porcentajeGrasa(sexo), "Paciente", "Oct");
          }else if(medidas.get(9).size()<=0 && medidas.get(10).size()>0){
              data.addValue(0.0, "Paciente", "Oct");
          }else{
              data.addValue(null, "Paciente", "Oct");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(10).get(medidas.get(10).size() - 1).porcentajeGrasa(sexo), "Paciente", "Nov");
          }else if(medidas.get(10).size()<=0 && medidas.get(11).size()>0){
              data.addValue(0.0, "Paciente", "Nov");
          }else{
              data.addValue(null, "Paciente", "Nov");
          }
          if(medidas.get(10).size()>0){
          data.addValue(medidas.get(11).get(medidas.get(11).size() - 1).porcentajeGrasa(sexo), "Paciente", "Dec");
          }else{
               data.addValue(null, "Paciente", "Dec");
          }
          linea = ChartFactory.createLineChart("Gráfico porcentaje grasa en un año","Meses", "Porcentaje Grasa",  data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }
      
      
      /**
       * Nos inserta en la label que le pasemos, como parametro, una grafrica con la el porcentaje de grasa del ultimo trimestre
       * Donde prima el ultimo porcentaje de grasa del mes
       * @param panel
       * @param label
       * @param dni 
       */
      public static void graficaMedidasPliegueUltimoTrimestrePorcentajeGrasa(JPanel panel,JLabel label,String dni,String sexo){
          OperacionesBaseDatos op = new OperacionesBaseDatos();
           List<List<MedidaPliegue>> medidas = borrarMesesNullPliegue(getMedidasPliegueOrdenadasMeses(staticas.MetodosUtiles.elimarCerosIntercaladosPliegue(op.getMedidasPlieguePaciente(dni))));
          JFreeChart linea;
          DefaultCategoryDataset data = new DefaultCategoryDataset();
          Date fechaActual = new Date();
          Calendar actual = new GregorianCalendar();
          actual.setTime(fechaActual);
          if (actual.get(Calendar.MONTH) < 2) {
              for (int i = 0; i <= actual.get(Calendar.MONTH); i++) {
                  if (medidas.get(i).size() > 0) {
                      for (int x = 0; x < medidas.get(i).size(); x++) {
                          data.addValue(medidas.get(i).get(x).porcentajeGrasa(sexo), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                      }
                  }else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  } else {
                      data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          } else {
              for (int i = actual.get(Calendar.MONTH) - 2; i <= actual.get(Calendar.MONTH); i++) {
                  if (medidas.get(i).size() > 0) {
                      for (int x = 0; x < medidas.get(i).size(); x++) {
                          Calendar aux = new GregorianCalendar();
                          aux.setTime(medidas.get(i).get(x).getFecha());
                          
                          data.addValue(medidas.get(i).get(x).porcentajeGrasa(sexo), "Paciente", getMes(medidas.get(i).get(x).getFecha()));
                          
                      }
                  } else if(medidas.get(i).size()==0&&i==actual.get(Calendar.MONTH)){
                       data.addValue(null, "Paciente", obtenerMesPosicion(i));
                  }else {
                      data.addValue(0.0, "Paciente", obtenerMesPosicion(i));
                  }
              }
          }
          linea = ChartFactory.createLineChart("Gráfico porcentaje grasa trimestre","Porcentaje Grasa", "Meses", data, PlotOrientation.VERTICAL, true, true, true);
          BufferedImage graficoLinea = linea.createBufferedImage(panel.getWidth(), panel.getHeight());
          label.setSize(panel.getSize());
          label.setIcon(new ImageIcon(graficoLinea));
          panel.updateUI();
      }
}
     