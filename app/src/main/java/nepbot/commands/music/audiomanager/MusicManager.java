package nepbot.commands.music.audiomanager;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;

public class MusicManager {
    private final YTForwarder forwarder;
    private final Scheduler scheduler;

    public MusicManager(AudioPlayerManager manager){
        AudioPlayer player = manager.createPlayer();
        scheduler = new Scheduler(player);
        player.addListener(scheduler);
        forwarder = new YTForwarder(player);
    }

    public Scheduler getAudioScheduler(){
        return scheduler;
    }

    public YTForwarder getAudioForwarder(){
        return forwarder;
    }
}
