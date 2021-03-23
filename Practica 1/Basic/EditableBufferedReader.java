/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.Basic;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;
import practica1.Constants.Keyboard;

/**
 *
 * @author Marina
 */
public class EditableBufferedReader extends BufferedReader {
    
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
    
    public int read() throws IOException {
        DataInputStream in = new DataInputStream(System.in);
        int i = -1;
        try {
            i = super.read(); //Reads a single character
            
            if(i != Keyboard.ESC) {
                return i;
            }
            
            switch (i = super.read()) {
                case Keyboard.CSI:
                    switch(i = super.read()) {
                        case Keyboard.LEFT:  line.goLeft();
                                    break;
                        case Keyboard.RIGHT: line.goRight();
                                    break;
                        case Keyboard.HOME:  line.goHome();
                                    break;
                        case Keyboard.END:   line.goEnd();
                                    break;
                        case Keyboard.INS:   line.insertChar();
                                    break;
                        case Keyboard.DEL:   line.deleteChar();
                                    break;
                        default:    return i;
                    }
                default: return i;
            }
        } catch(IOException ex) {
            
        }
        
        return i;
    }
    
    public String readLine () {
        setRaw();
        //bucle hasta intro/// utilizar el metodo read para editar
        unsetRaw();
        return line.getLine();
    }
    
}
