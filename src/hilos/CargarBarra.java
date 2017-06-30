/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JProgressBar;

/**
 *Este Hilo nos permite cargar una barra y cuando termine de cargar
 * pone visibles los dos objetos alta y ver.
 * @author agg
 */
public class CargarBarra extends Thread {
    
    private JProgressBar barra;
    private JButton alta,ver;

    public CargarBarra(JProgressBar barra,JButton alta,JButton ver) {
        super();
        this.barra = barra;
        this.alta=alta;
        this.ver=ver;
    }
    @Override
    public void run(){
        for(int i=0;i<100;i++){
            barra.setValue(i);
            pausa(30);
        }
        ver.setVisible(true);
        alta.setVisible(true);
        barra.setVisible(false);
        
    }
    
    public void pausa(int mils){
        try {
            Thread.sleep(mils);
        } catch (InterruptedException ex) {
            Logger.getLogger(CargarBarra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
