/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import UpperEssential.UpperEssentialLookAndFeel;
import baseDatos.OperacionesBaseDatos;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.Paciente;

/**
 *
 * @author agg
 */
public class DatosPacienteDialogo extends javax.swing.JDialog {

    private boolean actualizar;
    private boolean foto=false;
    private Paciente paciente;
    private File archivo;
    private TablaPacientes tabla;
    private JFrame parent;
    
    /**
     * Creates new form DatosPacienteDialogo
     */
    public DatosPacienteDialogo(java.awt.Frame parent, boolean modal) throws UnsupportedLookAndFeelException, ParseException, IOException {
        super(parent, modal);
        this.paciente=new Paciente();
         UIManager.setLookAndFeel(new UpperEssentialLookAndFeel());
         initComponents();
         this.setLocationRelativeTo(null);
         this.setResizable(false);
         embarazada.setVisible(true);
         //ponemos el jspinner en formato de dia mes año
        Date datenow = Calendar.getInstance().getTime();
        SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
        fechaNacimiento.setModel(smb);
        JSpinner.DateEditor d = new JSpinner.DateEditor(fechaNacimiento, "dd-MMM-yyyy");
        fechaNacimiento.setEditor(d);
        actualizar=false;
        URL rutaDefecto=parent.getClass().getResource("/recursos/defecto.jpg");
        labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefecto));
        this.parent=(JFrame) parent;
        rellenarCampos();
         
    }
    public DatosPacienteDialogo(java.awt.Frame parent, boolean modal,TablaPacientes tabla) throws UnsupportedLookAndFeelException {
        super(parent, modal);
        this.paciente=new Paciente();
         UIManager.setLookAndFeel(new UpperEssentialLookAndFeel());
         initComponents();
         this.setLocationRelativeTo(null);
         this.setResizable(false);
         embarazada.setVisible(true);
         this.tabla=tabla;
         menuEditar.setVisible(false);
         //ponemos el jspinner en formato de dia mes año
        Date datenow = Calendar.getInstance().getTime();
        SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
        fechaNacimiento.setModel(smb);
        JSpinner.DateEditor d = new JSpinner.DateEditor(fechaNacimiento, "dd-MMM-yyyy");
        fechaNacimiento.setEditor(d);
        actualizar=false;
        URL rutaDefecto=parent.getClass().getResource("/recursos/defecto.jpg");
        labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefecto));
          this.parent=(JFrame) parent;
    }

     public DatosPacienteDialogo(java.awt.Frame parent, boolean modal,Paciente paciente) throws UnsupportedLookAndFeelException, ParseException, IOException {
        super(parent, modal);
         UIManager.setLookAndFeel(new UpperEssentialLookAndFeel());
         initComponents();
         this.setLocationRelativeTo(null);
         this.setResizable(false);
         embarazada.setVisible(false);
         //ponemos el jspinner en formato de dia mes año
        Date datenow = Calendar.getInstance().getTime();
        SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
        fechaNacimiento.setModel(smb);
        JSpinner.DateEditor d = new JSpinner.DateEditor(fechaNacimiento, "dd-MMM-yyyy");
        fechaNacimiento.setEditor(d);
        actualizar=true;
        this.paciente=paciente;
        URL rutaDefecto=parent.getClass().getResource("/recursos/defecto.jpg");
        labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefecto));
        rellenarCampos();
         this.parent=(JFrame) parent;
    }
     
      public DatosPacienteDialogo(java.awt.Frame parent, boolean modal,Paciente paciente,TablaPacientes tabla) throws UnsupportedLookAndFeelException, ParseException, IOException {
        super(parent, modal);
         UIManager.setLookAndFeel(new UpperEssentialLookAndFeel());
         initComponents();
         this.setLocationRelativeTo(null);
         this.setResizable(false);
         embarazada.setVisible(false);
         this.tabla=tabla;
         //ponemos el jspinner en formato de dia mes año
        Date datenow = Calendar.getInstance().getTime();
        SpinnerDateModel smb = new SpinnerDateModel(datenow, null, null, Calendar.HOUR_OF_DAY);
        fechaNacimiento.setModel(smb);
        JSpinner.DateEditor d = new JSpinner.DateEditor(fechaNacimiento, "dd-MMM-yyyy");
        fechaNacimiento.setEditor(d);
        actualizar=true;
        this.paciente=paciente;
        URL rutaDefecto=parent.getClass().getResource("/recursos/defecto.jpg");
        labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefecto));
        rellenarCampos();
         this.parent=(JFrame) parent;
    }
     private void rellenarCampos() throws ParseException, IOException{
         
         if(paciente !=null&&actualizar){
             textNombre.setText(paciente.getNombre());
             textApellidos.setText(paciente.getApellidos());
             textDireccion.setText(paciente.getDireccion());
             textDni.setText(paciente.getDni());
             textDni.setEditable(false);
             textEmail.setText(paciente.getEmail());
             textTelefono.setText(String.valueOf(paciente.getNumeroTelefono()));
             sexo.setSelectedItem(paciente.getSexo());
             fechaNacimiento.setValue(staticas.MetodosUtiles.convertirFechaModeloSpinner(paciente.getFechaNacimiento()));
             if(paciente.getSexo().equalsIgnoreCase("MUJER")){
                 embarazada.setVisible(true);
                 embarazada.setSelected(paciente.isEmbarazada());
                 embarazada.setEnabled(false);
             }else{
                  embarazada.setVisible(false);
                 embarazada.setSelected(paciente.isEmbarazada());
                 embarazada.setEnabled(false);
             }
             if(paciente.getFoto()!=null){
                 labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagen(paciente.getFoto()));
                 
             }
             
             textNombre.setEditable(false);
             textApellidos.setEditable(false);
             textDireccion.setEditable(false);
             textEmail.setEditable(false);
             textTelefono.setEditable(false);
             textEmail.setEditable(false);
             sexo.setEnabled(false);
             fechaNacimiento.setEnabled(false);
             botonAceptar.setVisible(false);
             botonCancelar.setVisible(false);
             botonImagen.setVisible(false);
         }else{
             jMenuBar1.setVisible(false);
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

        jSeparator3 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        textNombre = new javax.swing.JTextField();
        textApellidos = new javax.swing.JTextField();
        textDni = new javax.swing.JTextField();
        textEmail = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textTelefono = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        labelRutaImagen = new javax.swing.JLabel();
        botonImagen = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDireccion = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        sexo = new javax.swing.JComboBox<>();
        embarazada = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        fechaNacimiento = new javax.swing.JSpinner();
        botonAceptar = new javax.swing.JButton();
        jSeparator9 = new javax.swing.JSeparator();
        botonCancelar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuEditar = new javax.swing.JMenu();
        menuActulizar = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setFont(jLabel1.getFont().deriveFont(jLabel1.getFont().getStyle() | java.awt.Font.BOLD, 18));
        jLabel1.setText("DATOS PACIENTE");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel2.setText("Nombre");

        jLabel3.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel3.setText("Apellidos");

        jLabel4.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel4.setText("DNI");

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel5.setText("Email");

        textNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textNombreKeyTyped(evt);
            }
        });

        textApellidos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textApellidosKeyTyped(evt);
            }
        });

        textDni.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textDniFocusLost(evt);
            }
        });
        textDni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textDniKeyTyped(evt);
            }
        });

        textEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textEmailFocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel8.setText("Teléfono");

        textTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textTelefonoKeyTyped(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        labelRutaImagen.setBorder(new javax.swing.border.MatteBorder(null));

        botonImagen.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        botonImagen.setText("Imagen...");
        botonImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonImagenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(labelRutaImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(botonImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelRutaImagen, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(botonImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNombre)
                            .addComponent(textApellidos)
                            .addComponent(textDni)
                            .addComponent(textEmail)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(15, 15, 15)
                        .addComponent(textTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel7.setText("Dirección");

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        textDireccion.setColumns(20);
        textDireccion.setLineWrap(true);
        textDireccion.setRows(5);
        textDireccion.setWrapStyleWord(true);
        jScrollPane1.setViewportView(textDireccion);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel6.setText("Sexo");

        sexo.setFont(sexo.getFont().deriveFont((float)12));
        sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MUJER", "HOMBRE" }));
        sexo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                sexoItemStateChanged(evt);
            }
        });

        embarazada.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        embarazada.setText("Embarazada");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator5)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(sexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(embarazada, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel6)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(embarazada)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        jLabel9.setText("Fecha de nacimiento");

        fechaNacimiento.setFont(new java.awt.Font("Ubuntu", 0, 12)); // NOI18N
        fechaNacimiento.setModel(new javax.swing.SpinnerDateModel());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator6)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel9)
                .addGap(0, 46, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fechaNacimiento)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(fechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        botonAceptar.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        botonAceptar.setText("ACEPTAR");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        jSeparator9.setOrientation(javax.swing.SwingConstants.VERTICAL);

        botonCancelar.setFont(new java.awt.Font("Ubuntu", 1, 12)); // NOI18N
        botonCancelar.setText("CANCELAR");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(22, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(321, 321, 321)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator9)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        menuEditar.setText("EDITAR");

        menuActulizar.setText("ACTUALIZAR");
        menuActulizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActulizarActionPerformed(evt);
            }
        });
        menuEditar.add(menuActulizar);

        jMenuBar1.add(menuEditar);

        setJMenuBar(jMenuBar1);

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

    private void botonImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonImagenActionPerformed
        JFileChooser escojerFoto=new JFileChooser();
         escojerFoto.addChoosableFileFilter(new FileNameExtensionFilter("JPG", "jpg"));
         escojerFoto.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
        int ap = escojerFoto.showOpenDialog(this);

        if(ap == JFileChooser.APPROVE_OPTION){
            archivo=escojerFoto.getSelectedFile();
            try {
                labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagen(Files.readAllBytes(archivo.toPath())));
                foto=true;
            } catch (IOException ex) {
                Logger.getLogger(DatosPacienteDialogo.class.getName()).log(Level.SEVERE, null, ex);
            }
            labelRutaImagen.setVisible(true);
           
        }

    }//GEN-LAST:event_botonImagenActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        String nombre=textNombre.getText();
        String apellido=textApellidos.getText();
        String email=textEmail.getText();
        String direccion= textDireccion.getText();
        String dni=textDni.getText();
        String sex= sexo.getSelectedItem().toString();
        String telefono=textTelefono.getText();
        Date fecha=(Date) fechaNacimiento.getValue();

        if(paciente==null){
            paciente=new Paciente();
        }
        if(!nombre.equalsIgnoreCase("")){
            paciente.setNombre(nombre);

        }
        if(!apellido.equalsIgnoreCase("")){
            paciente.setApellidos(apellido);
        }

        if(!direccion.equalsIgnoreCase("")){
            paciente.setDireccion(direccion);
        }
        if(!dni.equalsIgnoreCase("")){
            paciente.setDni(dni);
        }
        if(!email.equalsIgnoreCase("")){
            paciente.setEmail(email);
        }
        if(!sex.equalsIgnoreCase("")){
            paciente.setSexo(sex);
        }
        if(!telefono.equalsIgnoreCase("")){
            paciente.setNumeroTelefono(Integer.parseInt(telefono));
        }
        if(fecha!=null){
            paciente.setFechaNacimiento(fecha);
        }
        if(embarazada.isVisible()){
            paciente.setEmbarazada(embarazada.isSelected());
        }

        if(archivo!=null){
            try {
                paciente.setFoto(staticas.MetodosUtiles.getArrayImagen(archivo));
            } catch (IOException ex) {
                Logger.getLogger(DatosPacienteDialogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(archivo==null && sex.equalsIgnoreCase("MUJER")){
            URL rutaDefecto=this.getClass().getResource("/recursos/defecto.jpg");
            labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefecto));
            File file = null;
            try {
                
                
                byte[] bytes = null;
                InputStream in=parent.getClass().getResourceAsStream("/recursos/defecto.jpg");
                bytes =  staticas.MetodosUtiles.toByteArrayUsingJava(in);
                paciente.setFoto(bytes);
                labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefecto));

            } catch (IOException ex) {
                Logger.getLogger(DatosPacienteDialogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            URL rutaDefectoHombre = parent.getClass().getResource("/recursos/defectoHombre.jpg");
            File file = null;
            try {
                
                
                byte[] bytes = null;
                InputStream in=parent.getClass().getResourceAsStream("/recursos/defectoHombre.jpg");
                bytes =  staticas.MetodosUtiles.toByteArrayUsingJava(in);
                paciente.setFoto(bytes);
                labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefectoHombre));

            } catch (IOException ex) {
                Logger.getLogger(DatosPacienteDialogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        OperacionesBaseDatos op=new OperacionesBaseDatos();
        if(actualizar){
            op.actulizarPaciente(paciente);
        }else{
            op.insertarPaciente(paciente);
        }
       if(tabla!=null){
           tabla.recargarTabla();
       }
        this.dispose();

    }//GEN-LAST:event_botonAceptarActionPerformed

    private void sexoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_sexoItemStateChanged
        String aux=sexo.getSelectedItem().toString();
        
        if(aux.equalsIgnoreCase("HOMBRE")&&!actualizar){
            embarazada.setVisible(false);
            if(!foto){
            URL rutaDefectoHombre = parent.getClass().getResource("/recursos/defectoHombre.jpg");
            File file = null;
            try {

                
                byte[] bytes = null;
                InputStream in=parent.getClass().getResourceAsStream("/recursos/defectoHombre.jpg");
                bytes =  staticas.MetodosUtiles.toByteArrayUsingJava(in);
                paciente.setFoto(bytes);
                labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefectoHombre));

            } catch (IOException ex) {
                Logger.getLogger(DatosPacienteDialogo.class.getName()).log(Level.SEVERE, null, ex);
            }
            }

            
        }else{
            if(!foto){
            URL rutaDefecto=this.getClass().getResource("/recursos/defecto.jpg");
        labelRutaImagen.setIcon(staticas.MetodosUtiles.getImagenFichero(rutaDefecto));}
            embarazada.setVisible(true);
        }
        
    }//GEN-LAST:event_sexoItemStateChanged

    private void textTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textTelefonoKeyTyped
        char c=evt.getKeyChar();
        if(c<'0'||c>'9')evt.consume();
    }//GEN-LAST:event_textTelefonoKeyTyped

    private void textEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textEmailFocusLost
        if(staticas.MetodosUtiles.isEmail(textEmail.getText())){

        }else{
            JOptionPane.showMessageDialog(null, "Email incorrecto","Validar email",JOptionPane.INFORMATION_MESSAGE);
            textEmail.requestFocus();
        }
    }//GEN-LAST:event_textEmailFocusLost

    private void textDniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textDniKeyTyped

        char c=evt.getKeyChar();
        if(c>='a'&&c<='z'){
            evt.setKeyChar(Character.toUpperCase(c));
        }
        if(textDni.getText().length()>9)evt.consume();

    }//GEN-LAST:event_textDniKeyTyped

    private void textDniFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textDniFocusLost
        if(staticas.MetodosUtiles.isDni(textDni.getText())){

        }else{
            JOptionPane.showMessageDialog(null, "DNI incorrecto","Validar DNI",JOptionPane.INFORMATION_MESSAGE);
            textDni.requestFocus();
        }
    }//GEN-LAST:event_textDniFocusLost

    private void textApellidosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textApellidosKeyTyped
        char c=evt.getKeyChar();
        if((c<'a'||c>'z')&&(c<'A'||c>'Z')&&(c!='ñ'&&c!='Ñ')&& (c!=(char)KeyEvent.VK_SPACE))evt.consume();
    }//GEN-LAST:event_textApellidosKeyTyped

    private void textNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textNombreKeyTyped
        char c=evt.getKeyChar();
        if((c<'a'||c>'z')&&(c<'A'||c>'Z')&&(c!='ñ'&&c!='Ñ')&& (c!=(char)KeyEvent.VK_SPACE))evt.consume();
    }//GEN-LAST:event_textNombreKeyTyped

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void menuActulizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActulizarActionPerformed
        
         if(paciente.getSexo().equalsIgnoreCase("MUJER")){
                 
                 embarazada.setEnabled(true);
             }
             
             
             textNombre.setEditable(true);
             textApellidos.setEditable(true);
             textDireccion.setEditable(true);
             textEmail.setEditable(true);
             textTelefono.setEditable(true);
             textEmail.setEditable(true);
             sexo.setEnabled(true);
             fechaNacimiento.setEnabled(true);
             botonAceptar.setVisible(true);
             botonCancelar.setVisible(true);
             botonImagen.setVisible(true);
        
    }//GEN-LAST:event_menuActulizarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       if(tabla!= null){
           tabla.recargarTabla();
       }
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
            java.util.logging.Logger.getLogger(DatosPacienteDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatosPacienteDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatosPacienteDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatosPacienteDialogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DatosPacienteDialogo dialog = null;
                try {
                    dialog = new DatosPacienteDialogo(new javax.swing.JFrame(), true);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(DatosPacienteDialogo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(DatosPacienteDialogo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(DatosPacienteDialogo.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonImagen;
    private javax.swing.JCheckBox embarazada;
    private javax.swing.JSpinner fechaNacimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JLabel labelRutaImagen;
    private javax.swing.JMenuItem menuActulizar;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JComboBox<String> sexo;
    private javax.swing.JTextField textApellidos;
    private javax.swing.JTextArea textDireccion;
    private javax.swing.JTextField textDni;
    private javax.swing.JTextField textEmail;
    private javax.swing.JTextField textNombre;
    private javax.swing.JTextField textTelefono;
    // End of variables declaration//GEN-END:variables
}
