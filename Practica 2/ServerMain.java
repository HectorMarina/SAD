/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

import java.io.IOException;

/**
 *
 * @author Marina
 */
public class ServerMain {
    
    public static void main(String[] args) {
        /*if(args.length < 1) {
            System.out.println("Syntax Error: java MyServerSocket <port-number>");
            System.exit(0);
        }*/
        
        //int port = Integer.parseInt(args[0]);
        
        try {
            MyServerSocket server = new MyServerSocket(6969);
            server.execute();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
