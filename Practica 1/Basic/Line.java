/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.Basic;

/**
 *
 * @author Marina
 */
public class Line {
    
    String line;
    int pos;

    public Line() {
        line = new String();
        pos = 0;
    }

    String getLine() {

        return line;

    }

    int getPos() {
        return pos;
    }

    void insertChar() {

    }

    void deleteChar() {

    }

    void goLeft() {
        if (pos != 0) {
            pos--;
        }
    }

    void goRight() {
        if (pos != line.length()) {
            pos++;
        }
    }

    void goHome() {
        pos = 0;
    }

    void goEnd() {
        pos = line.length() - 1;
    }
}
