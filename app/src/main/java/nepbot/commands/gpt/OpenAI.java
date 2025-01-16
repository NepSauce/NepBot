package nepbot.commands.gpt;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import nepbot.FileHandler;

public class OpenAI{
    @SuppressWarnings("deprecation")
    public static void main(String[] args) throws Exception{
        String apiKey;
        FileHandler<String> key = new FileHandler<>();
        apiKey = key.readSingleLine("ChatGPT.txt");
        String model = "gpt-3.5-turbo";  

        // Define the request body (JSON format)
        String jsonBody = "{\n" +"  \"model\": \"" + model + "\",\n" +"  \"messages\": [{\"role\": \"user\", \"content\": \"What's the capital of France?\"}],\n" +"  \"max_tokens\": 100\n" +"}";

        // Create the URL and open the connection
        URL url = new URL("https://api.openai.com/v1/chat/completions");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + apiKey);
        connection.setDoOutput(true);

        // Write the JSON body
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Read the response
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            System.out.println("Response from OpenAI: " + response.toString());
        }
    }
}
