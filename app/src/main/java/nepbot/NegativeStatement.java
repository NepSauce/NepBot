package nepbot;

public class NegativeStatement extends Statement{
    public static String sayNegative(String fileName, int seed){
        String sentence;
        FileHandler<String> fileHandler = new FileHandler<>();
        sentence = fileHandler.readRandomSingleLine(fileName, seed);
        return sentence;
    }
}