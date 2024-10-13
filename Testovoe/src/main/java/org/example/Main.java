package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

class Main {

    public static void main(String[] args) throws SQLException {

        SQL_Methods sql = new SQL_Methods();
        sql.createNewTable();
        TrackList trackList = new JsonParser().jsonParser();
        for(Song song : trackList.getData()) {
            sql.insert(song.getTrack_name(), song.getArtist(),(song.getDuration()));
        }
        sql.delete("track_name1");
        sql.update("track_name10", "Leps", 1111, "track_name2");
    }
}