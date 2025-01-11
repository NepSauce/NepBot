package nepbot.commands.music.audiomanager;

import java.nio.ByteBuffer;

import org.jetbrains.annotations.Nullable;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.playback.MutableAudioFrame;

import net.dv8tion.jda.api.audio.AudioSendHandler;

public class AudioForwarder implements AudioSendHandler{
    private final AudioPlayer player;
    private final ByteBuffer buffer = ByteBuffer.allocate(1024);
    private final MutableAudioFrame frame = new MutableAudioFrame();

    public AudioForwarder(AudioPlayer player){
        this.player = player;
        frame.setBuffer(buffer);
    }

    @Override
    public boolean canProvide(){
        return player.provide(frame);
    }

    @Nullable
    @Override
    public ByteBuffer provide20MsAudio() {
        return buffer.flip();
    }

    @Override
    public boolean isOpus(){
        return true;
    }
}
