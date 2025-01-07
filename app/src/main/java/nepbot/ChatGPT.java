package nepbot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.URL;

public class ChatGPT{
    public static String generatePrompt(String prompt){
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "";

        FileHandler<String> tokenHandling = new FileHandler<>();
        apiKey = tokenHandling.readSingleLine("ChatGPT.txt");
        return "";
    }

}
