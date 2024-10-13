package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonParser {
    public TrackList jsonParser() {
        String filePath = "C:\\Java_Projects_DLYA_LAIFA\\Testovoe\\src\\main\\resources\\data\\songs.json";
        ObjectMapper mapper = new ObjectMapper();

        try{
            TrackList trackList = mapper.readValue(new File(filePath), TrackList.class);
            return trackList;
        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}
