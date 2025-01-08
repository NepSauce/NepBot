package nepbot;

import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Event extends ListenerAdapter{
    public static String[] splitMessage;
    
    @SuppressWarnings("null")
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if (event.getAuthor().isBot()){
            return;
        }
    
        String message = event.getMessage().getContentRaw();
        String messageLower = message.toLowerCase();
        List<String> negativeWords = Arrays.asList("suck", "stupid", "dumb", "awful","trash", 
        "garbage", "useless", "weak", "lame", "cringe", "pathetic", "toxic", "shit", "sucks", "gay", 
        "ugly", "horrible", "disgusting", "awful", "ridiculous", "stupid", "annoying", "gay", "worthless", 
        "fail", "dead", "screwed", "unreal", "unwanted", "unhelpful", "unvalued", "unnecessary", "bad", 
        "ass");
    
        splitMessage = event.getMessage().getContentRaw().toLowerCase().split(" ");
    
        if (message.toLowerCase().contains("rot")){
            onRotReceived(event);
        }
    
        else if (messageLower.contains("nep")){
            boolean containsBadWord = false;

            for (String badWord : negativeWords){
                if (messageLower.contains(badWord)){
                containsBadWord = true;
                break;
                }
            }

            if (containsBadWord) {
                if (messageLower.contains("not")){
                    for (String badWord : negativeWords){
                        if (messageLower.indexOf("nep") < messageLower.indexOf("not") 
                        && messageLower.indexOf("not") < messageLower.indexOf(badWord)){
                            return;
                        }
                    }
                }

            onNegativeReceived(event);
            }
        }
    
        else if (splitMessage[0].contains("nep") && splitMessage.length == 1){
            onNepReceived(event);
        }
    }
    
    private void onRotReceived(@NotNull MessageReceivedEvent event){
        event.getChannel().sendMessage("The Rot Consumes.").queue();
    }
    
    private void onNepReceived(@NotNull MessageReceivedEvent event){
        event.getChannel().sendMessage("Nep Nep").queue();  
    }
    
    private void onNegativeReceived(@NotNull MessageReceivedEvent event){
        int random = RandomSeed.RandomGenUsingSeed(FileHandler.getLineCount("NegativeStatements.txt"));
        String statement = NegativeStatement.sayNegative("NegativeStatements.txt", random);
        event.getChannel().sendMessage(statement).queue();
    }
}
