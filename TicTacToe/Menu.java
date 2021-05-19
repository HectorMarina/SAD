package TicTacToe;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JLabel {

    JPanel panel = new JPanel();
    JLabel titulo = new JLabel("Tic Tac Toe & Connect4");//Creamos el label
    JButton start2 = new JButton("Tic Tac Toe");
    JButton start1 = new JButton("Connect4");
    JButton exit = new JButton("Salir");
    ImageIcon gif = new ImageIcon("menu.gif");//Creamos un icono gif
    public Menu() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        putLabel();
        putPanel();
        putBotones();

    }

    private void putPanel() {
        panel.setSize(700,500);
        panel.setBackground(Color.WHITE);
        this.setLayout(null);//Desactivando el diseño
        panel.setLayout(null);//Desactivando el diseño
        this.add(panel);

    }

    private void putLabel() {
        titulo.setFont(new Font("arial", Font.BOLD, 30));//Establecemos tipo de fuente y  tamaño
        titulo.setBounds(37, 10, 400, 80);//Establecemos el tamaño y posicion de la etiqueta
        titulo.setHorizontalAlignment(SwingConstants.CENTER);//Establecemos la alineacion horizontal del texto (Se puede poner en el constructor)

        JLabel tablero = new JLabel(gif);
        tablero.setBounds(80, 80, 320, 320);//Ponemos el tamaño y posición del label
        this.add(tablero);//Añadimos las etiquetas al panel
        panel.add(titulo);

    }

    private void putBotones() {
        start2.setBounds(380, 150, 300, 35);//Posicionamos y imensionamos los botones
        start1.setBounds(380, 200, 300, 35);
        exit.setBounds(450, 250, 140, 35);
        start2.setBackground(Color.WHITE);//Ponemos el color de fondo del boton
        start1.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        start2.setBorderPainted(false);//Quitamos los bordes
        start1.setBorderPainted(false);
        exit.setBorderPainted(false);
        start1.setFont(new Font("arial", Font.BOLD, 25));//Ponemos el tipo de letra y tamaño
        start2.setFont(new Font("arial", Font.BOLD, 25));
        exit.setFont(new Font("arial", Font.BOLD, 25));
        panel.add(start1);//Añadimos los botones al panel
        panel.add(start2);
        panel.add(exit);
    }

}
