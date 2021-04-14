/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.MVC;

import java.util.Observable;
import java.util.Observer;
import practica1.Constants.Key;
import practica1.Constants.Keyboard;

/**
 *
 * @author Marina
 */
public class Console implements Observer {
    //private Line line;

    public Console(Line line) {
        //this.line = line;
    }

    @Override
    public void update(Observable o, Object arg) {
        
        int cmd = (int) arg;
        switch (cmd) {
            case Keyboard.LEFT:
                System.out.println(Key.LEFT);
                break;
            case Keyboard.RIGHT:
                System.out.println(Key.RIGHT);
                break;
            case Keyboard.HOME:
                System.out.println(Key.HOME);
                break;
            case Keyboard.END:
                System.out.println(Key.END);
                break;
            case Keyboard.INS:
                System.out.println(Key.INS);
                break;
            case Keyboard.BKSP:
                System.out.println(Key.BKSP);
                break;
        }
    }
}
