package nepbot;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Event extends ListenerAdapter{
    String[] splitMessage;
    static Boolean dadBotMode = false;
    
    @SuppressWarnings("null")
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event){
        if (event.getAuthor().isBot()){
            return;
        }
    
        String message = event.getMessage().getContentRaw();
        String messageLower = message.toLowerCase();
    
        splitMessage = event.getMessage().getContentRaw().toLowerCase().split(" ");
    
        if (message.toLowerCase().contains("rot")){
            onRotReceived(event);
        }
    
        else if (messageLower.contains("nep") && messageLower.contains("gay")) {
            if (messageLower.contains("nep") && messageLower.contains("gay") && messageLower.contains("not")) {
                if (messageLower.indexOf("nep") < messageLower.indexOf("not") && messageLower.indexOf("not") < messageLower.indexOf("gay")) {
                    return;
                }
            }
            onNegativeReceived(event);
        }
    
        else if (splitMessage[0].contains("nep")){
            onNepReceived(event);
        }
    }
    
    private void onRotReceived(@NotNull MessageReceivedEvent event){
        event.getChannel().sendMessage("The Rot Consumes.").queue();
    }
    
    private void onNepReceived(@NotNull MessageReceivedEvent event){
        if (splitMessage[0].equalsIgnoreCase("nep") && splitMessage.length == 1){
            event.getChannel().sendMessage("Nep Nep").queue();
        }
    }
    
    private void onNegativeReceived(@NotNull MessageReceivedEvent event){
        int random = RandomSeed.RandomGenUsingSeed(FileHandler.getLineCount("NegativeStatements.txt"));
        String statement = NegativeStatement.sayNegative("NegativeStatements.txt", random);
        event.getChannel().sendMessage(statement).queue();
    }
    
    public static Boolean getDadMode(){
        return dadBotMode;
    }
    

}
