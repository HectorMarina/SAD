/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projecte;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Marina
 */
public class Tablero extends JPanel implements ComponentListener, ActionListener {
    
    private JButton[][] mCasillas = null;
    private int mNumeroDeFilas;
    private int mNumeroDeColumnas;
    
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
        
        //Se conectan los eventos del panel con el mismo panel
        this.addComponentListener(this);
        
    }
    
    @Override
    public void componentResized(ComponentEvent e) {
        
        //Se invoca al metodo que acomoda los botones en el tablero
        this.ordenar();
        
    }

    @Override
    public void componentMoved(ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentShown(ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void componentHidden(ComponentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        //Se comprueba que el que ha causado el evento es de tipo JButton
        if(e.getSource() instanceof JButton) {
            
            //Se obtiene una referencia del objeto que ha causado el evento
            JButton temp = (JButton) e.getSource();
            
            //Se asigna color verde de fondo al boton pulsado
            temp.setBackground(Color.GREEN);
        }
        
    }
    
    public void setNumeroDeFilas(int mNumeroDeFilas) {
        this.mNumeroDeFilas = mNumeroDeFilas;
    }
    
    public void setNumeroDeColumnas(int mNumeroDeColumnas) {
        this.mNumeroDeColumnas = mNumeroDeColumnas;
    }
}
