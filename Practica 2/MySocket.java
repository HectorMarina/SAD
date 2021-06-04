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
public class MySocket extends Socket {
    
    private int port;
    private String ipAddr;
    private String nickName;
    private BufferedReader in;
    private PrintWriter out;
    
    
    public MySocket(String ipAddr, int port) {
        this.ipAddr = ipAddr;
        this.port = port;
        try {
            in = new BufferedReader(new InputStreamReader(this.getInputStream()));
            out = new PrintWriter(this.getOutputStream(), true);
        } catch(IOException e) {
            
        }
        
    }
    
    public void execute() {
        try {
            Socket socket = new Socket(ipAddr, port);
            
            
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
    
}
