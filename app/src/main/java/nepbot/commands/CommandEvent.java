package nepbot.commands;

import org.jetbrains.annotations.NotNull;

import nepbot.Event;
import nepbot.commands.dad.DadCommand;
import nepbot.commands.help.HelpCommand;
import nepbot.commands.music.MoveCommand;
import nepbot.commands.music.audiomanager.PlayerManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CommandEvent extends ListenerAdapter{
    private static String[] splitMessage;
    private static Boolean dadBotMode = true;
    
    
    @SuppressWarnings({ "null", "unused" })
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if (event.getAuthor().isBot()){
            return;
        }
    
        splitMessage = Event.getSplitMessage(); // Ensure this returns the correct data.
        if (splitMessage == null || splitMessage.length < 2){
            event.getChannel().sendMessage("Nep Thinks You Should Provide a Command After `!nep`.").queue();
            return;
        }
    
        Guild guild = event.getGuild();
        User member = event.getAuthor();
        VoiceChannel channel = event.getMember() != null && event.getMember().getVoiceState() != null
                ? (VoiceChannel) event.getMember().getVoiceState().getChannel()
                : null;
    
        if (splitMessage[0].contains("!nep")){
            switch (splitMessage[1].toLowerCase()){
                case "dadmode" -> DadCommand.onDadCommandReceived(event);
                case "help" -> HelpCommand.onHelpCommandReceived(event);
                case "grab" -> {
                    if (channel != null) {
                        MoveCommand.joinChannel(channel, guild);
                        event.getChannel().sendMessage("Nep Joined The Current Voice Channel.").queue();
                    } else {
                        event.getChannel().sendMessage("Nep Thinks That Command Is Invalid.").queue();
                    }
                }
                case "find" -> {
                    if (splitMessage.length < 3){
                        event.getChannel().sendMessage("Nep Thinks You Should Provide a Track URL.").queue();
                        return;
                    }
                    String trackURL = splitMessage[2];
                    if (channel != null){
                        PlayerManager playerManager = PlayerManager.getPlayer();
                        playerManager.play(guild, trackURL);
                    } 
                    else{
                        event.getChannel().sendMessage("Nep Thinks You Should Join a Voice Channel First.").queue();
                    }
                }
                case "yeet" -> {
                    MoveCommand.leaveChannel(guild);
                    event.getChannel().sendMessage("Nep Disconnected From Voice Channel.").queue();
                }
                default -> event.getChannel().sendMessage("Nep Doesn't Recognize This Command.").queue();
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
