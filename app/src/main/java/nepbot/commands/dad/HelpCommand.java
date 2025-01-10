package nepbot.commands.dad;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand {
    public static void onHelpCommandReceived(@NotNull MessageReceivedEvent event) {
        String helpMessage = """
                **__Nepmata Command List__**
                
                **`!nep DadMode [True/False]:`** Toggle Dad Mode on or off.

                **`!nep [Play/Pause]:`** Play or pause the current track.

                **`!nep Start [Youtube Link]:`** Join your current voice channel and start playing music.

                **`!nep Yeet:`** Remove Nepmata from the voice channel.

                """;

        event.getChannel().sendMessage(helpMessage).queue();
    }
}
