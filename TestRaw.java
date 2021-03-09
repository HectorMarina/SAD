package P1;

import java.io.IOException;
import java.io.InputStreamReader;

public class TestRaw {
      public static void main(String args[]) throws IOException {

          EditableBufferedReader prueba = new EditableBufferedReader( new InputStreamReader(System.in));
        prueba.setRaw();
        //prueba.unsetRaw();
    }
    
}
