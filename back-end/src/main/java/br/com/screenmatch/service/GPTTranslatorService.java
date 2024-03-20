package br.com.screenmatch.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

public class GPTTranslatorService {
    public static String translate(String text) {
        // Initialize the OpenAI service with your API key
        OpenAiService service = new OpenAiService(System.getenv("OPENAI_APIKEY"));

        // Create a completion request for the OpenAI API using the recommended replacement model
        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("traduza o seguinte texto para o português: " + text)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        // Make the completion request to the OpenAI API
        var response = service.createCompletion(request);

        // Return the text of the first choice in the response
        return response.getChoices().get(0).getText();
    }
}
