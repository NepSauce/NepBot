package nepbot.commands.music;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;

public class JoinCommand{
    public static void joinChannel(VoiceChannel channel, Guild guild){
        guild.getAudioManager().openAudioConnection(channel);
    }

    public static void leaveChannel(Guild guild){
        guild.getAudioManager().closeAudioConnection();
    }
}
