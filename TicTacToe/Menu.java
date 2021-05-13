package TicTacToe;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Menu extends JLabel{

    JPanel panel = new JPanel();
    JLabel titulo = new JLabel("Tic Tac Toe");//Creamos el label
    JButton start2 = new JButton("Modo 2 jugadores");
    JButton start1 = new JButton("Modo 1 jugador");
    JButton exit = new JButton("Salir");
    ImageIcon gif = new ImageIcon("menu.gif");//Creamos un icono gif
    JLabel tablero = new JLabel(gif);

    public Menu() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        putLabel();
        putPanel();
        putBotones();

    }

    private void putPanel() {
        this.setLayout(null);//Desactivando el diseño
        this.setBackground(Color.WHITE);

    }

    private void putLabel() {
        titulo.setFont(new Font("arial", Font.BOLD, 30));//Establecemos tipo de fuente y  tamaño
        titulo.setBounds(80, 10, 300, 80);//Establecemos el tamaño y posicion de la etiqueta
        titulo.setHorizontalAlignment(SwingConstants.CENTER);//Establecemos la alineacion horizontal del texto (Se puede poner en el constructor)

        tablero.setBounds(80, 80, 300, 300);//Ponemos el tamaño y posición del label

        this.add(tablero);//Añadimos las etiquetas al panel
        this.add(titulo);

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
        this.add(start1);
        this.add(start2);
        this.add(exit);
    }

}
