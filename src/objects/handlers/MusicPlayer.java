package objects.handlers;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.*;

import java.util.HashMap;
import java.util.Scanner;

public class MusicPlayer {
    private AudioContext ac;
    public Glide fade;
    private Gain playingTrack;
    private HashMap<String,Sample> trackList;

    public MusicPlayer(){
        this.ac = new AudioContext();
        trackList = new HashMap<>();
        fade = new Glide(ac,1);
        trackList.put("doom1",SampleManager.sample("res/audio/doom1.mp3"));
        trackList.put("doom2",SampleManager.sample("res/audio/doom2.mp3"));
    }


    public void playTrack(String track){
//		SampleManager.setBufferingRegime(Sample.Regime.newStreamingRegime(1000));
        SamplePlayer player = new SamplePlayer(ac, trackList.get(track));
//        fade.setGlideTime(5000);
//        fade.setValue(1);
        playingTrack = new Gain(ac,5,fade);
        playingTrack.addInput(player);
        ac.out.addInput(playingTrack);
    }

    public void changeTrack(String track){
//        fade.setGlideTime(5000);
//        fade.setValue(0);
        playingTrack.kill();
        playTrack(track);
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
            float i = sc.nextFloat();
            music.fade.setValue(i);
//            music.changeTrack(i);
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
