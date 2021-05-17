/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Marina
 */
public class BarraMenu extends JFrame {
    
    JMenuBar barraMenu = new JMenuBar();
    JMenu archivo = new JMenu("Archivo");
    JMenu ayuda = new JMenu("Ayuda");
    JMenuItem nuevaPartida = new JMenuItem("Nueva Partida");
    JMenuItem salir = new JMenuItem("Salir");
    JMenuItem instrucciones = new JMenuItem("Instrucciones");
    
    public void agregarMenu() {
        barraMenu.add(archivo);
        barraMenu.add(ayuda);
        archivo.add(nuevaPartida);
        archivo.add(salir);
        ayuda.add(instrucciones);
        setJMenuBar(barraMenu);
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
}
