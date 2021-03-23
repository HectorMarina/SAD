package P1;

import java.util.Observable;

public class Line extends Observable {

    private String line;
    private int pos;
    private boolean permission;

    public Line() {
        line = new String();
        pos = 0;
        permission = false;
    }

    public String getLine() {
        return line;
    }

    public int getPos() {
        return pos;
    }

    public Boolean getPermission() {
        return permission = true;
    }
    //Buffer,String,Lista...
    /*public void insertChar(char character) { //Revisar
        if (permission && pos != line.length()) {
            line += Character.toString(character);
        }else{
            
        }
        pos++;
        setChanged();
        notifyObservers();
    }*/

    public void deleteChar() {//Revisar
        if (pos < line.length()) {
            line = line.substring(pos - 1, pos);
        }
        pos--;
        setChanged();
        notifyObservers();
    }

    public void bkspChar() {//Revisar
        if (pos < line.length()) {
            line = line.substring(pos, pos + 1);
        }
        setChanged();
        notifyObservers();
    }

    public void goLeft() {
        if (pos != 0) {
            pos--;
        }
        setChanged();
        notifyObservers();
    }

    public void goRight() {
        if (pos != line.length()) {
            pos++;
        }
        setChanged();
        notifyObservers();
    }

    public void goHome() {
        pos = 0;
        setChanged();
        notifyObservers();
    }

    public void goEnd() {
        pos = line.length() - 1;
        setChanged();
        notifyObservers();
    }

}
