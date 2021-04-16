/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Marina
 */
public class MainFrame extends JFrame {
    
    private Tablero mTablero = new Tablero();
    
    
    public MainFrame() {
        try {
            jbInit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
        
        //Se configura el diseño y se pone el diseño del borde
        this.setLayout(new BorderLayout());
        
        //Se obtiene el panel del contenido con diseño null (usa coordenadas)
        this.getContentPane().setLayout(null);
        
        //Se asigna el tamaño del formulario
        this.setSize(new Dimension(400, 300));
        
        //Se establece la operacion de cierre por defecto
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Se agrega el tablero al formulario y se pone que ocupe todo el espacio
        this.add(mTablero, BorderLayout.CENTER);
        
        //Se asigna numero de filas del tablero
        mTablero.setNumeroDeFilas(8);
        
        //Se asigna numero de columnas del tablero
        mTablero.setNumeroDeColumnas(4);
        
        //Se inicializa el tablero
        mTablero.inicializar();
        
        //Se asigna el tamaño al tablero
        mTablero.setSize(200, 200);
        
        mTablero.ordenar();
        
    }
}
