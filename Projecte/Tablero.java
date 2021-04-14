/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Marina
 */
public class Tablero extends JPanel {
    
    private JButton[][] mCasillas = null;
    private int mNumeroDeFilas = 4;
    private int mNumeroDeColumnas = 6;
    
    public Tablero() {
        try {
            jbInit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void inicializar() {
        
        mCasillas = new JButton[mNumeroDeFilas][mNumeroDeColumnas];
        
        for(int fila=0; fila<mNumeroDeFilas; fila++) {
            for(int columna=0; columna<mNumeroDeColumnas; columna++) {
                JButton temp = new JButton();
                temp.setText("(" + fila + ", " + columna + ")");
                
                //Se añade el boton al tablero
                this.add(temp);
                
                //Se asigna a la casilla de la coleccion el boton
                mCasillas[fila][columna] = temp;
            }
        }
    }
    
    public void ordenar() {
        int anchoTotal = this.getWidth();
        int altoTotal = this.getHeight();
        int anchoDeCasilla = anchoTotal / mNumeroDeColumnas;
        int altoDeCasilla = altoTotal / mNumeroDeFilas;
        
        for(int fila=0; fila<mNumeroDeFilas; fila++) {
            for(int columna=0; columna<mNumeroDeColumnas; columna++) {
                
                //Se obtiene una referencia al boton actual
                JButton temp = mCasillas[fila][columna];
                
                //Se fija cada casilla a una posicion y tamaño en funcion de la fila y la columna
                temp.setBounds(columna*anchoDeCasilla, fila*altoDeCasilla, anchoDeCasilla, altoDeCasilla);
                
            }
        }
    }
    
    private void jbInit() throws Exception {
        
        //Se configura el diseño y se pone a null
        this.setLayout(null);
        
        //Se establece el fondo en blanco
        this.setBackground(Color.WHITE);
        
    }
}
