package controllers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public class SoundController {
    private Clip[] clip;
    private float sfx = 0;
    private float music = 0;



    private File[] files = {
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\bg.wav"),
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\select.wav"),
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\enter.wav"),
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\start.wav"),
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\win.wav"),
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\correct.wav"),
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\lose.wav"),
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\click.wav"),
            new File("C:\\Users\\admin\\Desktop\\JAVA\\sodoku5\\src\\sounds\\wrong.wav")
    };

    public SoundController(float sfx, float music) {
        this.sfx=sfx;
        this.music=music;
        clip = new Clip[files.length];

        for (int i = 0; i < files.length; i++) {
            try {
                if (files[i].exists()) {
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(files[i]);
                    clip[i] = AudioSystem.getClip();
                    clip[i].open(audioInputStream);
                } else {
                    System.out.println("File" + i + " Not Found!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public void start(int i){
        FloatControl volume = (FloatControl) clip[i].getControl(FloatControl.Type.MASTER_GAIN);
        if(i==0){
            volume.setValue(20f * (float) Math.log10(music));
            clip[i].loop(Clip.LOOP_CONTINUOUSLY);
            clip[i].start();
        }else{
            volume.setValue(20f * (float) Math.log10(sfx));
            clip[i].start();
        }

    }

    public Clip[] getClip() {
        return clip;
    }


}
