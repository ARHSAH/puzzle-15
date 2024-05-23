package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import static controller.Variables.*;

public class Constants {
    private static Constants INSTANCE;

    public static Constants getINSTANCE() throws IOException {
        if(INSTANCE == null){
            INSTANCE = new Constants();
        }
        return INSTANCE;
    }
    private Constants() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(file);

        widthTiles = rootNode.path("widthTiles").asInt();
        heightTiles = rootNode.path("heightTiles").asInt();
        mode = rootNode.path("mode").asBoolean();

        for(int i = 0 ; i < rootNode.path("initial-ordering").size() ; i++){
            int helper = rootNode.path("initial-ordering").get(i).asInt();
            String helper1 = rootNode.path("images").get(i).asText();
            imagesRandomOrder.add(helper1);
            piecesRandomOrder.add(helper);

        }
    }
}
