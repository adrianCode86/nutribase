/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auxiliares;

import javax.swing.table.DefaultTableModel;

/**
 *Esta clase se usara pa genenera un mode de un jtable que sus celdas no sean editables
 * @author agg
 */
public class TablaModeloNoEdit extends DefaultTableModel {
      /**
     * Define la posibilidad de editar de una columna.
     */
    private final boolean [] tableColums = {false, false, false,false};
     
    @Override
    public final boolean isCellEditable(int row, int column) {
        return this.tableColums[column];
    }
}
