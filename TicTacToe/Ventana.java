package TicTacToe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ventana extends JFrame {

    Menu menu = new Menu();
    Tablero tablero = new Tablero();
    Tablero4 tablero4 = new Tablero4();

    public Ventana() {
        jbInit();
        activarBotones();
    }

    private void jbInit() {
        this.setSize(700, 500);//Establecemos el tamaño de la ventana
        this.setTitle("Tic Tac Toe");//Establecemos el titulo de la ventana
        this.setBackground(Color.BLUE);//Establecemos el fondo de la ventana
        this.setLocationRelativeTo(null);//Ponemos la ventana en el centro de la pantalla
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Hacer que acabe el porgrama cuando cerramos la ventana
        this.add(menu);
        menu.setVisible(true);
    }

    private void activarBotones() {
        menu.start2.addActionListener((ActionEvent e) -> {
            tablero.nombre1 = JOptionPane.showInputDialog("Nombre del jugador X");
            tablero.jugadorX.setText(tablero.nombre1 + "(X): " + tablero.marcadorX);
            tablero.nombre2 = JOptionPane.showInputDialog("Nombre del jugador O");
            tablero.jugadorO.setText(tablero.nombre2 + "(O): " + tablero.marcadorO);
            tablero.partida = Integer.parseInt(JOptionPane.showInputDialog("Número de partidas para ganar"));
            add(tablero);
            menu.setVisible(false);
            tablero.setVisible(true);
            validate();
        });
        menu.start1.addActionListener((ActionEvent e) -> {
            tablero4.nombre1 = JOptionPane.showInputDialog("Nombre del jugador Rojo");
            tablero4.nombre2 = JOptionPane.showInputDialog("Nombre del jugador Amarillo");
            tablero4.partida = Integer.parseInt(JOptionPane.showInputDialog("Número de partidas para ganar"));
            tablero4.jugadorRojo.setText(tablero4.nombre1 + "(R): " + tablero4.marcadorRojo);
            tablero4.jugadorAmarillo.setText(tablero4.nombre2 + "(A): " + tablero4.marcadorAmarillo);
            add(tablero4);
            menu.setVisible(false);
            tablero4.setVisible(true);
            validate();
        });
        menu.exit.addActionListener((ActionEvent e) -> {
            dispose();
        });
        tablero.menu.addActionListener((ActionEvent e) -> {
            tablero.setVisible(false);
            menu.setVisible(true);
            tablero.marcadorX = 0;
            tablero.marcadorO = 0;
            validate();
        });
    }
}
