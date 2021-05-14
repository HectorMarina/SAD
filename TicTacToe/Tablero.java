/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TicTacToe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Marina
 */

public class Tablero extends JPanel implements ActionListener{

    private final JButton[][] casillas = new JButton[Constants.FILAS][Constants.COLUMNAS];//Creamos las casillas
    private int casillaOcupada[][] = {{0, 0, 0},
                                      {0, 0, 0},
                                      {0, 0, 0}};
    ImageIcon casillaVacia = new ImageIcon("casillaVacia.png");//Creamos la imagen de casilla vacía
    ImageIcon casillaX = new ImageIcon("x.png");//Creamos la imagen de casilla X
    ImageIcon casillaO = new ImageIcon("o.png");//Creamos la imagen de casilla O
    private int turno = 0;
    JLabel jugadorX = new JLabel();
    JLabel jugadorO = new JLabel();
    JButton restart = new JButton("Restart");
    JButton menu = new JButton("Menu");
    JButton sonido = new JButton("Sound on");
    JButton exit = new JButton("Exit");
    JButton opciones = new JButton("Options");
    private boolean sound = false;
    private boolean empate = false;
    public int marcadorX = 0;
    public int marcadorO = 0;
    private int contadorCasillas = 0;
    public String nombre1 = "";
    public String nombre2 = "";

    public Tablero() {
        iniciarComponentes();

    }

    private void iniciarComponentes() {
        putPanel();
        putTablero();
        putCasillas();
        menuOpciones();
    }

    private void putPanel() {
        this.setLayout(null);//Desactivando el diseño
        this.setBackground(Color.WHITE);//Ajustamos el color de fondo a blanco

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

        this.add(titulo);//Añadimos las etiquetas al panel
        this.add(tablero);

        //Etiqueta tipo texto
        jugadorX.setBounds(80, 400, 200, 25);//Posicionamos y dimensionamos los nombres de los jugadores
        jugadorX.setFont(Constants.LETRANOMBRES);//Cambiamos la fuente
        jugadorO.setBounds(250, 400, 200, 25);
        jugadorO.setFont(Constants.LETRANOMBRES);
        this.add(jugadorX);//Añadimos los nombres al panel
        this.add(jugadorO);
    }

    private void putCasillas() {
        int y = 0;
        int x = 0;
        for (int i = 0; i < Constants.FILAS; i++) {
            for (int j = 0; j < Constants.COLUMNAS; j++) {
                switch (i) {
                    case 0:
                        y = Constants.Y1;
                        break;
                    case 1:
                        y = Constants.Y2;
                        break;
                    case 2:
                        y = Constants.Y3;
                        break;
                }
                switch (j) {
                    case 0:
                        x = Constants.X1;
                        break;
                    case 1:
                        x = Constants.X2;
                        break;
                    case 2:
                        x = Constants.X3;
                        break;
                }
                casillas[i][j] = new JButton();//Creamos el boton
                casillas[i][j].setBounds(x, y, Constants.SQUARE, Constants.SQUARE);//Dimensionamos y decidimos su posición
                casillas[i][j].setBorderPainted(false);//Quitamos los bordes del boton
                casillas[i][j].setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casillas[i][j].getWidth(), casillas[i][j].getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
                casillas[i][j].addActionListener(this);//Añadimos el ActionListener a las casillas
                this.add(casillas[i][j]);//Añadimos el boton al panel

            }
        }
    }
    
    private void comprobarPosicion(Rectangle posicionBoton, String nombreCasilla) {
        
        int i = 0;
        int j = 0;
        
        switch(posicionBoton.x) {
            case Constants.X1:
                i = 0;
                switch(posicionBoton.y) {
                    case Constants.Y1:
                        j = 0;
                        break;
                    case Constants.Y2:
                        j = 1;
                        break;
                    case Constants.Y3:
                        j = 2;
                        break;
                }
                break;
            case Constants.X2:
                i = 1;
                switch(posicionBoton.y) {
                    case Constants.Y1:
                        j = 0;
                        break;
                    case Constants.Y2:
                        j = 1;
                        break;
                    case Constants.Y3:
                        j = 2;
                        break;
                }
                break;
            case Constants.X3:
                i = 2;
                switch(posicionBoton.y) {
                    case Constants.Y1:
                        j = 0;
                        break;
                    case Constants.Y2:
                        j = 1;
                        break;
                    case Constants.Y3:
                        j = 2;
                        break;
                }
                break;
        }
        
        if(nombreCasilla.equals(nombre1)) {
            casillaOcupada[i][j] = Constants.JUGADORX;
        } else if(nombreCasilla.equals(nombre2)) {
            casillaOcupada[i][j] = Constants.JUGADORO;
        }
        
    }
    
    private boolean comprobarEmpate() {
        
        if(contadorCasillas == 9-1) {
            empate = true;
        } else {
            empate = false;
        }
        
        return empate;
    }
    
