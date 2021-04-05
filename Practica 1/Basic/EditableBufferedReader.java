
package Basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import Constants.Keyboard;

public class EditableBufferedReader extends BufferedReader {

    private Line line;

    public EditableBufferedReader(Reader reader) {
        super(reader);
        this.line = new Line();
    }

    public void setRaw() throws IOException, InterruptedException {
        //pasar el terminal de modo cooked a modo raw
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al passar al mode Raw");
        }
    }

    public void unsetRaw() throws IOException, InterruptedException {
        //pasar el terminal de modo raw a modo cooked
        try {
            String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
            Runtime.getRuntime().exec(cmd).waitFor();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Error al passar al mode Cooked");
        }
    }

    @Override
    public int read() throws IOException {
        int i = -1;
        try {
            //Lee el siguiente caracter o tecla
            i = super.read();

            //Detectar que es una secuencia de escape
            if (i != Keyboard.ESC) {
                i = super.read();

                switch (i = super.read()) {
                    //Detectar si el siguiente caracter es un corchete
                    case Keyboard.CSI:
                        //Detectar el caracter y devolver su entero
                        switch (i = super.read()) {
                            case 'D':
                                return Keyboard.LEFT;

                            case 'C':
                                return Keyboard.RIGHT;

                            case '1':
                                return Keyboard.HOME;

                            case '4':
                                return Keyboard.END;

                            case '2':
                                return Keyboard.INS;

                            case '3':
                                return Keyboard.DEL;

                            default:
                                return i;
                        }
                    default:
                        return i;
                }
            } else if (i == Keyboard.BKSP) {
                return Keyboard.BKSP;
            }
        } catch (IOException ex) {

        }

        return -1;
    }

    @Override
    public String readLine() throws IOException {
        //Pasamos a modo Raw la consola
        //this.setRaw();
        int i = -1;
        try {
            //Lee el siguiente caracter o tecla
            i = super.read();

            //Detectar que es una secuencia de escape
            if (i != Keyboard.ESC) {
                i = super.read();

                switch (i = super.read()) {
                    //Detectar si el siguiente caracter es un corchete
                    case Keyboard.CSI:
                        //Detectar el caracter y llamar al metodo correspondiente
                        switch (i = super.read()) {
                            case 'D':
                                //Movemos el cursor a la izquierda
                                this.line.goLeft();
                                break;

                            case 'C':
                                //Movemos el cursor a la derecha
                                this.line.goRight();
                                break;

                            case '1':
                                //Movemos el cursor al inicio de la linea
                                this.line.goHome();
                                break;

                            case '4':
                                //Movemos el cursor al final de la linea
                                this.line.goEnd();
                                break;

                            case '2':
                                //Damos o quitamos permiso
                                this.line.insert();
                                break;

                            case '3':
                                this.line.deleteChar();
                                break;
                        }
                }
            } else if (i == Keyboard.BKSP) {
                this.line.bkspChar();
            }

            //Pasamos a modo Cooked la consola
            //this.unsetRaw();
            //Si tenemos permiso para sobreescribir lo haremos
            //Si no tenemos permiso para sobreescribir no lo haremos
            this.line.insertChar((char) i);

        } catch (IOException ex) {

        }

        return this.line.toString();
    }

}
