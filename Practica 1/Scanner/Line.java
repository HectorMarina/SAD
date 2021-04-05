package Scanner;

//import java.util.ArrayList;

public class Line {

    //private ArrayList<Character> linea;
    private String line;
    private int pos;
    private boolean permission; //Variable utilizada para dar permiso a la hora de insertar un caracter

    public Line() {
        this.line = new String();
        //this.linea = new ArrayList<>();
        this.pos = 0;
        this.permission = false;
    }

    public String getLine() {
        //Devuelve el contenido de la linea
        return this.line;
        //return this.linea; en el caso de que hagamos un metodo ArrayList

    }

    public int getPos() {
        //Devuelve la posicion actual del cursor
        return this.pos;
    }

    public void insert() {
        //Damos permiso para sobreescribir
         this.permission = !this.permission;
    }

    public void insertChar(char character) {
        //Miramos si tenemos permiso para sobreescribir
        if (this.permission) {
            if (this.pos < this.line.length()) {
                //Si no tenemos el cursor al final de la linea insertamos en la posicion actual substituyendo por el caracter que habia antes
                this.line = this.line.substring(0, this.pos) + Character.toString(character) + this.line.substring(this.pos + 1, this.line.length());
            } else {
                //Si estamos al final de la linea substituimos el caracter de la ultima posicion por el que insertamos
                this.line = this.line.substring(0, this.line.length() - 1) + Character.toString(character);
            }
        } else {
            //Si no tenemos permiso para sobreescribir insertamos el caracter en la posicion actual sin sobreescribir
            this.line = this.line = this.line.substring(0, this.pos) + Character.toString(character) + this.line.substring(this.pos, this.line.length());
        }
        /*
        //Miramos si tenemos permiso para sobreescribir
        if (this.permission) {
            if (this.pos < this.linea.size()) {
                //Si no tenemos el cursor al final de la linea insertamos en la posicion actual substituyendo por el caracter que habia antes
                this.linea = this.linea.set(this.pos, character);
            } else {
                //Si estamos al final de la linea substituimos el caracter de la ultima posicion por el que insertamos
                this.linea = this.linea.add(this.pos,character);
            }
        } else {
            //Si no tenemos permiso para sobreescribir insertamos el caracter en la posicion actual sin sobreescribir
            this.linea = this.linea.add(this.pos, character) ;
        }*/
        this.pos++;

    }

    public void deleteChar() {
        //Si no me encuentro al inicio de linea procedo a borrarno me encuentro al inicio de linea procedo a borrar
        /*if (this.pos != this.linea.size()) {
            //Borramos el caracter de la posicion anterior del cursor
            this.linea = this.linea.remove(this.pos);
        }*/

        //Si no me encuentro al inicio de linea procedo a borrar
        if (this.pos > 0) {
            //Borramos el caracter de la posicion anterior del cursor
            this.line = this.line.substring(0, this.pos) + this.line.substring(this.pos + 1, this.line.length());

        }
    }

    public void bkspChar() {
        //Si //Si no me encuentro al inicio de linea procedo a borrarno me encuentro al inicio de linea procedo a borrar
        /*if (this.pos > 0) {
            //Borramos el caracter de la posicion anterior del cursor
            this.linea = this.linea.remove(this.pos - 1);
            this.goLeft();
        }
         */
        //Si //Si no me encuentro al inicio de linea procedo a borrarno me encuentro al inicio de linea procedo a borrar
        if (this.pos > 0) {
            //Borramos el caracter de la posicion anterior del cursor
            this.line = this.line.substring(0, this.pos - 1) + this.line.substring(this.pos, this.line.length());
            this.goLeft();
        }

    }

    public void goLeft() {
        //Mueve el cursor a la izquierda
        if (this.pos != 0) {
            //Si el cursor no se encuentra al principio de la linea lo mueve a la izquierda
            this.pos--;
        }
    }

    public void goRight() {
        //Mueve el cursor a la derecha
        if (this.pos != this.line.length()) { // this.linea.size() si es ArrayList
            //Si el cursor no se encuentra al final de la linea lo mueve a la derecha
            this.pos++;
        }
    }

    public void goHome() {
        //Mueve el cursor al inicio de la linea
        this.pos = 0;
    }

    public void goEnd() {
        //Mueve el cursor al final de la linea
        this.pos = this.line.length() - 1;
        //this.pos = this.linea.size() -1;
        
    }

    @Override
    public String toString(){
        return this.line;
        /*
        String vector = "";
        for(Character character: this.linea){
            vector += character
        }
        return vector;
        */
        
    }
}
