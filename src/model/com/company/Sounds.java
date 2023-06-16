package model.com.company;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sounds {

    public void reproduceOK() {

        File archivo = new File("src/audio/ok.wav");

        if (archivo.exists()) {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

                clip.addLineListener(new LineListener() {
                    public void update(LineEvent evento) {
                        if (evento.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reproduceNO() {
        File archivo = new File("src/audio/no.wav");

        if (archivo.exists()) {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();

                clip.addLineListener(new LineListener() {
                    public void update(LineEvent evento) {
                        if (evento.getType() == LineEvent.Type.STOP) {
                            clip.close();
                        }
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reproduceBreak() {
        File archivo = new File("src/audio/break.wav");
        if (archivo.exists()) {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            }  catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reproduceSFX() {
        File archivo = new File("src/audio/sfx.wav");
        if (archivo.exists()) {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            }  catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reproduceExito() {
        File archivo = new File("src/audio/exito.wav");
        if (archivo.exists()) {
            try {
                AudioInputStream audio = AudioSystem.getAudioInputStream(archivo);
                Clip clip = AudioSystem.getClip();
                clip.open(audio);
                clip.start();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}
