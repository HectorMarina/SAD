package prac3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MenuInicialPanel extends JPanel {

    String nickname;
    JTextField textField = new JTextField();
    JButton registro = new JButton("Register");
    JLabel name = new JLabel("Please introduce your username: ");

    public MenuInicialPanel() {
        putPanel();
        putLogin();
        putBoton();

    }

    private void putPanel() {
        this.setLayout(null);//Desactivando el diseño
        this.setBackground(Color.WHITE);//Ajustamos el color de fondo a blanco
    }

    private void putLogin() {//Hacer que no se pueda elegir un nick ya en uso
        JLabel titulo = new JLabel("Please introduce your name");//Creamos el label
        titulo.setFont(new Font("arial", Font.BOLD, 25));//Establecemos tipo de fuente y  tamaño
        titulo.setBounds(25, 50, 400, 100);//Establecemos el tamaño y posicion de la etiqueta
        titulo.setHorizontalAlignment(SwingConstants.CENTER);//Establecemos la alineacion horizontal del texto (Se puede poner en el constructor)
        this.add(titulo);

        textField.setBounds(130, 130, 165, 40);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setFont(new Font("arial", Font.BOLD, 25));
        this.add(textField);
        nickname = textField.getText();
    }

    private void putBoton() {
        registro.setBounds(135, 175, 150, 30);//Posicionamos y imensionamos los botones
        registro.setBackground(Color.BLUE);//Ponemos el color de fondo del boton
        registro.setBorderPainted(false);//Quitamos los bordes
        registro.setFont(new Font("arial", Font.BOLD, 25));
        this.add(registro);

    }
}
