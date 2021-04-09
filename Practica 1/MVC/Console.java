/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import Constants.Keyboard;
import java.util.Observable;
import java.util.Observer;

public class Console implements Observer {

    

    public Console(Line line) {
        
    }

    @Override
    public void update(Observable o, Object arg) {
        int cmd = (int) arg;
        switch (cmd) {
            case Keyboard.LEFT:
                System.out.println();
                break;
            case Keyboard.RIGHT:
                System.out.println();
                break;
            case Keyboard.HOME:
                System.out.println();
                break;
            case Keyboard.END:
                System.out.println();
                break;
            case Keyboard.INS:
                System.out.println();
                break;
            case Keyboard.BKSP:
                System.out.println();
                break;
            
        }
    }
}
