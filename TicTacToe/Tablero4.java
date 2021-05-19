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

public class Tablero4 extends JPanel implements ActionListener {

    private final JButton[][] casillas = new JButton[7][6];//Creamos las casillas

    private final ImageIcon casillaVacia = new ImageIcon("casillaVacia4.png");//Creamos la imagen de casilla vacía
    private final ImageIcon roja = new ImageIcon("rojo.png");//Creamos la imagen de casilla X
    private final ImageIcon amarilla = new ImageIcon("amarillo.png");//Creamos la imagen de casilla O
    private final ImageIcon sonidoOn = new ImageIcon("sonidoOn.png");//Creamos la imagen de sonido encendido
    private final ImageIcon sonidoOff = new ImageIcon("sonidoOff.png");//Creamos la imagen de sonido apagado
    private int turno = 0;//Variable para manejar el turno
    private int casillasOcupadas[][] = {{0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0},
    {0, 0, 0, 0, 0, 0, 0}};//Matriz para controlar las casillas
    JLabel jugadorRojo = new JLabel();//Label del jugador rojo
    JLabel jugadorAmarillo = new JLabel();//Label del jugador amarillo
    JButton restart = new JButton("Restart");//Boton restart
    JButton menu = new JButton("Menu");//Boton menu
    JButton sonido = new JButton("Sound on");//Boton sonido
    JButton exit = new JButton("Exit");//Boton salir
    JButton opciones = new JButton("Options");//Boton opciones
    private boolean sound = false;//Variable para manejar el sonido
    public int marcadorRojo = 0;//Marcador jugador Rojo
    public int marcadorAmarillo = 0;//Marcador jugador Amarillo
    public String nombre1 = "";//Casilla de tipo Amarillo
    public String nombre2 = "";//Casilla de tipo Rojo
    public int partida;//Numero de partidas para ganar
    private boolean ganador = false;//Variable para saber si hay un ganador
    private int turnoInicial = 0;//Variable para saber quien ha empezado
    private int contadorCasillas = 0;//Variable para contar las casillas ocupadas

    public Tablero4() {
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
        this.setBackground(Color.WHITE);//Ajustamos el color de fondo a blanco    }
    }

    private void putTablero() {
        //Etiqueta tipo Texto
        JLabel titulo = new JLabel("Connect4");//Creamos el label
        titulo.setFont(new Font("arial", Font.BOLD, 30));//Establecemos tipo de fuente y  tamaño
        titulo.setBounds(110, 0, 300, 80);//Establecemos el tamaño y posicion de la etiqueta
        titulo.setHorizontalAlignment(SwingConstants.CENTER);//Establecemos la alineacion horizontal del texto (Se puede poner en el constructor)
        this.add(titulo);//Añadimos las etiquetas al panel
        //Etiqueta tipo imagen
        ImageIcon imagen = new ImageIcon("tablero4.png");//Creamos una imagen
        JLabel tablero = new JLabel(); //Creamos el label
        tablero.setBounds(80, 65, 350, 350);//Ponemos el tamaño y posición del label
        tablero.setIcon(new ImageIcon(imagen.getImage().getScaledInstance(tablero.getWidth(), tablero.getHeight(), Image.SCALE_SMOOTH)));//Estabelecemos medidaas de la imagen y la adaptacmos al label
        this.add(tablero);//Añadimos las etiquetas al panel

        //Etiqueta tipo texto
        jugadorRojo.setBounds(120, 420, 200, 25);//Posicionamos y dimensionamos los nombres de los jugadores
        jugadorRojo.setFont(Constants.LETRANOMBRES);//Cambiamos la fuente
        jugadorAmarillo.setBounds(290, 420, 200, 25);
        jugadorAmarillo.setFont(Constants.LETRANOMBRES);
        this.add(jugadorRojo);//Añadimos los nombres al panel
        this.add(jugadorAmarillo);
    }

