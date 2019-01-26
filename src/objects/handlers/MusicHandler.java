package objects.handlers;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.data.SampleManager;
import net.beadsproject.beads.ugens.*;

import java.util.HashMap;
import java.util.Scanner;

public class MusicHandler {
    private AudioContext ac;
    public Glide fade;
    private Gain playingTrack;
    public HashMap<String,Sample> trackList;

    public MusicHandler(){
        this.ac = new AudioContext();
        trackList = new HashMap<>();
//        fade = new Glide(ac,1,transitionSpeed);
        trackList.put("alarm",SampleManager.sample("res/audio/alarm.mp3"));
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
    }


//    public void playTrack(String track){
//        SamplePlayer player = new SamplePlayer(ac, trackList.get(track));
//        fade.setGlideTime(5000);
//        fade.setValue(1);
//        playingTrack = new Gain(ac,5,fade);
//        player.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS);
//        playingTrack.addInput(player);
//        ac.out.addInput(playingTrack);
//    }

    public AudioContext getAC(){
        return(ac);
    }

    public void start(){
        ac.start();
    }

    public void stop(){
        ac.stop();
    }

//    public void changeTrack(String track){
//        fade.setGlideTime(5000);
//        fade.setValue(0);
//        playingTrack.kill();
//        playTrack(track);
//    }

    //TEST function to check if audio playing and libs work
    public static void main(String[] args) {
        MusicHandler music = new MusicHandler();
        music.start();
        MusicPlayer track2 = new MusicPlayer(music.getAC(),music.trackList.get("night"),5,2000,true);
        MusicPlayer track1 = new MusicPlayer(music.getAC(),music.trackList.get("whisper1"),1,2000,true);
        Scanner sc = new Scanner(System.in);
        while(true){
            float i = sc.nextFloat();
            int n = sc.nextInt();
            track1.fade(i,n);
        }

    }
}

class MusicPlayer{
    private AudioContext ac;
    private Sample track;
    private SamplePlayer player;
    private Glide fade;
    private Gain g;

    public MusicPlayer(AudioContext ac, Sample track, float volume, int transitionSpeed, boolean loop){
        this.track = track;
        this.ac = ac;
        this.player = new SamplePlayer(ac,track);
        this.fade = new Glide(ac,volume,transitionSpeed);
        if(loop){
            player.setLoopType(SamplePlayer.LoopType.LOOP_FORWARDS); //loop forwards
        }else{
            player.setLoopType(SamplePlayer.LoopType.NO_LOOP_FORWARDS); //noloop
        }
        g = new Gain(ac,5,fade);
        g.addInput(player);
        ac.out.addInput(g);
    }
    public void fade(float targetVol,int transition){
        fade.setValue(targetVol);
        fade.setGlideTime(transition);
    }
    public void start(){
        player.start();
    }
    public void stop(){
        player.pause(true);
    }
    public void kill(){
        player.kill();
    }
}