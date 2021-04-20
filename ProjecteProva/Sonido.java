/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1.projecte;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Marina
 */
public class Sonido {

    public void mouseClicked(MouseEvent evt) throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        if (evt.getModifiersEx() == MouseEvent.MOUSE_CLICKED) {
            try {

                // Se obtiene un Clip de sonido
                Clip sonido = AudioSystem.getClip();

                // Se carga con un fichero wav
                sonido.open(AudioSystem.getAudioInputStream(new File("ficha.mp3")));

                // Comienza la reproducción
                sonido.start();

                // Espera mientras se esté reproduciendo.
                while (sonido.isRunning()) {
                    Thread.sleep(1000);
                }

                // Se cierra el clip.
                sonido.close();
            } catch (Exception e) {
                System.out.println("" + e);
            }
        }
    }
}
