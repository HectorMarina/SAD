package TicTacToe;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Ventana extends JFrame {

    Menu menu = new Menu();
    Tablero tablero = new Tablero();
    String nombre1 = "";
    String nombre2 = "";

    public Ventana() {
        this.setSize(700, 500);//Establecemos el tamaño de la ventana
        this.setTitle("Tic Tac Toe");//Establecemos el titulo de la ventana
        this.setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);//Ponemos la ventana en el centro de la pantalla
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Hacer que acabe el porgrama cuando cerramos la ventana
        this.add(menu);
        menu.setVisible(true);
        menu.start2.addActionListener((ActionEvent e) -> {
            nombre1 = JOptionPane.showInputDialog("Nombre del jugador 1");
            tablero.jugador1.setText(nombre1);
            nombre2 = JOptionPane.showInputDialog("Nombre del jugador 2");
            tablero.jugador2.setText(nombre2);
            add(tablero);
            menu.setVisible(false);
            tablero.setVisible(true);
            validate();
        });
        /*menu.start1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nombre1 = JOptionPane.showInputDialog("Nombre del jugador 1");
                tablero.jugador1.setText(nombre1);
                tablero.jugador2.setText("Máquina");
                menu.setVisible(false);
                
                validate();
            }
        });*/
        menu.exit.addActionListener((ActionEvent e) -> {
            dispose();
        });
        
        
    }
}
