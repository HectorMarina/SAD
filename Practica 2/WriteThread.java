/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Marina
 */
public class WriteThread extends Thread {
    
    private PrintWriter writer;
    private Socket socket;
    private MySocket client;
    
    public WriteThread(Socket socket, MySocket client) {
        this.socket = socket;
        this.client = client;
        
        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            System.out.println("Error obteniendo el output stream: " + e.getMessage());
        }
        
    }
    
    public void run() {
        
        Console console = System.console();
        
        String nickName = console.readLine("Escribe tu nickName");
        client.setNickName(nickName);
        writer.println(nickName);
        
        String text;
        
        do {
            text = console.readLine("[ " + client.getNickName() + " ]");
            writer.println(text);
        } while(text != null);
        
        try {
            socket.close();
        } catch(IOException e) {
            System.out.println("Error escribiendo al servidor");
        }
    }
    
}
