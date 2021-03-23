package P1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        } catch (IOException ex) {
            System.out.println("Error al passar al mode Raw");
        }
    }

    public void unsetRaw() throws InterruptedException {
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (IOException ex) {
            System.out.println("Error al passar al mode Cooked");
        }
    }

    public int read() throws IOException {
        DataInputStream in = new DataInputStream(System.in);
        int i = -1;
        try {
            i = super.read(); //Reads a single character

            if (i != Constants.ESC) {
                return i;
            }

            switch (i = super.read()) {
                case Constants.CSI:
                    switch (i = super.read()) {
                        case Constants.LEFT:
                            line.goLeft();
                            break;
                        case Constants.RIGHT:
                            line.goRight();
                            break;
                        case Constants.HOME:
                            line.goHome();
                            break;
                        case Constants.END:
                            line.goEnd();
                            break;
                        //case Constants.INS:   line.insertChar();
                        //break;
                        case Constants.DEL:
                            line.deleteChar();
                            break;
                        default:
                            return i;
                    }
                default:
                    return i;
            }
        } catch (IOException ex) {

        }

        return i;
    }

    public int readWithScanner() {
        Scanner reader = new Scanner(System.in);
        int integer = reader.nextInt();
        while (reader.hasNextInt()) {
            integer = reader.nextInt();
            System.out.println();
        }

        return integer;
    }

    @Override
    public String readLine() throws IOException {
        try {
            this.setRaw();
            while(this.read() != -1)
                this.unsetRaw();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(EditableBufferedReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return line.getLine();
    }

    public String readLineWithScanner() {
        Scanner reader = new Scanner(System.in);
        String sTexto = reader.nextLine();
        while (reader.hasNextLine()) {
            sTexto = reader.nextLine();
            System.out.println();
        }

        return sTexto;
    }

}
