package Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class EditableBufferedReader extends BufferedReader {

    private Line line;

    public EditableBufferedReader(Reader reader) {
        super(reader);
        line = new Line();
    }

    public void setRaw() throws IOException, InterruptedException {
        //pasar el terminal de modo cooked a modo raw
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (IOException ex) {
            System.out.println("Error al passar al mode Raw");
        }
    }

    public void unsetRaw() throws InterruptedException {
        //pasar el terminal de modo raw a modo cooked
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (IOException ex) {
            System.out.println("Error al passar al mode Cooked");
        }
    }

    public int read() {
        //Lee el caracter o tecla introducido utilizando la clase scanner
        Scanner reader = new Scanner(System.in);
        //Guardamos el caracter en un entero
        int integer = reader.nextInt();
        //Mientras haya algun caracter mas a leer los va guardando y lo printa por pantalla
        while (reader.hasNextInt()) {
            integer = reader.nextInt();
            System.out.println();
        }

        return integer;
    }

    public String readLine() {
        //Lee la linea introducida con opcion de modificarla
        Scanner reader = new Scanner(System.in);
        //Guardamos la linea en un string
        String sTexto = reader.nextLine();
        //Mientras haya caracteres para leer los va guardando y los printa por pantalla
        while (reader.hasNextLine()) {
            sTexto = reader.nextLine();
            System.out.println();
        }

        return sTexto;
    }

}
