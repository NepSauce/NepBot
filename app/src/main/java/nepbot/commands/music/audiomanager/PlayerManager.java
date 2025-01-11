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

import net.dv8tion.jda.api.entities.Guild;

public class PlayerManager{
    private static PlayerManager INSTANCE;
    private final Map<Long, MusicManager> musicMap = new HashMap<>();
    private final AudioPlayerManager playerManager = new DefaultAudioPlayerManager();

    private PlayerManager(){
        AudioSourceManagers.registerLocalSource(playerManager);
        AudioSourceManagers.registerRemoteSources(playerManager);
    }

    @SuppressWarnings("unused")
    public MusicManager getMusicManager(Guild guild){
        return musicMap.computeIfAbsent(guild.getIdLong(), (guildId) -> {
            MusicManager manager = new MusicManager(playerManager);
            guild.getAudioManager().setSendingHandler(manager.getAudioForwarder());
            return manager;
        });
    }

    public void play(Guild guild, String trackURL){
        MusicManager musicManager = getMusicManager(guild);
        playerManager.loadItemOrdered(musicManager, trackURL, new AudioLoadResultHandler(){

            @Override
            public void trackLoaded(AudioTrack track){
                musicManager.getAudioScheduler().queue(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist){
            }

            @Override
            public void noMatches() {
            }

            @Override
            public void loadFailed(FriendlyException exception){
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