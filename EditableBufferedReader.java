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
            i = super.read(); //Reads a single character
            
            if(i != ESC) {
                return i;
            }
            
            switch (i = super.read()) {
                case CSI:
                    switch(i = super.read()) {
                        case LEFT:  line.goLeft();
                                    break;
                        case RIGHT: line.goRight();
                                    break;
                        case HOME:  line.goHome();
                                    break;
                        case END:   line.goEnd();
                                    break;
                        case INS:   line.insertChar();
                                    break;
                        case DEL:   line.deleteChar();
                                    break;
                        default:    return i;
                    }
                default: return i;
            }
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
        this.setRaw();
        //bucle hasta intro/// utilizar el metodo read para editar
        this.unsetRaw();
        return line.getLine();
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