    private void putCasillas() {//Colocamos las casillas para que coincidan con la imagen del tablero
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
                casillas[i][j].setBorderPainted(false);//Quitamos los bordes del boton
                casillas[i][j].setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casillas[i][j].getWidth(), casillas[i][j].getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
                casillas[i][j].addActionListener(this);//Añadimos el ActionListener a las casillas
                this.add(casillas[i][j]);//Añadimos el boton al panel    
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(restart)) {//Si el boton es restart
            restartCompeticion();//Hacemos restart
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
                //sonido.setText("Sonido off");//Cambiamos el label dependiendo de sonido
                sonido.setIcon(new ImageIcon(sonidoOff.getImage().getScaledInstance(sonido.getWidth(), sonido.getHeight(), Image.SCALE_SMOOTH)));
            } else {
                //sonido.setText("Sonido on");//Cambiamos el label dependiendo de sonido
                sonido.setIcon(new ImageIcon(sonidoOn.getImage().getScaledInstance(sonido.getWidth(), sonido.getHeight(), Image.SCALE_SMOOTH)));
            }
        } else if (e.getSource().equals(opciones)) {//Si el boton es opciones
            JOptionPane.showMessageDialog(null, " Press alt + m(Menu) / r(Restart) / s(Sound on/off) / e(Exit)", "Options", 1);//Mostaramos la ventana emergente con los comandos
        } else {
            JButton casilla = (JButton) e.getSource();//Miramos la casilla que hemos pulsado y obtenemos su posicion
            int x = obtenerPosY(casilla.getX());
            int y = obtenerUltimaFila(x);
            if (y != -1) {//Si esta disponible
                if (turno % 2 == 0) {//Si es el turno de Rojo
                    casillas[x][y].setIcon(new ImageIcon(roja.getImage().getScaledInstance(casillas[x][y].getWidth(), casillas[x][y].getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                    estadoDelJuego(Constants.JUGADORR);//Comprobamos el estado del juego
                    reproducirSonido(sound);//Reproducimos el sonido de la ficha
                } else {//Si es el turno de Amarillo
                    casillas[x][y].setIcon(new ImageIcon(amarilla.getImage().getScaledInstance(casillas[x][y].getWidth(), casillas[x][y].getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
                    estadoDelJuego(Constants.JUGADORA);//Comprobamos el estado del juego
                    reproducirSonido(sound);//Reproducimos el sonido de la ficha
                }
            }
            turno++;//Pasamos el turno
            contadorCasillas++;//Casillas ocupadas++
            comprobarGanador();//Comprobamos si alguien ha ganado el campeonato
        }
    }

    private void estadoDelJuego(int jugador) {
        if (contadorCasillas < 43) {//Comprobamos si no hay empate
            if (comprobarFila(jugador) == jugador || comprobarColumna(jugador) == jugador || comprobarDiagonalADerechas(jugador) == jugador || comprobarDiagonalAIzquierdas(jugador) == jugador) {
                ganadorJugada(jugador);//Hay un ganador
            }
        } else {
            JOptionPane.showMessageDialog(null, "Empate");//Mostramos el panel de empate y hacemos restart
            restartPartida();
        }
    }

    private void ganadorJugada(int jugador) {
        switch (jugador) {//Dependiendo del ganador mostramos un panel u otro y lo añadimos al marcador
            case Constants.JUGADORR:
                JOptionPane.showMessageDialog(null, "Ha ganado el Jugador Rojo");
                marcadorRojo++;
                jugadorRojo.setText(nombre1 + "(R): " + marcadorRojo);

                break;
            case Constants.JUGADORA:
                JOptionPane.showMessageDialog(null, "Ha ganado el Jugador Amarillo");
                marcadorAmarillo++;
                jugadorAmarillo.setText(nombre2 + "(A): " + marcadorAmarillo);
                break;
        }
        cambioDeTurno(jugador);//Comprobamos el turno para ver quien empezara la suguiente partida y hacemos restart
        restartPartida();
    }

    private int obtenerUltimaFila(int j) {
        for (int i = 5; i >= 0; i--) {
            if (casillasOcupadas[i][j] == 0) {
                ocuparCasilla(i, j);//Marcamos la casilla
                return i;//Obtenemos la fila mas baja de la columna que hemos seleccionado
            }
        }
        return -1;
    }

    private int obtenerPosY(int pos) {//A partir de la posicion de la casilla obtenemos la columna
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

    private void ocuparCasilla(int i, int j) {//Dependiendo del turno marcamos la casilla con una ficha u otra
        if (turno % 2 == 0) {
            casillasOcupadas[i][j] = Constants.JUGADORR;
        } else {
            casillasOcupadas[i][j] = Constants.JUGADORA;
        }
    }

    private void menuOpciones() {
        menu.setBounds(515, 120, 100, 20);//Dimensionamos los botones y los posicionamos
        restart.setBounds(515, 170, 100, 20);
        sonido.setBounds(550, 320, 25, 20);
        exit.setBounds(515, 270, 100, 20);
        opciones.setBounds(515, 220, 100, 20);
        menu.setBackground(Color.WHITE);//Ponemos el fondo blanco
        restart.setBackground(Color.WHITE);
        sonido.setBackground(Color.WHITE);
        exit.setBackground(Color.WHITE);
        opciones.setBackground(Color.WHITE);
        menu.setFont(Constants.LETRAMENUOPCIONES);//Cambiamos la fuente y el tamaño de la letra
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

    private void restartPartida() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {//Ponemos todas las casillas vacias otra vez y restablecemos el turno inicial
                casillasOcupadas[j][i] = Constants.CASILLAVACIA;
                casillas[i][j].setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casillas[i][j].getWidth(), casillas[i][j].getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
            }
        }
        turnoInicial++;
        contadorCasillas = 0;
    }

    private void restartCompeticion() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {//Ponemos todas las casillas vacias otra vez
                casillas[i][j].setIcon(new ImageIcon(casillaVacia.getImage().getScaledInstance(casillas[i][j].getWidth(), casillas[i][j].getHeight(), Image.SCALE_SMOOTH)));//Dimensionamos la imagen segun el boton
                casillasOcupadas[j][i] = Constants.CASILLAVACIA;
            }
        }
        marcadorRojo = 0;//Restablecemos los marcadores y el turno
        marcadorAmarillo = 0;
        turno = 0;
        turnoInicial = 0;
        contadorCasillas = 0;
        jugadorRojo.setText(nombre1 + "(R): " + marcadorRojo);
        jugadorAmarillo.setText(nombre2 + "(A): " + marcadorAmarillo);
    }

    private int comprobarFila(int jugador) {//Comprobamos si hay una fila
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (casillasOcupadas[i][j] == jugador && casillasOcupadas[i][j + 1] == jugador && casillasOcupadas[i][j + 2] == jugador && casillasOcupadas[i][j + 3] == jugador) {
                    return jugador;
                }
            }
        }

        return 0;
    }

    private int comprobarColumna(int jugador) {//Comprobamos si hay una columna
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                if (casillasOcupadas[i][j] == jugador && casillasOcupadas[i + 1][j] == jugador && casillasOcupadas[i + 2][j] == jugador && casillasOcupadas[i + 3][j] == jugador) {
                    return jugador;
                }
            }
        }
        return 0;
    }

    private int comprobarDiagonalADerechas(int jugador) {//Comprobamos si hay una diagonal a derechas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (casillasOcupadas[i][j] == jugador && casillasOcupadas[i + 1][j + 1] == jugador && casillasOcupadas[i + 2][j + 2] == jugador && casillasOcupadas[i + 3][j + 3] == jugador) {
                    return jugador;
                }
            }
        }
        return 0;
    }

    private int comprobarDiagonalAIzquierdas(int jugador) {//Comprobamos si hay una diagonal a izquierdas
        for (int i = 0; i < 3; i++) {
            for (int j = 3; j < 7; j++) {
                if (casillasOcupadas[i][j] == jugador && casillasOcupadas[i + 1][j - 1] == jugador && casillasOcupadas[i + 2][j - 2] == jugador && casillasOcupadas[i + 3][j - 3] == jugador) {
                    return jugador;
                }
            }
        }
        return 0;
    }

    private void comprobarGanador() {//Si alguien ha ganado el numero de partidas para ganar el campeonato salta el panel correspondiente con sonido y hace restart del campeonato
        Icon icono = new ImageIcon("trofeore.png");
        if (marcadorRojo == partida) {
            sonidoVictoria(sound);
            sonidoAplausos(sound);
            JOptionPane.showMessageDialog(null, "Felicidades " + nombre1 + "(R) Has ganado :)", "Victoria", JOptionPane.PLAIN_MESSAGE, icono);
            restartCompeticion();
        } else if (marcadorAmarillo == partida) {
            sonidoVictoria(sound);
            sonidoAplausos(sound);
            JOptionPane.showMessageDialog(null, "Felicidades " + nombre2 + "(A) Has ganado :)", "Victoria", JOptionPane.PLAIN_MESSAGE, icono);
            restartCompeticion();
        }
    }

    private void reproducirSonido(boolean sonido) {//Reproducimos el sonido de la ficha
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

    private void sonidoVictoria(boolean sonido) {//Reproducimos el sonido de victoria
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
