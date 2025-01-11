package nepbot.commands;

import org.jetbrains.annotations.NotNull;

import nepbot.Event;
import nepbot.commands.dad.DadCommand;
import nepbot.commands.help.HelpCommand;
import nepbot.commands.music.MoveCommand;
import nepbot.commands.music.audiomanager.YTPlayer;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CommandEvent extends ListenerAdapter{
    private static String[] splitMessage;
    private static Boolean dadBotMode = true;
    private final YTPlayer audioManager = new YTPlayer();
    
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
    
                if(member != null){
                    MoveCommand.joinChannel(channel, guild);
                    event.getChannel().sendMessage("Nep Joined The Current Voice Channel.").queue();

                    if (splitMessage.length < 4){
                        if (splitMessage[1].equalsIgnoreCase("find")){
                            String trackURL = splitMessage[1];
                            System.out.print(trackURL);

                            if (channel != null){
                                audioManager.loadVideo(trackURL, event);
                            }
                            else{
                                event.getChannel().sendMessage("Nep Thinks You Should Join a Voice Channel First.").queue();
                            }
                        }

                        if (splitMessage[1].equalsIgnoreCase("play")){
                            audioManager.playTrack(event);
                        }

                        if (splitMessage[1].equalsIgnoreCase("pause")){
                            audioManager.pauseTrack(event);
                        }
                    }
                    else{
                        event.getChannel().sendMessage("Nep Thinks That Command Is Invalid.").queue();
                    }
                }
                else{
                    event.getChannel().sendMessage("Join a Voice Channel First.").queue();
                }
            }
            else if (splitMessage[1].equalsIgnoreCase("yeet")){
                MoveCommand.leaveChannel(guild);
                event.getChannel().sendMessage("Nep Disconnected From Voice Channel.").queue();
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
