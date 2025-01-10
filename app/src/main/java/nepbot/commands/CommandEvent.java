package nepbot.commands;

import java.lang.reflect.Member;

import org.jetbrains.annotations.NotNull;

import nepbot.Event;
import nepbot.commands.dad.DadCommand;
import nepbot.commands.dad.HelpCommand;
import nepbot.commands.music.JoinCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;


public class CommandEvent extends ListenerAdapter{
    public static String[] splitMessage;
    static Boolean dadBotMode = true;
    
    @SuppressWarnings({ "null", "unused" })
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        Guild guild = event.getGuild();
        User member = event.getAuthor();

        if (event.getAuthor().isBot()){
            return;
        }

        splitMessage = Event.getSplitMessage();
        Boolean containsNep = splitMessage[0].contains("!nep");

        if (containsNep){
            if (splitMessage[1].equalsIgnoreCase("dadMode")){
                DadCommand.onDadCommandReceived(event);
            }
            else if (splitMessage[1].equalsIgnoreCase("help")){
                HelpCommand.onHelpCommandReceived(event);
            }
            else if (splitMessage[1].equalsIgnoreCase("grab")){
                VoiceChannel channel = event.getMember().getVoiceState().getChannel().asVoiceChannel();
    
                if(channel != null && member != null){
                    JoinCommand.joinChannel(channel, guild);
                    event.getChannel().sendMessage("-Joined Current Voice Channel").queue();
                }
                else{
                    event.getChannel().sendMessage("-Join a Voice Channel First.").queue();;
                }
            }
            else if (splitMessage[1].equalsIgnoreCase("yeet")){
                JoinCommand.leaveChannel(guild);
                event.getChannel().sendMessage("-Disconnected From Voice Channel").queue();
            }
        }

    }
    
    public static Boolean getDadBotMode(){
        return dadBotMode;
    }

    public static void setDadBotMode(Boolean setMode){
        dadBotMode = setMode;
    }
}
