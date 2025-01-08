package nepbot;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class BotStatement extends ListenerAdapter{
    public static String getRandomNegative(String fileName, int seed){
        String sentence;
        FileHandler<String> fileHandler = new FileHandler<>();
        sentence = fileHandler.readRandomSingleLine(fileName, seed);
        return sentence;
    }

    public static String getRandomPositive(String fileName, int seed){
        String sentence;
        FileHandler<String> fileHandler = new FileHandler<>();
        sentence = fileHandler.readRandomSingleLine(fileName, seed);
        return sentence;
    }

    public static void onRotReceived(@NotNull MessageReceivedEvent event){
        event.getChannel().sendMessage("The Rot Consumes.").queue();
    }
    
    public static void onNepReceived(@NotNull MessageReceivedEvent event){
        event.getChannel().sendMessage("Nep Nep").queue();  
    }
    
    public static void onNegativeReceived(@NotNull MessageReceivedEvent event){
        int random = RandomSeed.RandomGenUsingSeed(FileHandler.getLineCount("NegativeStatements.txt"));
        String statement = BotStatement.getRandomNegative("NegativeStatements.txt", random);
        event.getChannel().sendMessage(statement).queue();
    }

    public static void onPositiveReceived(@NotNull MessageReceivedEvent event){
        int random = RandomSeed.RandomGenUsingSeed(FileHandler.getLineCount("PositiveStatements.txt"));
        String statement = BotStatement.getRandomNegative("PositiveStatements.txt", random);
        event.getChannel().sendMessage(statement).queue();
    }
}