package prac2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Client {

    public static final String HOST = "localhost";
    public static final int PORT = 5000;
    private static String nickname;

    public static void main(String[] args) throws IOException {
        BufferedReader entry = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("[Client] Please introduce your nickname\n");
        //Pedimos el nickname del usuario
        try {
            nickname = entry.readLine();
            System.out.println("[Client] Hello " + nickname + " now you are connected!\n");

        } catch (IOException e) {
            System.out.println("[Error] Couldn't read the message!!");
        }
        //Creamos el socket
        MySocket socket = new MySocket(HOST, PORT);

        //Input Thread
        new Thread() {
            @Override
            public void run() {
                String line;
                try {
                    while ((line = entry.readLine()) != null) {
                        socket.println(line);
                    }
                    socket.close();
                } catch (IOException e) {
                    System.out.println("[Error] Couldn't read the message!!");
                }
            }
        }.start();

        //Output Thread
        new Thread() {
            @Override
            public void run() {
                String line;
                while ((line = socket.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }.start();
    }
}
