
package TicTacToe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Tablero extends JFrame {
    
    JPanel panel = new JPanel();//Creacion de un panel
    
    public Tablero(){
        this.setSize(500, 500);//Establecemos el tamaño de la ventana
        setTitle("Tic Tac Toe");//Establecemos el titulo de la ventana
       setLocationRelativeTo(null);//Ponemos la ventana en el centro de la pantalla
       iniciarComponentes();
       setDefaultCloseOperation(EXIT_ON_CLOSE);//Hacer que acabe el porgrama cuando cerramos la ventana
       
    }

    private void iniciarComponentes() {
        putPanels();
        putLabels();
        putButtons();
        
    }

    private void putPanels() {
        panel.setLayout(null);//Desactivando el diseño
        panel.setBackground(Color.WHITE);//Ajustamos el color de fondo a blanco
        this.getContentPane().add(panel);//Agregamos el panel a la ventana
    }
    
    private void putLabels() {
        //Etiqueta tipo Texto
        JLabel titulo = new JLabel("Tic Tac Toe");//Creamos el label
        titulo.setFont(new Font("arial",Font.BOLD,30));//Establecemos tipo de fuente y  tamaño
        titulo.setBounds(85, 10, 300, 80);//Establecemos el tamaño y posicion de la etiqueta
        titulo.setHorizontalAlignment(SwingConstants.CENTER);//Establecemos la alineacion horizontal del texto (Se puede poner en el constructor)
        
//Etiqueta tipo imagen
        ImageIcon imagen = new ImageIcon("tablero.png");//Creamos una imagen
        JLabel tablero = new JLabel(); //Creamos el label
        tablero.setBounds(80, 80, 300, 300);//Ponemos el tamaño y posición del label
        tablero.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(tablero.getWidth(), tablero.getHeight(), Image.SCALE_SMOOTH)));//Estabelecemos medidaas de la imagen y la adaptacmos al label

        panel.add(titulo);//Añadimos las etiquetas al panel
        panel.add(tablero);
    }

    private void putButtons() {
        //Boton 2 imagen
        JButton boton11 = new JButton();//Creamos el boton
        boton11.setBounds(187, 193, 80, 80);//Dimensionamos y decidimos su posición
        boton11.setFocusable(false);//Quitamos los bordes del boton
        ImageIcon casilla = new ImageIcon("boton1.png");//Creamos una imagen para el boton
        boton11.setIcon(new ImageIcon(casilla.getImage().getScaledInstance(boton11.getWidth(), boton11.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(boton11);//Añadimos el boton al panel
        
        
        /*JButton boton12 = new JButton();
        
        
        
        JButton boton13 = new JButton();
        
        
        
        
        JButton boton21 = new JButton();
        
        
        
        
        JButton boton22 = new JButton();
        
        
        
        
        JButton boton23 = new JButton();
        
        
        
        
        JButton boton31 = new JButton();
        
        
        
        
        JButton boton32 = new JButton();
        
        
        
        
        JButton boton33 = new JButton();*/
        
        
        
        
    }

    
}
