/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.MVC;

import java.util.Observable;

/**
 *
 * @author Marina
 */
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
