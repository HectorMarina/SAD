/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Marina
 */
public class MyServerSocket extends ServerSocket {
    
    private int port;
    public static ConcurrentHashMap<String, Server> clients = new ConcurrentHashMap<String, Server>();
    
    public MyServerSocket(int port) {
        this.port = port;
    }
    
    /*
        MyServerSocket guarda los clientes en un map. Los valores de key (K) y value (V) para la aplicacion son:
        - Key: Indica los nombres de los usuarios (String)
        - Value: Indica el Thread que sirve al cliente key (Server)
    */
    
    public void execute() {
        //El serverSocket escucha por el puerto port
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("El Servidor Chat esta escuchando por el puerto " + port);
            
            //El Servidor Chat mientras escucha por el puerto port
            while(true) {
                //Se escucha si alguien se conecta al serverSocket y cuando se conecta acepta la conexion
                Socket socket = serverSocket.accept();
                System.out.println("Se ha conectado un nuevo usuario");
                
                //Se hace un nuevo usuario con el socket que esta conectado al serverSocket y el serverSocket y se inicializa nuevoUsuario
                Server nuevoUsuario = new Server(socket, this);
                nuevoUsuario.start();
            }
            
        } catch(IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        if(args.length < 1) {
            System.out.println("Syntax Error: java MyServerSocket <port-number>");
            System.exit(0);
        }
        
        int port = Integer.parseInt(args[0]);
        
        MyServerSocket server = new MyServerSocket(port);
        server.execute();
    }
    
    public void addUserName(String userName, Server server) {
        clients.put(userName, server);
    }
    
    public void removeUser(String userName) {
        clients.remove(userName);
    }
    
    public Set<String> getUserNames() {
        Set<String> userNames = new HashSet<>();
        for(String key : clients.keySet()) {
            userNames.add(key);
        }
        
        return userNames;
    }
    
    public boolean hasUsers() {
        return !clients.isEmpty();
    }
    
    public void broadcast(String mensaje, String usuarioExcluido) {
        for(String key : clients.keySet()) {
            if(!key.equals(usuarioExcluido)) {
                clients.get(key).sendMessage(mensaje);
            }
        }
    }
    
}
