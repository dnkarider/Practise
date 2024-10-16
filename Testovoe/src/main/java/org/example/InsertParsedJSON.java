package org.example;

import java.io.IOException;
import java.sql.SQLException;

public class InsertParsedJSON {
    public void insertParsedJSON(String filePath, SongsSQL_Methods sql) throws SQLException, IOException {
        TrackList trackList = new JsonParser(filePath).jsonParser();
        try {
            for (Song song : trackList.getData()) {
                sql.insert(song.getTrack_name(), song.getArtist(), (song.getDuration()));
            }
            System.out.println("JSON обработан успешно!");
        } catch (NullPointerException e){
            System.out.println("Не получилось обработать JSON, строки не будут добавлены!");
        }
    }
}
