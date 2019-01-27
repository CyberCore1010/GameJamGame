package objects.handlers;

import game.Game;
import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.*;

import java.util.HashMap;
import java.util.Scanner;

public class MusicHandler {
    private AudioContext ac;
    private Game game;
    public HashMap<String,Sample> trackList;
    //music:
    public MusicPlayer playerWalking;

    public MusicHandler(){
        this.ac = new AudioContext();
//        this.game = g;
        trackList = new HashMap<>();
        trackList.put("alarm",SampleManager.sample("res/audio/alarm.mp3"));
        trackList.put("music",SampleManager.sample("res/audio/music.mp3"));
        trackList.put("nailing",SampleManager.sample("res/audio/nailing.mp3"));
        trackList.put("night",SampleManager.sample("res/audio/night.mp3"));
        trackList.put("playerSteps",SampleManager.sample("res/audio/playerSteps.mp3"));
        trackList.put("stairs",SampleManager.sample("res/audio/stairs.mp3"));
        trackList.put("tripwire",SampleManager.sample("res/audio/tripwire.mp3"));
        trackList.put("whisper1",SampleManager.sample("res/audio/whisper1.mp3"));
        trackList.put("whisper1Right",SampleManager.sample("res/audio/whisper1Right.mp3"));
        trackList.put("whisper1Left",SampleManager.sample("res/audio/whisper1Left.mp3"));
        trackList.put("whisper2",SampleManager.sample("res/audio/whisper2.mp3"));
        trackList.put("windup",SampleManager.sample("res/audio/windup.mp3"));
        trackList.put("woosh",SampleManager.sample("res/audio/woosh.mp3"));
//        initSounds();
    }

//    private void initSounds(){
//        playerWalking = new MusicPlayer(ac,trackList.get("playerSteps"),4,1,true);
//    }

    public AudioContext getAC(){
        System.out.println("Getting AC");return(ac);
    }

    public Sample getTrack(String s){
        System.out.println(s);
        return(trackList.get(s));
    }

    public void start(){
        ac.start();
    }

    public void stop(){
        ac.stop();
    }


//    TEST function to check if audio playing and libs work
    public static void main(String[] args) {
        MusicHandler music = new MusicHandler();
        music.start();
        MusicPlayer track2 = new MusicPlayer(music.getAC(),music.trackList.get("night"),2,2000,true);
//        MusicPlayer track1 = new MusicPlayer(music.getAC(),music.trackList.get("whisper1"),1,2000,true);
        Scanner sc = new Scanner(System.in);
        while(true){
            int i = sc.nextInt();
            if(i==1){
                music.playerWalking.resume();
            }else{
                music.playerWalking.pause();
            }
        }

    }
}

