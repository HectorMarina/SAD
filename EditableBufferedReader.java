/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

/**
 *
 * @author Marina
 */
public class EditableBufferedReader extends BufferedReader {
    
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {
        // TODO code application logic here
    //}
    
    public EditableBufferedReader(Reader reader) {
        super(reader);
    }
    
    public void setRaw() throws IOException, InterruptedException {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch(IOException ex) {
            System.out.println("Error al passar al mode Raw");
        }
    }
    
    public void unsetRaw() {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch(IOException ex) {
            System.out.println("Error al passar al mode Cooked");
        }
    }
    
    public int read() throws IOException {
        DataInputStream in = new DataInputStream(System.in);
        int i = -1;
        try {
            i = in.readInt();
        } catch(IOException ex) {
            
        }
        
        return i; 
    }
    
    public int readWithScanner() {
        Scanner reader = new Scanner(System.in);
        int integer = reader.nextInt();
        while(reader.hasNextInt()) {
            integer = reader.nextInt();
            System.out.println();
        }
        
        return integer;
    }
    
    public String readLine () {
        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
        String sTexto;
        
        return sTexto;
    }
    
    public String readLineWithScanner() {
        Scanner reader = new Scanner(System.in);
        String sTexto = reader.nextLine();
        while(reader.hasNextLine()) {
            sTexto = reader.nextLine();
            System.out.println();
        }
        
        return sTexto;
    }
    
}
