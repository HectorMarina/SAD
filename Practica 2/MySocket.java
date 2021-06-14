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
public class MySocket {
    
    private int port;
    private String ipAddr;
    private String nickName;
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;
    
    
    public MySocket(String ipAddr, int port) {
        this.ipAddr = ipAddr;
        this.port = port;
        
        try {
            socket = new Socket("localhost", 5000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch(IOException e) {
            
        }
        
    }
    
    /*
        Cada cliente esta compuesto de dos Threads:
        - Un Thread de lectura que es el que escucha los mensajes del servidor
        - Un Thread de escritura que es el que escucha lo que el usuario escribe en la consola
    */
    
    public void execute() {
        try {
            //Se hace un socket con la direccion IP del host y el puerto al que esta conectado
            Socket socket = new Socket(ipAddr, port);
            
            System.out.println("Conectado al servidor chat");
            
            //Se inicializa el Thread de lectura
            new ReadThread(socket, this).start();
            
            //Se inicializa el Thread de escritura
            new WriteThread(socket, this).start();
            
            
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        
    }
    
    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    public void println(String s) {
        writer.println(s);
    }
    
    public String readLine() {
        try {
            return reader.readLine();
        } catch(IOException e) {
            return e.getMessage();
        }
    }
    
    /*public static void main(String[] args) {
        if(args.length < 2) {
            System.out.println("Syntax Error: java MySocket <host> <port-number>");
            System.exit(0);
        }
        
        String ipAddr = args[0];
        int port = Integer.parseInt(args[1]);
        
        MySocket client = new MySocket(ipAddr, port);
        client.execute();
    }*/
    
}
