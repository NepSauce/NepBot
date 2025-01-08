package nepbot.commands;

import nepbot.FileHandler;

public class ChatGPT{
    public static String generatePrompt(String prompt){
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey;
        FileHandler<String> keyHandling = new FileHandler<>();
        apiKey = keyHandling.readSingleLine("ChatGPT.txt");

        return "";
    }

}
