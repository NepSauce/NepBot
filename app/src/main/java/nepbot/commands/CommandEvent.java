package nepbot.commands;

import org.jetbrains.annotations.NotNull;

import nepbot.Event;
import nepbot.commands.dad.DadCommand;
import nepbot.commands.dad.HelpCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CommandEvent extends ListenerAdapter{
    public static String[] splitMessage;
    static Boolean dadBotMode = true;
    
    @SuppressWarnings("null")
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if (event.getAuthor().isBot()){
            return;
        }

        splitMessage = Event.getSplitMessage();
        Boolean containsNep = splitMessage[0].contains("!nep");

        if (containsNep && splitMessage[1].equalsIgnoreCase("dadMode")){
            DadCommand.onDadCommandReceived(event);
        }
        if (containsNep && splitMessage[1].equalsIgnoreCase("help")){
            HelpCommand.onHelpCommandReceived(event);
        }
    }
    
    public static Boolean getDadBotMode(){
        return dadBotMode;
    }

    public static void setDadBotMode(Boolean setMode){
        dadBotMode = setMode;
    }
}
