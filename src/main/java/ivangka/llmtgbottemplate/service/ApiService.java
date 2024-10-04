package ivangka.llmtgbottemplate.service;

import ivangka.llmtgbottemplate.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiService {

    private final String apiToken = AppConfig.getApiToken();
    private final String uri = AppConfig.getUri();
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    public String postRequest(String inputs) {

        WebClient.Builder builder = WebClient.builder();
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("inputs", inputs);
        requestBody.put("max_tokens", "5000");

        String response = "";
        try {
            response = builder.build()
                    .post()
                    .uri(uri)
                    .header("Authorization", "Bearer " + apiToken)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            logger.debug("Response from API: " + response);
        } catch (Exception e) {
            logger.error("Error occurred while calling the API: "
                    + e.getMessage().replaceAll("\\R", " ") + "\n", e);
        }

        return response;
    }

}
