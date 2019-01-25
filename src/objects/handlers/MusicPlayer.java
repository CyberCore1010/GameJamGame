package objects.handlers;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.ugens.SamplePlayer;

public class MusicPlayer {
    private AudioContext ac;
    private Gain g;

    public MusicPlayer(){
        this.ac = new AudioContext();
        this.g = new Gain(ac, 5, 1f);
    }


    public void playSong(String track){
        String file = "res/audio/" + track + ".mp3";
        SamplePlayer player = new SamplePlayer(this.ac, SampleManager.sample(file));

        g.addInput(player);
        ac.out.addInput(g);
    }


    public void start(){
        ac.start();
    }
    public void stop(){
        ac.stop();
    }

    //TEST function to check if audio playing and libs work
    public static void main(String[] args) {

        MusicPlayer music = new MusicPlayer();
        music.playSong("doom1");
        music.start();



//        AudioContext ac;
//
//        ac = new AudioContext();
//
//        Noise n = new Noise(ac);
//
//        Gain g = new Gain(ac, 1, 0.1f);
//
//        g.addInput(n);
//        ac.out.addInput(g);
//
//        ac.start();
    }
}
