package TicTacToe;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Tablero extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();//Creacion de un panel
    private int casillaOcupada[][] = {{0, 0, 0},
                                      {0, 0, 0},
                                      {0, 0, 0}};

    private JButton[][] casillas = new JButton[Constants.FILAS][Constants.COLUMNAS];//Creamos las casillas

    private ImageIcon casillaVacia = new ImageIcon("casillaVacia.png");//Creamos la imagen de casilla vacía
    private ImageIcon casillaX = new ImageIcon("x.png");//Creamos la imagen de casilla X
    private ImageIcon casillaO = new ImageIcon("o.png");//Creamos la imagen de casilla O
    private int turno = 0;

    public Tablero() {
        setSize(500, 500);//Establecemos el tamaño de la ventana
        setTitle("Tic Tac Toe");//Establecemos el titulo de la ventana
        setLocationRelativeTo(null);//Ponemos la ventana en el centro de la pantalla
        iniciarComponentes();
        setDefaultCloseOperation(EXIT_ON_CLOSE);//Hacer que acabe el porgrama cuando cerramos la ventana

    }

    private void iniciarComponentes() {
        putPanel();
        putTablero();
        putCasillas();
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
                casillas[i][j].addActionListener(this);
                panel.add(casillas[i][j]);//Añadimos el boton al panel

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton casilla = (JButton) e.getSource();
        if (turno % 2 == 0) {//Si es el turno de X
            casilla.setIcon(new ImageIcon(casillaX.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X

        } else {//Si es el turno de O
            casilla.setIcon(new ImageIcon(casillaO.getImage().getScaledInstance(casilla.getWidth(), casilla.getHeight(), Image.SCALE_SMOOTH)));//Colocamos la figura correspondiente X
        }
        turno++;//Pasamos el turno
    }
}
