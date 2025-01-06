package nepbot;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileHandler<T>{
    @SuppressWarnings("unchecked")
    public T readSingleLine(String fileName){
        T token = null;

        try{
            File newFile = new File(fileName);
            Scanner newReader = new Scanner(newFile);
            
            if (newReader.hasNextLine()){
                token = (T) newReader.nextLine();
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
