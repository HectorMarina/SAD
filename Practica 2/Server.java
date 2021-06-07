/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marina
 */
public class Server extends Thread {
    
    private Socket socket;
    private MyServerSocket server;
    private BufferedReader reader;
    private PrintWriter writer;
    
    
    public Server(Socket socket, MyServerSocket server) {
        this.socket = socket;
        this.server = server;
    }
    
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            
            String userName = reader.readLine();
            server.addUserName(userName, this);
            
            String mensajeServidor = "Se ha conectado un nuevo usuario: " + userName;
            server.broadcast(mensajeServidor, userName);
            
            String mensajeCliente;
            
            do {
                mensajeCliente = reader.readLine();
                mensajeServidor = "[ " + userName + "]: " + mensajeCliente;
                server.broadcast(mensajeServidor, userName);
            } while(mensajeCliente != null);
            
            server.removeUser(userName);
            socket.close();
            
            mensajeServidor = userName + " se ha desconectado";
            server.broadcast(mensajeServidor, userName);
            
            
        } catch(IOException e) {
            System.out.println("Error en el servidor");
        }
    }
    
    public void printUsers() {
        if(server.hasUsers()) {
            writer.println("Usuarios conectados: " + server.getUserNames());
        } else {
            writer.println("No hay usuarios conectados");
        }
    }
    
    public void sendMessage(String mensaje) {
        writer.println(mensaje);
    }
    
}
