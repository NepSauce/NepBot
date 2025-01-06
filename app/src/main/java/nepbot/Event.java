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

        Pattern pattern = Pattern.compile("(?=.*n*e*p*)(?=.*g*a*y*)");
        Matcher matcher = pattern.matcher(message);

        if (messageLower.contains("nep") && messageLower.contains("gay")) {
            if (messageLower.contains("nep") && messageLower.contains("gay") && messageLower.contains("not")) {
                if (messageLower.indexOf("nep") < messageLower.indexOf("not") && messageLower.indexOf("not") < messageLower.indexOf("gay")) {
                    return;
                }
            }
            event.getChannel().sendMessage("WHAT DO YOU MEAN??").queue();
        }

        else if (messageLower.contains("nep nep")){
            event.getChannel().sendMessage("Nep Nep").queue();
        }
    }
}
