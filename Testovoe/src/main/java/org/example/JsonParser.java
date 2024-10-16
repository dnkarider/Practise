package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    private String filePath;

    public JsonParser(String filePath) {
        this.filePath = filePath;
    }
    public TrackList jsonParser() {
        ObjectMapper mapper = new ObjectMapper();

        try{
            TrackList trackList = mapper.readValue(new File(filePath), TrackList.class);
            return trackList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
