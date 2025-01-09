package nepbot.commands.dad;

import org.jetbrains.annotations.NotNull;

import nepbot.Event;
import nepbot.commands.CommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class DadCommand {
    public static String[] splitString;

    public static void onDadCommandReceived(@NotNull MessageReceivedEvent event){
        splitString = Event.getSplitMessage();
        
        if (splitString.length == 2){
            if (CommandEvent.getDadBotMode()){
                event.getChannel().sendMessage("`Dad Bot Mode is Enabled`").queue();
            }
            else{
                event.getChannel().sendMessage("`Dad Bot Mode is Disabled`").queue();
            }
        }
        else if (splitString[2].equalsIgnoreCase("true" )){
            CommandEvent.setDadBotMode(true);
            event.getChannel().sendMessage("`Dad Bot Mode Enabled.`").queue();
        }
        else if (splitString[2].equalsIgnoreCase("false" )){
            event.getChannel().sendMessage("`Dad Bot Mode Disabled`").queue();
        }
        else{
            event.getChannel().sendMessage("`Not a Valid Command`").queue();
        }
    }
}
