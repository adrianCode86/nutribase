/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import UpperEssential.UpperEssentialLookAndFeel;
import auxiliares.*;
import auxiliares.TablaModeloNoEdit;
import baseDatos.OperacionesBaseDatos;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumnModel;
import modelo.Paciente;
import modelo.PacienteTabla;

/**
 *
 * @author agg
 */
public class TablaPacientes extends javax.swing.JDialog {

    private List<PacienteTabla> pacientes;
    
    private  DatosPacienteDialogo dialogo;
    private OperacionesBaseDatos op;
    
    private java.awt.Frame parent;
    /**
     * Creates new form TablaPacientes
     */
    public TablaPacientes(java.awt.Frame parent, boolean modal) throws UnsupportedLookAndFeelException, IOException {
        super(parent, modal);
        this.parent=parent;
         UIManager.setLookAndFeel(new UpperEssentialLookAndFeel());
        initComponents();
        
        this.setLocationRelativeTo(null);
        op= new OperacionesBaseDatos();
        dialogo=new DatosPacienteDialogo(parent, modal,this);
        this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
        recargarTabla();
        
    }

    public  void recargarTabla(){
         try {
            tabla.setDefaultRenderer(Object.class, new TablaImagen());
            OperacionesBaseDatos op=new OperacionesBaseDatos();
            pacientes=op.obtenerPacienteTabla();
            TablaModeloNoEdit model=new TablaModeloNoEdit();
            model.addColumn("");
            model.addColumn("Nombre");
            model.addColumn("Apellidos");
            
            for(PacienteTabla pa:pacientes){
                model.addRow(pa.toObject());
            }
            
            tabla.setModel(model);
            tabla.setRowHeight(64);
            TableColumnModel col=tabla.getColumnModel();
            col.getColumn(0).setPreferredWidth(15);
        } catch (IOException ex) {
             System.out.println(ex.getMessage());
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        menuTabla = new javax.swing.JPopupMenu();
        menuBorrar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        Editar = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Insertar = new javax.swing.JMenuItem();

        menuBorrar.setText("Borrar");
        menuBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBorrarActionPerformed(evt);
            }
        });
        menuTabla.add(menuBorrar);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, jLabel1.getFont().getSize()+18));
        jLabel1.setText("LISTA DE PACIENTES");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setComponentPopupMenu(menuTabla);
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        tabla.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tablaPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(406, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap(382, Short.MAX_VALUE))
            .addComponent(jSeparator3)
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
        );

        jMenu1.setText("ALTA");

        Insertar.setText("Alta paciente");
        Insertar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InsertarActionPerformed(evt);
            }
        });
        jMenu1.add(Insertar);

        Editar.add(jMenu1);

        setJMenuBar(Editar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InsertarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InsertarActionPerformed
        try {
            dialogo=new DatosPacienteDialogo(parent, rootPaneCheckingEnabled,this);
            dialogo.setVisible(rootPaneCheckingEnabled);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TablaPacientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_InsertarActionPerformed

    private void tablaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tablaPropertyChange
        
            
    }//GEN-LAST:event_tablaPropertyChange

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
       int posicion=tabla.getSelectedRow();
       if(evt.getClickCount()==2){
       if(posicion !=-1){
          
           Paciente  paciente=op.getPaciente(pacientes.get(posicion).getDni());
           
           VistaPacienteGeneral  vista;
           try {
               
               vista = new VistaPacienteGeneral(parent, rootPaneCheckingEnabled, paciente);
               vista.setVisible(rootPaneCheckingEnabled);
               this.dispose();
               
           } catch (UnsupportedLookAndFeelException ex) {
               Logger.getLogger(TablaPacientes.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(TablaPacientes.class.getName()).log(Level.SEVERE, null, ex);
           }
           
        }
       }
    }//GEN-LAST:event_tablaMouseClicked

    private void menuBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBorrarActionPerformed
        int posicion=tabla.getSelectedRow();
        if(posicion != -1){
           Paciente  paciente=op.getPaciente(pacientes.get(posicion).getDni());
           int confirmar=JOptionPane.showConfirmDialog(null, "¿Está seguro de borrar a "+paciente.getNombre()+"?");
           if(confirmar==0){
           op.borrarPaciente(paciente);
           }
           recargarTabla();
        }
    }//GEN-LAST:event_menuBorrarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
     
     
    
     System.exit(0);
     
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TablaPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TablaPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TablaPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TablaPacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                TablaPacientes dialog = null;
                try {
                    dialog = new TablaPacientes(new javax.swing.JFrame(), true);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(TablaPacientes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(TablaPacientes.class.getName()).log(Level.SEVERE, null, ex);
                }
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar Editar;
    private javax.swing.JMenuItem Insertar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JMenuItem menuBorrar;
    private javax.swing.JPopupMenu menuTabla;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
