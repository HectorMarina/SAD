package prac3;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

public class Server implements Runnable {
    //Para el server utilizaremos un concurrentMap que es más eficiente que las otras opciones
    public static ConcurrentHashMap<String, MySocket> users = new ConcurrentHashMap<>();
    public static final int PORT = 5000;
    MySocket mySocket;
    String nickname;
    public static boolean nextUser = false;

    public Server(String nick, MySocket socket) throws IOException {
        this.nickname = nick;
        this.mySocket = socket;
    }

    public static void main(String[] args) throws IOException {
        //Creamos el socket
        MyServerSocket myServerSocket = new MyServerSocket(PORT);
        System.out.println("[Server] Please wait while the server is starting...");

        while (true) {
            //Aceptamos el socket para que se pueda usar
            MySocket socket = myServerSocket.accept();
            while (!nextUser) {
                
                socket.println("Please introduce your username: ");
                String username = socket.readLine();
                //Leemos el nombre del usuario y comprobamos que no esté ya en uso
                if (users.containsKey(username)) {
                    
                    socket.println("The username is not available, please introduce another: ");
                    //Si ya existe perdimos otra vez el nombre
                } else {
                    socket.println("The user:  " +username+ " is connected");
                    users.put(username, socket);
                    new Thread(new Server(username, socket)).start();
                    nextUser = true;
                    //Si no existe lo creamos
                }
            }
            nextUser = false;
        }
    }
    
    @Override
    public void run(){
        String line;
        while((line = mySocket.readLine()) != null){
            boolean connection = connection(nickname,line);
            if(connection){
                for(MySocket socket : users.values()){
                    if(socket != mySocket){
                    socket.println(nickname + ": " + line);
                        System.out.println(nickname + ": " + line);
                    }
                }
            }
        }
    }

    private boolean connection(String nickname, String line) {
        //Comprobamos el estado del usuario
        boolean connection = true;
        //Comprobamos si quieres salir y lo eliminamos del map
        if(line.equals("exit")){
            users.remove(nickname);
            
            for(MySocket socket : users.values()){
                if(socket != mySocket){
                    socket.println(nickname + ": Bye");
                    socket.println(nickname + " has left");
                    System.out.println(nickname + " says goodbye!!");
                    System.out.println(nickname + " is disconnected!!");
                    socket.close();
                }
            }
            connection = false;
        }
        return connection;
    }
}

