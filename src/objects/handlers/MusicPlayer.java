package objects.handlers;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.CrossFade;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Noise;
import net.beadsproject.beads.ugens.SamplePlayer;

import java.util.Scanner;

public class MusicPlayer {
    private AudioContext ac;
//    private Gain g;

    public MusicPlayer(){
        this.ac = new AudioContext();
//        this.g = new Gain(ac, 5, 1f);
    }


    public void playTrack(String track){
        String file = "res/audio/" + track + ".mp3";
        SamplePlayer player = new SamplePlayer(this.ac, SampleManager.sample(file));

        Gain g = new Gain(ac,5,1f);
        g.addInput(player);
        ac.out.addInput(g);
    }

    public void changeTrack(String track){
        String file = "res/audio/" + track + ".mp3";
        SamplePlayer player = new SamplePlayer(this.ac, SampleManager.sample(file));
        CrossFade fade = new CrossFade(ac,2);

        Gain g = new Gain(ac,5,1f);
        g.addInput(player);
        fade.fadeTo(g,1000);
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
        music.playTrack("doom1");
        music.start();
        Scanner sc = new Scanner(System.in);
        while(true){
            String i = sc.next();
            music.changeTrack(i);
        }


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
