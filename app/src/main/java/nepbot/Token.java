package nepbot;

public class Token {
    public static String tokenReader(String fileName){
        String token = "";
        FileHandler<String> tokenHandling = new FileHandler<String>();
        token = tokenHandling.readSingleLine("Token.txt");

        return token;
    }
}