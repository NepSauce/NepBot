package nepbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Token {
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
