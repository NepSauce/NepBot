package nepbot.commands.dad;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand {
    public static void onHelpCommandReceived(@NotNull MessageReceivedEvent event){
        event.getChannel().sendMessage("**`List of Commands`**").queue();
        event.getChannel().sendMessage("`!nep DadMode [True/False]:` Enables Dad Mode").queue();
        event.getChannel().sendMessage("`!nep Start [Youtube Link]:` Joins Current Voice Channel").queue();
        event.getChannel().sendMessage("`!nep [Play/Pause]:` Plays or Pauses Music").queue();
        event.getChannel().sendMessage("`!nep Yeet`: Removes Nepmata from Voice Channel").queue();
    }
}
