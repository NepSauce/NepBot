package nepbot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class NepBot{
    public static void main(String[] args)
    throws LoginException{
        String fileName = "Token.txt";
        String token = Token.tokenReader(fileName);

        JDA jda = JDABuilder.createDefault(token)
        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
        .build();
        jda.getPresence().setActivity(Activity.listening("Thy Mother"));
        jda.addEventListener(new Event());
        jda.addEventListener(new CommandEvent());
    }

}