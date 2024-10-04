package ivangka.llmtgbottemplate.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonService {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static JsonNode parseSubstring(String src) throws IOException {
        return objectMapper.readTree(src.substring(1, src.length() - 1));
    }

}
