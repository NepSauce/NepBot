package nepbot;

public class ChatGPT{
    public static String generatePrompt(String prompt){
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "";

        FileHandler<String> tokenHandling = new FileHandler<>();
        apiKey = tokenHandling.readSingleLine("ChatGPT.txt");
        return "";
    }

}
