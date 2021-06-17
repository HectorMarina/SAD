package prac3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Ventana extends JFrame {

    MenuInicialPanel menu = new MenuInicialPanel();
    Chat chat = new Chat();

    public Ventana() {
        jbInit();//Dimensionamos la ventana
        activarBoton();//Manejamos los botones
    }

    private void jbInit() {
        this.setSize(450, 450);//Establecemos el tamaño de la ventana
        this.setTitle("Chat");//Establecemos el titulo de la ventana
        this.setBackground(Color.WHITE);//Establecemos el fondo de la ventana
        this.setLocationRelativeTo(null);//Ponemos la ventana en el centro de la pantalla
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//Hacer que acabe el porgrama cuando cerramos la ventana
        this.add(menu);//Añdimos el menu a la ventana
        menu.setVisible(true);//Mostramos el menu
    }

    private void activarBoton() {
        menu.registro.addActionListener((ActionEvent e) -> {//Añadimos un action listener al boton de registro
            this.add(chat);//Añadimos el panel a la ventana
            menu.setVisible(false);//Quitamos y ponemos la visibilidad
            chat.setVisible(true);
            validate();//Actualizamos los cambios
        });
        chat.exit.addActionListener((ActionEvent e) -> {
            this.dispose();//Paramos y cerramos el programa
        });
    }
}