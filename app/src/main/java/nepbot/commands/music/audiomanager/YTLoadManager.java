package nepbot.commands.music.audiomanager;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class YTLoadHandler implements AudioLoadResultHandler{
    private final AudioPlayer player;
    private final MessageReceivedEvent event;

    public YTLoadHandler(AudioPlayer player, MessageReceivedEvent event){
        this.player = player;
        this.event= event;
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        player.playTrack(track);
        event.getChannel().sendMessage("Nep Is Playing: " + track.getInfo().title).queue();
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        AudioTrack firstTrack = playlist.getTracks().get(0);
        player.playTrack(firstTrack);
        event.getChannel().sendMessage("Nep Is Playing: " + firstTrack.getInfo().title).queue();
    }

    @Override
    public void noMatches() {
        event.getChannel().sendMessage("Nep Couldn't Find a Match").queue();
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        event.getChannel().sendMessage("Nep Couldn't Load The Track " + exception.getMessage()).queue();
    }
    
}
