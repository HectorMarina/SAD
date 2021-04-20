
package TicTacToe;

import javax.swing.JFrame;


public class MainFrame extends JFrame{
    
    private Tablero mTablero = new Tablero();
    
    public MainFrame() {
        try {
            jbInit();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        
    }
    
    
}
