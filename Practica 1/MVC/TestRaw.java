/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.MVC;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Marina
 */
public class TestRaw {
    
    public static void main(String args[]) throws IOException {

          EditableBufferedReader prueba = new EditableBufferedReader( new InputStreamReader(System.in));
        prueba.setRaw();
        //prueba.unsetRaw();
    }
}
