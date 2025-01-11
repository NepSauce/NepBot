package nepbot.commands.music.audiomanager;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

public class Scheduler extends AudioEventAdapter{
    private final AudioPlayer player;
    private final BlockingQueue<AudioTrack> queue = new LinkedBlockingDeque<>();

    public Scheduler(AudioPlayer player){
        this.player = player;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason reason){
        player.startTrack(queue.poll(), false);
    }

    public void queue(AudioTrack track){
        if (!player.startTrack(track, true));
        queue.offer(track);
    }
}
