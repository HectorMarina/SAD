package P1;

import java.util.Observable;
import java.util.Observer;

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
