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
        jbInit();//Dimensionamos la ventana
        activarBotones();//Manejamos los botones
    }

    private void jbInit() {
        this.setSize(700, 500);//Establecemos el tamaño de la ventana
        this.setTitle("Tic Tac Toe");//Establecemos el titulo de la ventana
        this.setBackground(Color.BLUE);//Establecemos el fondo de la ventana
        this.setLocationRelativeTo(null);//Ponemos la ventana en el centro de la pantalla
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Hacer que acabe el porgrama cuando cerramos la ventana
        this.add(menu);//Añdimos el menu a la ventana
        menu.setVisible(true);//Mostramos el menu
    }

    private void activarBotones() {
        menu.start2.addActionListener((ActionEvent e) -> {//3 en raya
            tablero.nombre1 = JOptionPane.showInputDialog("Nombre del jugador X");//Pedimos los nombres de los jugadores
            tablero.jugadorX.setText(tablero.nombre1 + "(X): " + tablero.marcadorX);//Ponemos el marcador
            tablero.nombre2 = JOptionPane.showInputDialog("Nombre del jugador O");
            tablero.jugadorO.setText(tablero.nombre2 + "(O): " + tablero.marcadorO);
            tablero.partida = Integer.parseInt(JOptionPane.showInputDialog("Número de partidas para ganar"));//Pedimos el numero de partridas para ganar el campeonato
            add(tablero);//Añadimos el panel tablero a la ventana
            menu.setVisible(false);
            tablero.setVisible(true);//Mostramos el tablero4
            validate();//Efectuamos los cambios
        });
        menu.start1.addActionListener((ActionEvent e) -> {//4 en raya
            tablero4.nombre1 = JOptionPane.showInputDialog("Nombre del jugador Rojo");//Pedimos los nombres de los jugadores
            tablero4.nombre2 = JOptionPane.showInputDialog("Nombre del jugador Amarillo");
            tablero4.partida = Integer.parseInt(JOptionPane.showInputDialog("Número de partidas para ganar"));//Pedimos el numero de partridas para ganar el campeonato
            tablero4.jugadorRojo.setText(tablero4.nombre1 + "(R): " + tablero4.marcadorRojo);//Ponemos el marcador
            tablero4.jugadorAmarillo.setText(tablero4.nombre2 + "(A): " + tablero4.marcadorAmarillo);
            add(tablero4);//Añadimos el panel tablero4 a la ventana
            menu.setVisible(false);
            tablero4.setVisible(true);//Mostramos el tablero4
            validate();//Efectuamos los cambios
        });
        menu.exit.addActionListener((ActionEvent e) -> {
            dispose();
        });
        tablero.menu.addActionListener((ActionEvent e) -> {//Menu del tablero
            tablero.setVisible(false);
            menu.setVisible(true);//Mostramos el menu y reiniciamos los marcadores
            tablero.marcadorX = 0;
            tablero.marcadorO = 0;
            validate();//Efectuamos los cambios
        });
        tablero4.menu.addActionListener((ActionEvent e) -> {//Menu del tablero4
            tablero4.setVisible(false);
            menu.setVisible(true);//Mostramos el menu y reiniciamos los marcadores
            tablero4.marcadorRojo = 0;
            tablero4.marcadorAmarillo = 0;
            validate();//Efectuamos los cambios
        });
    }
}
