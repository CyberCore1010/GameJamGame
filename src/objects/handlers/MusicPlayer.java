package objects.handlers;

import net.beadsproject.beads.core.AudioContext;
import net.beadsproject.beads.data.Sample;
import net.beadsproject.beads.ugens.Gain;
import net.beadsproject.beads.ugens.Glide;
import net.beadsproject.beads.ugens.SamplePlayer;

public class MusicPlayer{
    private boolean playing;
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
        player.start();
        pause();
    }
    public void fade(float targetVol,int transition){
        fade.setValue(targetVol);
        fade.setGlideTime(transition);
    }
    public boolean isPlaying(){return(playing);}
    public void pause(){
        player.pause(true); playing=false;}
    public void resume() {player.pause(false); playing=true;}
    public void kill(){
        player.kill();
    }
}
