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
    JList<String> userList  = new JList<>(list);
    JScrollPane listScrollPane  = new JScrollPane(userList);
    MenuInicialPanel menu = new MenuInicialPanel();
    MySocket mySocket = new MySocket(HOST,PORT);
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
        messages.setBounds(5, 40, 420, 330);
        messages.setBackground(Color.WHITE);
        messages.setEditable(false);
        this.add(messages);

        message.setBounds(5, 370, 350, 20);
        message.setBackground(Color.WHITE);
        message.setEditable(true);
        this.add(message);
        
        listScrollPane.setPreferredSize(new Dimension(100, 400));
        this.add(listScrollPane);
    }

    private void putBotones() {
        exit.setBounds(5, 5, 60, 20);
        exit.setBackground(Color.RED);//Ponemos el color de fondo del boton
        exit.setBorderPainted(false);//Quitamos los bordes
        exit.setFont(new Font("arial", Font.BOLD, 12));
        exit.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(exit);

        send.setBounds(355, 370, 70, 20);
        send.setBackground(Color.WHITE);//Ponemos el color de fondo del boton
        send.setBorderPainted(false);//Quitamos los bordes
        send.setFont(new Font("arial", Font.BOLD, 12));
        send.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(send);
    }

    private void putLabel() {
        nickname.setText("Welcome " + menu.nickname + "!!! Now you are connected :)");
        nickname.setBounds(150, 5, 300, 20);
        nickname.setFont(new Font("arial", Font.BOLD, 12));
        this.add(nickname);

    }

    private void actualizarLista() {
        String line;
        line = mySocket.readLine();
        String[] users = line.split(" ");
        list.removeAllElements();
        for (String usuarios : users) {
            list.addElement(usuarios);
        }
    }
    
    public class enterUsername implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (menu.nickname.length() > 0) {
                mySocket.println(menu.nickname);
                String servermsg = mySocket.readLine();
                if (servermsg.contains(" connectat")) {
                    connected = true;
                    MessageThread msth = new MessageThread();
                    msth.start();
                }
            }
        }
    }
    
    public class sendMessage implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
      mensaje = message.getText();
      if(mensaje.length() > 0){
        mySocket.println(mensaje);
        messages.append(menu.nickname + ": " + mensaje + "\n");
      }
    }
  }

 public class MessageThread extends Thread {
  @Override
  public void run() {
  while(connected){
    String missatge;
    missatge = mySocket.readLine();
    switch (missatge) {
      case "Exit":
        connected = false;
        break;
      case ".actualitza":
        actualizarLista();
        break;
      default:
        messages.append(missatge + " \n"); //Afegirà el contingut passat per el paràmetre StringBuffer missatge
        break;
        }
      }
    }
  }
 
 public static void main(String[] args) {
        Ventana ventana = new Ventana(); //View
        ventana.setVisible(true);
    }
}
