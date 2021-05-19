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
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tablero extends JPanel implements ActionListener {

    private final JButton[][] casillas = new JButton[Constants.FILAS][Constants.COLUMNAS];//Creamos las casillas
    private int casillaOcupada[][] = {{0, 0, 0},
    {0, 0, 0},
    {0, 0, 0}};//Matriz para controlar las casillas
    ImageIcon casillaVacia = new ImageIcon("casillaVacia.png");//Creamos la imagen de casilla vacía
    ImageIcon casillaX = new ImageIcon("x.png");//Creamos la imagen de casilla X
    ImageIcon casillaO = new ImageIcon("o.png");//Creamos la imagen de casilla O
    private final ImageIcon sonidoOn = new ImageIcon("sonidoOn.png");//Creamos la imagen de sonido encendido
    private final ImageIcon sonidoOff = new ImageIcon("sonidoOff.png");//Creamos la imagen de sonido apagado
    private int turno = 0;//Variable para manejar el turno
    JLabel jugadorX = new JLabel();//Label  del jugador X
    JLabel jugadorO = new JLabel();//Label  del jugador O
    JButton restart = new JButton("Restart");//Boton restart
    JButton menu = new JButton("Menu");//Boton menu
    JButton sonido = new JButton("Sound on");//Boton sonido
    JButton exit = new JButton("Exit");//Boton salir
    JButton opciones = new JButton("Options");//Boton opciones
    private boolean sound = false;//Variable para manejar el sonido
    public int marcadorX = 0;//Marcador jugador X
    public int marcadorO = 0;//Marcador jugador O
    public String nombre1 = "";//Casilla de tipo X
    public String nombre2 = "";//Casilla de tipo O
    public int partida;//Numero de partidas para ganar
    private int turnoInicial = 0;//Variable para saber quien ha empezado
    private int contadorCasillas = 0;//Variable para contar las casillas ocupadas

    public Tablero() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        putPanel();//Pone el panel
        putTablero();//Pone el tablero y los labels
        putCasillas();//Pone las casillas en el tablero
        menuOpciones();//Pone los botones del menu lateral
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

    private void putCasillas() {//Colocamos las casillas para que coincidan con la imagen del tablero
        int y = 0;
        int x = 0;
        for (int i = 0; i < Constants.FILAS; i++) {
            for (int j = 0; j < Constants.COLUMNAS; j++) {
                switch (i) {
                    case 0:
                        y = Constants.Y11;
                        break;
                    case 1:
                        y = Constants.Y22;
                        break;
                    case 2:
                        y = Constants.Y33;
                        break;
                }
                switch (j) {
                    case 0:
                        x = Constants.X11;
                        break;
                    case 1:
                        x = Constants.X22;
                        break;
                    case 2:
                        x = Constants.X33;
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

    private void comprobarPosicion(int i, int j, String nombreCasilla) {//Marcamos la casilla dependiendo de cual sea el que tira
        if (nombreCasilla.equals(nombre1)) {
            casillaOcupada[i][j] = Constants.JUGADORX;
        } else if (nombreCasilla.equals(nombre2)) {
            casillaOcupada[i][j] = Constants.JUGADORO;
        }
    }

    private void estadoDelJuego(int jugador) {//Comprobamos el estado del juegoS
        if (contadorCasillas < 10) {//Comprobamos si hay empate
            if (comprobarFila(jugador) == jugador || comprobarColumna(jugador) == jugador || comprobarDiagonalADerechas(jugador) == jugador || comprobarDiagonalAIzquierdas(jugador) == jugador) {
                ganadorJugada(jugador);//Hay un ganador

            }
        } else {
            JOptionPane.showMessageDialog(null, "Empate");//Mostramos el panel de empate y hacemos restart
            restartPartida();
        }
    }

    private int comprobarDiagonalADerechas(int jugador) {//Comprobamos si hay una diagonal a derechas
        if ((casillaOcupada[0][0] == jugador) && (casillaOcupada[1][1] == jugador) && (casillaOcupada[2][2] == jugador)) {

            return jugador;
        }
        return 0;

    }

    private int comprobarDiagonalAIzquierdas(int jugador) {//Comprobamos si hay una diagonal a izquierdas
        if ((casillaOcupada[0][2] == jugador) && (casillaOcupada[1][1] == jugador) && (casillaOcupada[2][0] == jugador)) {
            return jugador;
        }
        return 0;
    }

    private int comprobarFila(int jugador) {//Comprobamos si hay una fila
        for (int j = 0; j < 3; j++) {
            if (casillaOcupada[0][j] == jugador && casillaOcupada[1][j] == jugador && casillaOcupada[2][j] == jugador) {
                return jugador;
            }
        }
        return 0;
    }

    private int comprobarColumna(int jugador) {//Comprobamos si hay una columna
        for (int i = 0; i < 3; i++) {
            if (casillaOcupada[i][0] == jugador && casillaOcupada[i][1] == jugador && casillaOcupada[i][2] == jugador) {
                return jugador;
            }
        }
        return 0;
    }

    private void ganadorJugada(int jugador) {//Dependiendo del ganador mostramos un panel u otro y lo añadimos al marcador
        switch (jugador) {
            case Constants.JUGADORX:
                JOptionPane.showMessageDialog(null, "Ha ganado el Jugador X");
                marcadorX++;
                jugadorX.setText(nombre1 + "(X): " + marcadorX);
                break;
            case Constants.JUGADORO:
                JOptionPane.showMessageDialog(null, "Ha ganado el Jugador O");
                marcadorO++;
                jugadorO.setText(nombre2 + "(O): " + marcadorO);
                break;
        }
        cambioDeTurno(jugador);//Comprobamos el turno para ver quien empezara la suguiente partida y hacemos restart
        restartPartida();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(restart)) {//Si el boton es restart
            restartCompeticion();
            marcadorX = 0;//Restablecemos los marcadores y el turno
            marcadorO = 0;
            jugadorX.setText(nombre1 + "(X): " + marcadorX);
            jugadorO.setText(nombre2 + "(O): " + marcadorO);
            turno = 0;
        } else if (e.getSource().equals(exit)) {//Si el boton es exit
            System.exit(0);//Salimos y paramos el programa
        } else if (e.getSource().equals(sonido)) {//Si el boton es sonido
             sound = !sound;//Cambiamos la variable sonido
            if (!sound) {
                //sonido.setText("Sonido off");//Cambiamos el label dependiendo de sonido
                sonido.setIcon(new ImageIcon(sonidoOff.getImage().getScaledInstance(sonido.getWidth(), sonido.getHeight(), Image.SCALE_SMOOTH)));
            } else {
                //sonido.setText("Sonido on");//Cambiamos el label dependiendo de sonido
                sonido.setIcon(new ImageIcon(sonidoOn.getImage().getScaledInstance(sonido.getWidth(), sonido.getHeight(), Image.SCALE_SMOOTH)));
            }
        } else if (e.getSource().equals(opciones)) {//Si el boton es opciones
            JOptionPane.showMessageDialog(null, " Press alt + m(Menu) / r(Restart) / s(Sound on/off) / e(Exit)", "Options", 1);//Mostaramos la ventana emergente con los comandos
        } else {
            JButton casilla = (JButton) e.getSource();
            if (casillaOcupada[obtenerPosY(casilla.getX())][obtenerPosX(casilla.getY())] == Constants.CASILLAVACIA) {
                if (turno % 2 == 0) {//Si es el turno de X
                    casilla.setIcon(new ImageIcon(casillaX.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                    casilla.setName(nombre1);//Ponemos nombre a la casilla
                    comprobarPosicion(obtenerPosY(casilla.getX()), obtenerPosX(casilla.getY()), nombre1);//Marcamos la matriz de casilla ocupadas
                    estadoDelJuego(Constants.JUGADORX);//Miramos el estado del juego
                    reproducirSonido(sound);//Reproducimos el sonido de la ficha
                } else {//Si es el turno de O
                    casilla.setIcon(new ImageIcon(casillaO.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                    casilla.setName(nombre2);//Ponemos nombre a la casilla
                    comprobarPosicion(obtenerPosY(casilla.getX()), obtenerPosX(casilla.getY()), nombre2);//Marcamos la matriz de casilla ocupadas
                    estadoDelJuego(Constants.JUGADORO);//Miramos el estado del juego
                    reproducirSonido(sound);//Reproducimos el sonido de la ficha
                }
            }
            turno++;//Pasamos el turno
            contadorCasillas++;//Casilla ocupada++
            comprobarGanador();//Comprobamos si alguien ha ganado el campeonato
        }
    }

    private int obtenerPosX(int pos) {//Obtenemos la fila y columna a partir de la posicion
        int i = 0;
        switch (pos) {
            case Constants.Y11:
                i = 0;
                break;
            case Constants.Y22:
                i = 1;
                break;
            case Constants.Y33:
                i = 2;
                break;
        }
        return i;
    }

    private int obtenerPosY(int pos) {
        int i = 0;
        switch (pos) {
            case Constants.X11:
                i = 0;
                break;
            case Constants.X22:
                i = 1;
                break;
            case Constants.X33:
                i = 2;
                break;
        }
        return i;
    }

    private void menuOpciones() {
        menu.setBounds(450, 120, 100, 20);//Dimensionamos los botones y los posicionamos
        restart.setBounds(450, 170, 100, 20);
        sonido.setBounds(485, 320, 25, 20);
        exit.setBounds(450, 270, 100, 20);
        opciones.setBounds(450, 220, 100, 20);
        menu.setBackground(Color.WHITE);//Ponemos el fondo blanco del boton
        restart.setBackground(Color.WHITE);
        sonido.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        opciones.setBackground(Color.WHITE);
        menu.setFont(Constants.LETRAMENUOPCIONES);//Ajustamos la letra y tamaño
        restart.setFont(Constants.LETRAMENUOPCIONES);
        sonido.setIcon(new ImageIcon(sonidoOff.getImage().getScaledInstance(sonido.getWidth(), sonido.getHeight(), Image.SCALE_SMOOTH)));
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

    private void comprobarGanador() {//Si alguien ha ganado el numero de partidas para ganar el campeonato salta el panel correspondiente con sonido y hace restart del campeonato
        Icon icono = new ImageIcon("trofeore.png");
        if (marcadorX == partida) {
            sonidoVictoria(sound);
            sonidoAplausos(sound);
            JOptionPane.showMessageDialog(null, "Felicidades " + nombre1 + "(X) Has ganado :)", "Victoria", JOptionPane.PLAIN_MESSAGE, icono);
            restartCompeticion();
        } else if (marcadorO == partida) {
            sonidoVictoria(sound);
            sonidoAplausos(sound);
            JOptionPane.showMessageDialog(null, "Felicidades " + nombre2 + "(O) Has ganado :)", "Victoria", JOptionPane.PLAIN_MESSAGE, icono);
            restartCompeticion();
        }
    }

    private void restartPartida() {
        for (int i = 0; i < Constants.FILAS; i++) {
            for (int j = 0; j < Constants.COLUMNAS; j++) {//Ponemos todas las casillas vacias otra vez y restablecemos el turno inicial
                casillas[i][j].setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casillas[i][j].getWidth(), casillas[i][j].getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
                casillaOcupada[i][j] = Constants.CASILLAVACIA;
            }
        }
        turnoInicial++;
        contadorCasillas = 0;
    }

    private void restartCompeticion() {
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
        turnoInicial = 0;
        jugadorX.setText(nombre1 + "(X): " + marcadorX);
        jugadorO.setText(nombre2 + "(O): " + marcadorO);
        contadorCasillas = 0;
    }

    private void reproducirSonido(boolean sonido) {//Reproducimos sonido de ficha
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

    private void sonidoVictoria(boolean sonido) {//Reproducimos sonido de victoria
        if (sonido) {
            try {
                // Se obtiene un Clip de sonido
                Clip s = AudioSystem.getClip();

                // Se carga con un fichero wav
                s.open(AudioSystem.getAudioInputStream(new File("win.wav")));

                // Comienza la reproducción
                s.start();

            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                System.out.println("" + ex);
            }
        }
    }

    private void sonidoAplausos(boolean sonido) {//Reproducimos sonido de aplausos
        if (sonido) {
            try {
                // Se obtiene un Clip de sonido
                Clip s = AudioSystem.getClip();

                // Se carga con un fichero wav
                s.open(AudioSystem.getAudioInputStream(new File("aplausos.wav")));

                // Comienza la reproducción
                s.start();

            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException ex) {
                System.out.println("" + ex);
            }
        }
    }

    private void cambioDeTurno(int jugador) {//Manejamos el cambio de turno para hacer que cada color empiece una partida cada vez
        if (jugador == Constants.JUGADORX && turnoInicial % 2 != 0) {
            turno++;
        } else if (jugador == Constants.JUGADORO && turnoInicial % 2 == 0) {
            turno++;
        }
    }
}
