/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.MVC;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Marina
 */
public class Console implements Observer {
    private Line line;

    public Console(Line line) {
        this.line = line;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
