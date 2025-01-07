package nepbot.commands;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class CommandEvent extends ListenerAdapter{
    String[] splitMessage;
    static Boolean dadBotMode = false;
    
    @SuppressWarnings("null")
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        splitMessage = event.getMessage().getContentRaw().toLowerCase().split(" ");

        if (splitMessage[0].contains("!nep") && splitMessage[1].equalsIgnoreCase("dadMode")){
            onDadReceived(event);
        }
    }

    public void onDadReceived(@NotNull MessageReceivedEvent event){
        if (splitMessage[2].equalsIgnoreCase("true" ) && dadBotMode == false){
            dadBotMode = true;
            event.getChannel().sendMessage("`Dad Bot Mode enabled.`").queue();
        }
        else if (splitMessage[2].equalsIgnoreCase("false" ) && dadBotMode == true){
            dadBotMode = false;
            event.getChannel().sendMessage("`Dad Bot Mode disabled.`").queue();
        }
        else{
            event.getChannel().sendMessage("`Dad Bot Mode is already disabled/enabled.`").queue();
        }
    }
}
