package TicTacToe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tablero extends JFrame {

    private JPanel panel = new JPanel();//Creacion de un panel
    private JButton casilla11 = new JButton();//Creamos la casilla noroeste
    private JButton casilla21 = new JButton();//Creamos la casilla oeste
    private JButton casilla31 = new JButton();//Creamos la casilla suroeste
    private JButton casilla22 = new JButton();//Creamos la casilla central
    private JButton casilla12 = new JButton();//Creamos la casilla norte
    private JButton casilla32 = new JButton();//Creamos la casilla sur
    private JButton casilla13 = new JButton();//Creamos la casilla noreste
    private JButton casilla23 = new JButton();//Creamos la casilla este
    private JButton casilla33 = new JButton();//Creamos la casilla sureste

    private ImageIcon casillaVacia = new ImageIcon("casillaVacia.png");//Creamos la imagen de casilla vacía
    private ImageIcon casillaX = new ImageIcon("x.png");//Creamos la imagen de casilla X
    private ImageIcon casillaO = new ImageIcon("o.png");//Creamos la imagen de casilla O
    
    public int turno = 0;
    private static final int XCASILLA = 75;
    private static final int YCASILLA = 75;
    
    

    public Tablero() {
        this.setSize(500, 500);//Establecemos el tamaño de la ventana
        setTitle("Tic Tac Toe");//Establecemos el titulo de la ventana
        setLocationRelativeTo(null);//Ponemos la ventana en el centro de la pantalla
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);//Hacer que acabe el porgrama cuando cerramos la ventana

    }

    private void iniciarComponentes() {
        putPanel();
        putTablero();
        putCasilla();

    }

    private void putPanel() {
        panel.setLayout(null);//Desactivando el diseño
        panel.setBackground(Color.WHITE);//Ajustamos el color de fondo a blanco
        this.getContentPane().add(panel);//Agregamos el panel a la ventana
    }

    private void putTablero() {
        //Etiqueta tipo Texto
        JLabel titulo = new JLabel("Tic Tac Toe");//Creamos el label
        titulo.setFont(new Font("arial", Font.BOLD, 30));//Establecemos tipo de fuente y  tamaño
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

    private void putCasilla() {
        //Casilla noroeste
        casilla11.setBounds(94, 97, XCASILLA, YCASILLA);//Dimensionamos y decidimos su posición
        casilla11.setBorderPainted(false);//Quitamos los bordes del boton
        casilla11.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla11.getWidth(), casilla11.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(casilla11);//Añadimos el boton al panel

        //Casilla norte
        casilla12.setBounds(189, 97, XCASILLA, YCASILLA);//Dimensionamos y decidimos su posición
        casilla12.setBorderPainted(false);//Quitamos los bordes del boton
        casilla12.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla12.getWidth(), casilla12.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(casilla12);//Añadimos el boton al panel

        //Casilla noreste
        casilla13.setBounds(287, 97, XCASILLA, YCASILLA);//Dimensionamos y decidimos su posición
        casilla13.setBorderPainted(false);//Quitamos los bordes del boton
        casilla13.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla11.getWidth(), casilla11.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(casilla13);//Añadimos el boton al panel

        //Casilla oeste
        casilla21.setBounds(94, 195, XCASILLA, YCASILLA);//Dimensionamos y decidimos su posición
        casilla21.setBorderPainted(false);//Quitamos los bordes del boton
        casilla21.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla21.getWidth(), casilla21.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(casilla21);//Añadimos el boton al panel

        //Casilla central
        casilla22.setBounds(189, 195, XCASILLA, YCASILLA);//Ponemos el tamaño y posición del label
        casilla22.setBorderPainted(false);//Quitamos los bordes del boton
        casilla22.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla22.getWidth(), casilla22.getHeight(), Image.SCALE_SMOOTH)));//Estabelecemos medidaas de la imagen y la adaptacmos al label
        panel.add(casilla22);//Añadimos el boton al panel

        //Casilla este
        casilla23.setBounds(287, 195, XCASILLA, YCASILLA);//Dimensionamos y decidimos su posición
        casilla23.setBorderPainted(false);//Quitamos los bordes del boton
        casilla23.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla23.getWidth(), casilla23.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(casilla23);//Añadimos el boton al panel

        //Casilla suroeste
        casilla31.setBounds(94, 290, XCASILLA, YCASILLA);//Dimensionamos y decidimos su posición
        casilla31.setBorderPainted(false);//Quitamos los bordes del boton
        casilla31.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla31.getWidth(), casilla31.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(casilla31);//Añadimos el boton al panel

        //Casilla sur
        casilla32.setBounds(189, 290, XCASILLA, YCASILLA);//Dimensionamos y decidimos su posición
        casilla32.setBorderPainted(false);//Quitamos los bordes del boton
        casilla32.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla32.getWidth(), casilla32.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(casilla32);//Añadimos el boton al panel

        //Casilla sureste
        casilla33.setBounds(287, 290, XCASILLA, YCASILLA);//Dimensionamos y decidimos su posición
        casilla33.setBorderPainted(false);//Quitamos los bordes del boton
        casilla33.setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casilla31.getWidth(), casilla31.getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
        panel.add(casilla33);//Añadimos el boton al panel

        eventoOyenteDeRaton();//Escuchamos al raton

    }

    private void eventoOyenteDeRaton() {
        //Agregando oyente de Raton
        MouseListener oyenteDeRaton = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent evento) {
                JButton boton = (JButton) evento.getSource();//Creamos un boton que sera el boton que hagamos click
                if (boton == casilla11 || boton == casilla12 || boton == casilla13 || boton == casilla21 || boton == casilla22 || boton == casilla23 || boton == casilla31 || boton == casilla32 || boton == casilla33) {//Si clickamos la casilla 
                    if (turno % 2 == 0) {//Miramos el turno 

                        boton.setIcon(new ImageIcon(casillaX.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X

                    } else {

                        boton.setIcon(new ImageIcon(casillaO.getImage().getScaledInstance(boton.getWidth(), boton.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente O
                        
                    }
                    turno++;

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        
        casilla11.addMouseListener(oyenteDeRaton);
        casilla12.addMouseListener(oyenteDeRaton);
        casilla13.addMouseListener(oyenteDeRaton);
        casilla21.addMouseListener(oyenteDeRaton);
        casilla22.addMouseListener(oyenteDeRaton);
        casilla23.addMouseListener(oyenteDeRaton);
        casilla31.addMouseListener(oyenteDeRaton);
        casilla32.addMouseListener(oyenteDeRaton);
        casilla33.addMouseListener(oyenteDeRaton);

    }
}
