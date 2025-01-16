package nepbot.commands.music.audiomanager;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class MusicManager {
    private final AudioForwarder forwarder;
    private final TrackScheduler scheduler;

    public MusicManager(AudioPlayerManager manager){
        AudioPlayer player = manager.createPlayer();

        if (player == null){
            throw new IllegalStateException("AudioPlayer could not be created.");
        }
        
        scheduler = new TrackScheduler(player);
        player.addListener(scheduler);
        forwarder = new AudioForwarder(player);
    }

    public TrackScheduler getTrackScheduler(){
        return scheduler;
    }

    public AudioForwarder getAudioForwarder(){
        return forwarder;
    }
}
