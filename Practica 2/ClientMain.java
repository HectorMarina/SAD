/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2;

/**
 *
 * @author Marina
 */
public class ClientMain {
    
    public static void main(String[] args) {
        /*if(args.length < 2) {
            System.out.println("Syntax Error: java MySocket <host> <port-number>");
            System.exit(0);
        }*/
        
        //String ipAddr = args[0];
        //int port = Integer.parseInt(args[1]);
        
        MySocket client = new MySocket("127.0.0.1", 6969);
        client.execute();
    }
    
}
