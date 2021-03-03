package P1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class EditableBufferReader extends BufferedReader {

    public EditableBufferReader(Reader in) {
        super(in);
    }

    public static void main(String args[]) {
        
        EditableBufferReader prueba = new EditableBufferReader();
        prueba.setRaw();
    }

    public void setRaw() {
        try {
            String[] command = {"/bin/sh", "-c", "stty raw </dev/tty"}; //stty -echo raw
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            System.out.println("Error al pasar al modo Raw");
        }

    public void unsetRaw() {
        try {
            String[] command = {"/bin/sh", "-c", "stty raw </dev/tty"};// stty echo cooked
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            System.out.println("Error al pasar al modo Cooked");
        }
    }

    @Override
    public int read() {
        return 1;
    }

    /*public String readLine() {
        return;
    }*/
}
