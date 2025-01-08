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
            try (Scanner newReader = new Scanner(newFile)) {
                if (newReader.hasNextLine()){
                    token = (T) newReader.nextLine();
                }
                else{
                    System.out.println("File Contents Are Empty"); 
                }
            }
            
        } 
        catch (FileNotFoundException e){
            System.out.println("File Was Not Found");
        }

        return token;
    }

    @SuppressWarnings("unchecked")
    public T readRandomSingleLine(String fileName, int randomIndex){
        T randomLine = null;
        int lineCount = getLineCount(fileName);

        if (lineCount == 0) {
            System.out.println("File Contents Are Empty");
            return randomLine;  
        }

        try {
            File newFile = new File(fileName);
            try (Scanner newReader = new Scanner(newFile)){
                for (int i = 0; i < randomIndex; i++){
                    newReader.nextLine(); 
                }

                if (newReader.hasNextLine()){
                    randomLine = (T) newReader.nextLine();
                }
            }
        } 
        catch (FileNotFoundException e){
            System.out.println("File Was Not Found");
        }

        return randomLine;
    }

    public static int getLineCount(String fileName){
        int lineCount = 0;

        try {
            File newFile = new File(fileName);
            try (Scanner newReader = new Scanner(newFile)) {
                while (newReader.hasNextLine()) {
                    newReader.nextLine();
                    lineCount++;
                }
            }
        } 
        catch (FileNotFoundException e){
            System.out.println("File Was Not Found");
        }

        return lineCount;
    }
}
