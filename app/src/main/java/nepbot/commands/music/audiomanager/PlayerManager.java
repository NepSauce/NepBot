package nepbot.commands.music.audiomanager;

import java.util.HashMap;
import java.util.Map;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;

public class PlayerManager{
    private static PlayerManager INSTANCE;
    @SuppressWarnings("FieldMayBeFinal")
    private Map<Long, MusicManager> guildMusicManagers = new HashMap<>();
    @SuppressWarnings("FieldMayBeFinal")
    private AudioPlayerManager audioPlayerManager = new DefaultAudioPlayerManager();

    private PlayerManager(){
        AudioSourceManagers.registerLocalSource(audioPlayerManager);
        AudioSourceManagers.registerRemoteSources(audioPlayerManager);
    }

    @SuppressWarnings("unused")
    public MusicManager getGuildMusicManager(Guild guild){
        return guildMusicManagers.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            MusicManager musicManager = new MusicManager(audioPlayerManager);
            guild.getAudioManager().setSendingHandler((AudioSendHandler) musicManager.getAudioForwarder());
            return musicManager;
        });
    }

    public void play(Guild guild, String trackURL){
        MusicManager guildMusicManager = getGuildMusicManager(guild);
        audioPlayerManager.loadItemOrdered(guildMusicManager, trackURL, new AudioLoadResultHandler(){

            @Override
            public void trackLoaded(AudioTrack track){
                guildMusicManager.getTrackScheduler().queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist){
            }

            @Override
            public void noMatches(){
                System.out.println("No matches found for the track URL.");
            }

            @Override
            public void loadFailed(FriendlyException exception){
                System.out.println("Failed to load track: " + exception.getMessage());
            }

        });
    }

    public static PlayerManager getPlayer(){
        if (INSTANCE == null){
            INSTANCE = new PlayerManager();
        }
        return INSTANCE;
    }
}