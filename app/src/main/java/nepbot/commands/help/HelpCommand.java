package nepbot.commands.help;

import org.jetbrains.annotations.NotNull;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class HelpCommand {
    public static void onHelpCommandReceived(@NotNull MessageReceivedEvent event) {
        String helpMessage = """
                **__Nepmata Command List__**
                
                **`!nep DadMode [True/False]:`** Toggle Dad Mode on or off.

                **`!nep Grab:`** Join your current voice channel.

                **`!nep [Play/Pause]:`** Play or pause the current track.

                **`!nep Find [Youtube Link]:`** Start playing the Youtube video.

                **`!nep Yeet:`** Remove Nepmata from the voice channel.

                """;

        event.getChannel().sendMessage(helpMessage).queue();
    }
}
