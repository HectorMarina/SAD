package TicTacToe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tablero4 extends JPanel implements ActionListener {

    private final JButton[][] casillas = new JButton[7][6];//Creamos las casillas

    private final ImageIcon casillaVacia = new ImageIcon("casillaVacia4.png");//Creamos la imagen de casilla vacía
    private final ImageIcon roja = new ImageIcon("rojo.png");//Creamos la imagen de casilla X
    private final ImageIcon amarilla = new ImageIcon("amarillo.png");//Creamos la imagen de casilla O
    private int turno = 0;
    private int casillasOcupadas[][] = {{0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0}};
    JLabel jugadorRojo = new JLabel();
    JLabel jugadorAmarillo = new JLabel();
    JButton restart = new JButton("Restart");
    JButton menu = new JButton("Menu");
    JButton sonido = new JButton("Sound on");
    JButton exit = new JButton("Exit");
    JButton opciones = new JButton("Options");
    private boolean sound = false;
    private boolean empate = false;
    public int marcadorRojo = 0;
    public int marcadorAmarillo = 0;
    private int contadorCasillas = 0;
    public String nombre1 = "";
    public String nombre2 = "";
    public int partida;

    public Tablero4() {
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
        this.setBackground(Color.WHITE);//Ajustamos el color de fondo a blanco    }
    }

    private void putTablero() {
        //Etiqueta tipo Texto
        JLabel titulo = new JLabel("Tic Tac Toe");//Creamos el label
        titulo.setFont(new Font("arial", Font.BOLD, 30));//Establecemos tipo de fuente y  tamaño
        titulo.setBounds(110, 10, 300, 80);//Establecemos el tamaño y posicion de la etiqueta
        titulo.setHorizontalAlignment(SwingConstants.CENTER);//Establecemos la alineacion horizontal del texto (Se puede poner en el constructor)

        //Etiqueta tipo imagen
        ImageIcon imagen = new ImageIcon("tablero4.png");//Creamos una imagen
        JLabel tablero = new JLabel(); //Creamos el label
        tablero.setBounds(80, 40, 350, 350);//Ponemos el tamaño y posición del label
        tablero.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(tablero.getWidth(), tablero.getHeight(), Image.SCALE_SMOOTH)));//Estabelecemos medidaas de la imagen y la adaptacmos al label
        //this.add(titulo);//Añadimos las etiquetas al panel
        this.add(tablero);

        //Etiqueta tipo texto
        jugadorRojo.setBounds(120, 400, 200, 25);//Posicionamos y dimensionamos los nombres de los jugadores
        jugadorRojo.setFont(Constants.LETRANOMBRES);//Cambiamos la fuente
        jugadorAmarillo.setBounds(290, 400, 200, 25);
        jugadorAmarillo.setFont(Constants.LETRANOMBRES);
        this.add(jugadorRojo);//Añadimos los nombres al panel
        this.add(jugadorAmarillo);
    }

    private void putCasillas() {
        int y = 0;
        int x = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                switch (j) {
                    case 0:
                        y = Constants.Y1;
                        break;
                    case 1:
                        y = Constants.Y2;
                        break;
                    case 2:
                        y = Constants.Y3;
                        break;
                    case 3:
                        y = Constants.Y4;
                        break;
                    case 4:
                        y = Constants.Y5;
                        break;
                    case 5:
                        y = Constants.Y6;
                        break;

                }
                switch (i) {
                    case 0:
                        x = Constants.X1;
                        break;
                    case 1:
                        x = Constants.X2;
                        break;
                    case 2:
                        x = Constants.X3;
                        break;
                    case 3:
                        x = Constants.X4;
                        break;
                    case 4:
                        x = Constants.X5;
                        break;
                    case 5:
                        x = Constants.X6;
                        break;
                    case 6:
                        x = Constants.X7;
                        break;
                }
                casillas[i][j] = new JButton();//Creamos el boton
                casillas[i][j].setBounds(x, y, Constants.SQUARE4, Constants.SQUARE4);//Dimensionamos y decidimos su posición
                casillas[i][j].setBackground(Color.WHITE);
                //casillas[i][j].setBorderPainted(false);//Quitamos los bordes del boton
                casillas[i][j].setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casillas[i][j].getWidth(), casillas[i][j].getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
                casillas[i][j].addActionListener(this);//Añadimos el ActionListener a las casillas
                this.add(casillas[i][j]);//Añadimos el boton al panel    
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(restart)) {//Si el boton es restart
            restart();
            marcadorRojo = 0;//Restablecemos los marcadores y el turno
            marcadorAmarillo = 0;
            jugadorRojo.setText(nombre1 + "(R): " + marcadorRojo);
            jugadorAmarillo.setText(nombre2 + "(A): " + marcadorAmarillo);
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
                int x = obtenerPosY(casilla.getX());
                int y = obtenerUltimaFila(x);
                if (y != -1) {
                if (turno % 2 == 0) {//Si es el turno de roja
                    casillas[x][y].setIcon(new ImageIcon(roja.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                } else if (turno % 2 != 0) {//Si es el turno de amarilla
                    casillas[x][y].setIcon(new ImageIcon(amarilla.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                }
                turno++;//Pasamos el turno
                reproducirSonido(sound);
            }
        }
    }

    private int obtenerUltimaFila(int j) {
        for (int i = 5; i >= 0; i--) {
            if (casillasOcupadas[i][j] == 0) {
                ocuparCasilla(i, j);
                return i;
            }
        }
        return -1;
    }

    private int obtenerPosY(int pos) {
        int i = 0;
        switch (pos) {
            case Constants.X1:
                i = 0;
                break;
            case Constants.X2:
                i = 1;
                break;
            case Constants.X3:
                i = 2;
                break;
            case Constants.X4:
                i = 3;
                break;
            case Constants.X5:
                i = 4;
                break;
            case Constants.X6:
                i = 5;
                break;
            case Constants.X7:
                i = 6;
                break;
        }
        return i;
    }
    
    private void ocuparCasilla(int i, int j) {
        if (turno % 2 != 0) {
            casillasOcupadas[i][j] = Constants.JUGADORX;
        } else {
            casillasOcupadas[i][j] = Constants.JUGADORO;
        }
    }

    private void menuOpciones() {
        menu.setBounds(515, 120, 100, 20);//Dimensionamos los botones y los posicionamos
        restart.setBounds(515, 170, 100, 20);
        sonido.setBounds(515, 220, 100, 20);
        exit.setBounds(515, 270, 100, 20);
        opciones.setBounds(515, 320, 100, 20);
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

    private void restart() {
        for (int i = 0; i < Constants.COLUMNAS4; i++) {
            for (int j = 0; j < Constants.FILAS4; j++) {//Ponemos todas las casillas vacias otra vez
                casillas[i][j].setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casillas[i][j].getWidth(), casillas[i][j].getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
                casillas[i][j].setEnabled(true);
                casillasOcupadas[i][j] = Constants.CASILLAVACIA;
            }
        }
    }

    private void reproducirSonido(boolean sonido) {
        if (sonido) {
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
    }
}
