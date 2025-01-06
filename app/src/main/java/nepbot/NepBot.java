package nepbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

public class NepBot{
    public static void main(String[] args)
    throws LoginException{
        String fileName = "Token.txt";
        String token = tokenReader(fileName);
        JDA jda = JDABuilder.createDefault(token).build();
    }

    public static String tokenReader(String fileName){
        String token = "";

        try{
            File newFile = new File(fileName);
            Scanner newReader = new Scanner(newFile);
            
            if (newReader.hasNextLine()){
                token = newReader.nextLine();
            }
            else{
                System.out.println("File Contents Are Empty"); 
            }
            
        } 
        catch (FileNotFoundException e){
            System.out.println("File Was Not Found");
        }

        return token;
    }
}