    private int comprobarJugada(int jugador) {
        
        if(((casillaOcupada[0][0] == jugador) && (casillaOcupada[0][1] == jugador) && (casillaOcupada[0][2] == jugador)) ||
                ((casillaOcupada[1][0] == jugador) && (casillaOcupada[1][1] == jugador) && (casillaOcupada[1][2] == jugador)) ||
                ((casillaOcupada[2][0] == jugador) && (casillaOcupada[2][1] == jugador) && (casillaOcupada[2][2] == jugador)) ||
                ((casillaOcupada[0][0] == jugador) && (casillaOcupada[1][0] == jugador) && (casillaOcupada[2][0] == jugador)) ||
                ((casillaOcupada[0][1] == jugador) && (casillaOcupada[1][1] == jugador) && (casillaOcupada[2][1] == jugador)) ||
                ((casillaOcupada[0][2] == jugador) && (casillaOcupada[1][2] == jugador) && (casillaOcupada[2][2] == jugador)) ||
                ((casillaOcupada[0][0] == jugador) && (casillaOcupada[1][1] == jugador) && (casillaOcupada[2][2] == jugador)) ||
                ((casillaOcupada[0][2] == jugador) && (casillaOcupada[1][1] == jugador) && (casillaOcupada[2][0] == jugador))) {
            
            return jugador;
        } else {
            if(comprobarEmpate()) {
                return Constants.EMPATE;
            } else {
                return Constants.SEGUIRJUGANDO;
            }
        }
    }
    
    private void ganadorJugada(int ganador) {
        
        if(ganador != Constants.SEGUIRJUGANDO) {
            for(int i=0; i<Constants.FILAS; i++) {
                for(int j=0; j<Constants.COLUMNAS; j++) {
                    casillas[i][j].setEnabled(false);
                }
            }

            switch(ganador) {
                case Constants.JUGADORX: JOptionPane.showMessageDialog(null, "Ha ganado el Jugador X");
                          break;
                case Constants.JUGADORO: JOptionPane.showMessageDialog(null, "Ha ganado el Jugador O");
                          break;
                default:  JOptionPane.showMessageDialog(null, "Empate");
                          break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(menu)) {//Si el boton es menu no hacemos nada

        } else if (e.getSource().equals(restart)) {//Si el boton es restart
            for (int i = 0; i < Constants.FILAS; i++) {
                for (int j = 0; j < Constants.COLUMNAS; j++) {//Ponemos todas las casillas vacias otra vez
                    casillas[i][j].setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casillas[i][j].getWidth(), casillas[i][j].getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
                    casillas[i][j].setEnabled(true);
                    casillaOcupada[i][j] = Constants.CASILLAVACIA;
                }
            }
            marcadorX = 0;//Restablecemos los marcadores y el turno
            marcadorO = 0;
            turno = 0;
        } else if (e.getSource().equals(exit)) {//Si el boton es exit
            System.exit(0);//Salimos y paramos el programa
        } else if (e.getSource().equals(sonido)) {//Si el boton es sonido
            sound = !sound;//Cambiamos la variable sonido
            if (!sound) {
                sonido.setText("Sonido off");//Cambiamos el label dependiendo de sonido
            } else {
                sonido.setText("Sonido on");//Cambiamos el label dependiendo de sonido
            }
        } else if (e.getSource().equals(opciones)) {//Si el boton es opciones
            JOptionPane.showMessageDialog(null, " Press alt + m(Menu) / r(Restart) / s(Sound on/off) / e(Exit)", "Options", 1);//Mostaramos la ventana emergente con los comandos
        } else {
            JButton casilla = (JButton) e.getSource();
            if (turno % 2 == 0) {//Si es el turno de X
                casilla.setIcon(new ImageIcon(casillaX.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                contadorCasillas++;
                casilla.setEnabled(false);
                casilla.setName(nombre1);
                comprobarPosicion(casilla.getBounds(), casilla.getName());
                int jugadorGanador = comprobarJugada(Constants.JUGADORX);
                ganadorJugada(jugadorGanador);
            } else {//Si es el turno de O
                casilla.setIcon(new ImageIcon(casillaO.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                casilla.setIcon(new ImageIcon(casillaO.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                contadorCasillas++;
                casilla.setEnabled(false);
                casilla.setName(nombre2);
                comprobarPosicion(casilla.getBounds(), casilla.getName());
                int jugadorGanador = comprobarJugada(Constants.JUGADORO);
                ganadorJugada(jugadorGanador);
            }
            turno++;//Pasamos el turno

            

            if (sound) {//Comprobamos la variable sonido y reproducimos el sonido o no
                reproducirSonido();
            }
        }
    }

    private void reproducirSonido() {
        try {
            // Se obtiene un Clip de sonido
            Clip s = AudioSystem.getClip();

            // Se carga con un fichero wav
            s.open(AudioSystem.getAudioInputStream(new File("ficha.wav")));

            // Comienza la reproducción
            s.start();

        } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
            System.out.println("" + ex);
        }
    }

    private void menuOpciones() {
        menu.setBounds(450, 120, 100, 20);//Dimensionamos los botones y los posicionamos
        restart.setBounds(450, 170, 100, 20);
        sonido.setBounds(450, 220, 100, 20);
        exit.setBounds(450, 270, 100, 20);
        opciones.setBounds(450, 320, 100, 20);
        menu.setBackground(Color.WHITE);//
        restart.setBackground(Color.WHITE);
        sonido.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        opciones.setBackground(Color.WHITE);
        menu.setFont(Constants.LETRAMENUOPCIONES);
        restart.setFont(Constants.LETRAMENUOPCIONES);
        sonido.setFont(Constants.LETRAMENUOPCIONES);
        exit.setFont(Constants.LETRAMENUOPCIONES);
        opciones.setFont(Constants.LETRAMENUOPCIONES);
        restart.addActionListener(this);//Añadimos los actionListener a los botones
        sonido.addActionListener(this);
        exit.addActionListener(this);
        opciones.addActionListener(this);
        menu.setMnemonic('m');//Añadimos los atajos de teclado a los botones
        restart.setMnemonic('r');
        sonido.setMnemonic('s');
        exit.setMnemonic('e');
        this.add(menu);//Añadimos los botones al panel
        this.add(restart);
        this.add(sonido);
        this.add(exit);
        this.add(opciones);
    }
}
