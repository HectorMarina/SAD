package prac3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Chat extends JPanel {

    public static final String HOST = "localhost";
    public static final int PORT = 3000;

    JLabel nickname = new JLabel();
    JButton send = new JButton("Send");
    JButton exit = new JButton("Exit");
    JTextArea messages = new JTextArea();
    JTextField message = new JTextField();
    DefaultListModel<String> list = new DefaultListModel<>();
    JList<String> userList = new JList<>(list);
    JScrollPane listScrollPane = new JScrollPane(userList);
    MenuInicialPanel menu = new MenuInicialPanel();
    MySocket mySocket = new MySocket(HOST, PORT);
    boolean connected = false;
    String mensaje;

    public Chat() {
        putPanel();
        putLabel();
        putMensajes();
        putBotones();
    }

    private void putPanel() {
        this.setLayout(null);//Desactivando el diseño
        this.setBackground(Color.WHITE);//Ajustamos el color de fondo a blanco
    }

    private void putMensajes() {
        messages.setBounds(5, 40, 420, 330);//Establecemos el tamaño y posición
        messages.setBackground(Color.WHITE);//Establecemos el color de fondo
        messages.setEditable(false);//Hacemos que no se pueda editar
        this.add(messages);

        message.setBounds(5, 370, 350, 20);//Establecemos el tamaño y posición
        message.setBackground(Color.WHITE);//Establecemos el color de fondo
        message.setEditable(true);//Hacemos que se pueda editar
        this.add(message);

        listScrollPane.setPreferredSize(new Dimension(100, 400));//Establecemos las dimensiones
        this.add(listScrollPane);
    }

    private void putBotones() {
        exit.setBounds(5, 5, 60, 20);//Establecemos el tamaño y posición
        exit.setBackground(Color.RED);//Ponemos el color de fondo del boton
        exit.setBorderPainted(false);//Quitamos los bordes
        exit.setFont(new Font("arial", Font.BOLD, 12));//Establecemos el tamaño y tipo de fuente
        exit.setHorizontalAlignment(SwingConstants.LEFT);//Establecemos la alineacion horizontal del texto
        this.add(exit);

        send.setBounds(355, 370, 70, 20);
        send.setBackground(Color.WHITE);//Ponemos el color de fondo del boton
        send.setBorderPainted(false);//Quitamos los bordes
        send.setFont(new Font("arial", Font.BOLD, 12));//Establecemos el tamaño y tipo de fuente
        send.setHorizontalAlignment(SwingConstants.LEFT);//Establecemos la alineacion horizontal del texto
        this.add(send);
    }

    private void putLabel() {
        nickname.setText("Welcome " + menu.nickname + "!!! Now you are connected :)");//Ponemos texto al label
        nickname.setBounds(150, 5, 300, 20);//Establecemos el tamaño y posición
        nickname.setFont(new Font("arial", Font.BOLD, 12));//Establecemos el tamaño y tipo de fuente
        this.add(nickname);

    }

    private void actualizarLista() {
        String line;
        line = mySocket.readLine();//Leemos la linea introducida por el usuario
        String[] users = line.split(" ");//Sepramaos la linea por el espacio
        list.removeAllElements();//Eliminamos los elementos de la lista
        for (String usuarios : users) {
            list.addElement(usuarios);//Añadimos los usuarios a la lista
        }
    }

    public class enterUsername implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {//Añadimos un action listener
            if (menu.nickname.length() > 0) {//Si hay un nickname
                mySocket.println(menu.nickname);//Printamos el nickname
                connected = true;//El usuario está conectado
                MessageThread msth = new MessageThread();//Creamos un nuevo thread
                msth.start();//Start del thread

            }
        }
    }

    public class sendMessage implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {//Añadimos un action listener
            mensaje = message.getText();//Leemos el mensaje
            if (mensaje.length() > 0) {
                mySocket.println(mensaje);//Lo printamos
                messages.append(menu.nickname + ": " + mensaje + "\n");
            }
        }
    }

    public class MessageThread extends Thread {

        @Override
        public void run() {
            while (connected) {
                String missatge;
                missatge = mySocket.readLine();//Leemos el mensaje
                switch (missatge) {
                    case "Exit"://Si Exit se desconecta
                        connected = false;
                        break;
                    case ".act"://Si actualiza, actualizamos la lista de users
                        actualizarLista();
                        break;
                    default:
                        messages.append(missatge + " \n"); //´ñadimos el contenido
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();
        ventana.setVisible(true);
    }
}
