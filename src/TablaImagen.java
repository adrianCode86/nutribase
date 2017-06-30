/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


/**
 *Este clase nos permite , que el label de la tabla pueda mostrar la imagen
 * @author agg
 */
public class TablaImagen extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        
        
        if(o instanceof JLabel){
            JLabel label=(JLabel)o;
            return label;
        }
        
        return super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1); 
    }
    
   
}
