/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marina
 */
public class ReadThread extends Thread {
    
    private BufferedReader reader;
    private Socket socket;
    private MySocket client;
    
    public ReadThread(Socket socket, MySocket client) {
        this.socket = socket;
        this.client = client;
        
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch(IOException e) {
            System.out.println("Error obteniendo el input strem: " + e.getMessage());
        }
        
    }
    
    public void run() {
        while(true) {
            try {
                String inTexto = reader.readLine();
                System.out.println("\n" + inTexto);
                
                if(client.getNickName() != null) {
                    System.out.println("[ " + client.getNickName() + " ]");
                }
                
            } catch (IOException ex) {
                System.out.println("Error leyendo del servidor: " + ex.getMessage());
            }
            
        }
    }
    
}
