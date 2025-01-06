package nepbot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Event extends ListenerAdapter{
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if (event.getAuthor().isBot()){
            return;
        }
        String message = event.getMessage().getContentRaw();
        String messageLower = message.toLowerCase();
        System.out.println(messageLower);

        Pattern pattern = Pattern.compile("(?=.*nep)(?=.*gay)");
        Matcher matcher = pattern.matcher(message);

        if (messageLower.contains("nep") && messageLower.contains("gay")) {
            // Check for negation: if "not" is present between "nep" and "gay", do not trigger the response
            if (messageLower.contains("nep") && messageLower.contains("gay") && messageLower.contains("not")) {
                // Don't trigger response if "nep" and "gay" are connected by "not"
                if (messageLower.indexOf("nep") < messageLower.indexOf("not") && messageLower.indexOf("not") < messageLower.indexOf("gay")) {
                    // If "nep" is followed by "not" and then "gay", ignore the message
                    return;
                }
            }
            // If no negation is detected, respond with "WHAT DO YOU MEAN"
            event.getChannel().sendMessage("WHAT DO YOU MEAN??").queue();
        }
        
        else if (messageLower.contains("nep nep")){
            event.getChannel().sendMessage("Nep Nep").queue();
        }
    }
}
