/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ireport;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import com.sun.org.apache.xerces.internal.impl.PropertyManager;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.MedidasNormales;
import modelo.MedidasReport;
import modelo.Paciente;
import modelo.PacienteReport;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *Esta clase tiene los metodos necesarios para generar un reporte
 * @author agg
 */
public class ManejoDatosPaciente {
    
    /**
     * Esta clase convierte un objeto paciente al objeto PacienteReport necesario
     * para generar un reporte del paciente
     * @param aux
     * @return 
     */
    public static PacienteReport convertirPaciente(Paciente aux){
        PacienteReport paciente= new PacienteReport();
        
        paciente.setApellidos(aux.getApellidos());
        paciente.setDireccion(aux.getDireccion());
        paciente.setDni(aux.getDni());
        paciente.setEdad(aux.getEdad());
        paciente.setEmail(aux.getEmail());
        paciente.setNombre(aux.getNombre());
        paciente.setTelefono(aux.getNumeroTelefono());
        
        //float peso, float imc, float pecho, float cintura, float cadera, float muslo
        for(MedidasNormales medida:aux.getMedidasNormales()){
            SimpleDateFormat sm=new SimpleDateFormat("dd/MM/YYYY");
            paciente.addMedida(new MedidasReport(sm.format(medida.getFecha()),medida.getPeso(),medida.calcularImc(),medida.getMedidaPecho(),medida.getMedidaCintura(),medida.getMedidaCadera(),medida.getMedidaMuslo()));
        }
        
        return paciente;
    }
    /**
     * Este metodo genera un informe con los datos del paciente y la ruta pasada como InputStream
     * @param paciente
     * @param parent
     * @param ruta
     * @throws JRException
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void generarReporte(Paciente paciente,Frame parent,InputStream ruta) throws JRException,FileNotFoundException, IOException{
        List<PacienteReport> aux = new ArrayList<>();
        aux.add(convertirPaciente(paciente));
        JRDataSource dataSource = new JRBeanCollectionDataSource(aux);
        Map parametros = new HashMap();
       
        String directorio;
        JFileChooser filechooser=new  JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF", "pdf");
        filechooser.setFileFilter(filter);
        int seleccion=filechooser.showSaveDialog(parent);
        if(seleccion==JFileChooser.APPROVE_OPTION){
            
            File fichero = filechooser.getSelectedFile();
            directorio=fichero.getAbsolutePath();
           
            
            JasperPrint print = JasperFillManager.fillReport(ruta, parametros,dataSource);
            JasperExportManager.exportReportToPdfFile(print, directorio+".pdf");
            JOptionPane.showMessageDialog(parent, "Guardado correctamente");
            
        }else{
            JOptionPane.showMessageDialog(parent, "Fallo al exportar");
        }
        
        
        
    }
    
}
