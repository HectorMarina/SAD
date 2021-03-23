/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.Scanner;

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
    
    private static final int LEFT = 'D';
    private static final int RIGHT = 'C';
    private static final int HOME = '1';
    private static final int END = '4';
    private static final int INS = '2';
    private static final int DEL = '3';
    private static final int ESC = '\033';
    private static final int CSI = '[';
    private Line line;
    
    public EditableBufferedReader(Reader reader) {
        super(reader);
        line = new Line();
    }
    
    public void setRaw() throws IOException, InterruptedException {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch(IOException ex) {
            System.out.println("Error al passar al mode Raw");
        }
    }
    
    public void unsetRaw() throws InterruptedException {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch(IOException ex) {
            System.out.println("Error al passar al mode Cooked");
        }
    }
    
    public int read() {
        Scanner reader = new Scanner(System.in);
        int integer = reader.nextInt();
        while(reader.hasNextInt()) {
            integer = reader.nextInt();
            System.out.println();
        }
        
        return integer;
    }
    
    public String readLine() {
        Scanner reader = new Scanner(System.in);
        String sTexto = reader.nextLine();
        while(reader.hasNextLine()) {
            sTexto = reader.nextLine();
            System.out.println();
        }
        
        return sTexto;
    }
    
}
