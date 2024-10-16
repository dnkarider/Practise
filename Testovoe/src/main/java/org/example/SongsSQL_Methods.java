package org.example;

import java.io.IOException;
import java.sql.*;

public class SongsSQL_Methods {

    public static String URL;
    Logger logger = new Logger();
    private Connection connection;

    public SongsSQL_Methods(String URL) throws SQLException, IOException {
        SongsSQL_Methods.URL = "jdbc:sqlite:" + URL;
        String createTableSQL = """
                CREATE TABLE IF NOT EXISTS songs (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    track_name text not null,
                    artist text not null,
                    duration int not null
                );""";
            connection = DriverManager.getConnection(SongsSQL_Methods.URL);
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSQL);
            System.out.println("Table is created or already exists.");
            logger.log("createTable");
        }


    public ResultSet find(int id) throws SQLException {
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from songs where id=?"
             );
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
    }

    public void insert(String track_name, String artist, int duration) throws SQLException, IOException{
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO songs (track_name, artist, duration) VALUES (?, ?, ?)"
             );
            preparedStatement.setString(1, track_name);
            preparedStatement.setString(2, artist);
            preparedStatement.setInt(3, duration);

            if(preparedStatement.executeUpdate() == 1) {
                System.out.println("The value inserted succesfully");
                logger.log("insert");
            } else {
                System.out.println("The value is not inserted");
        }
    }
    public void delete(int id) throws SQLException, IOException {
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE from songs WHERE id=?"
             );
            preparedStatement.setInt(1, id);

            if(preparedStatement.executeUpdate() == 1) {
                System.out.println("The value deleted succesfully");
                logger.log("delete");
            } else {
                System.out.println("The value is not deleted");
        }
    }
    public void update(String new_track_name, String new_artist, int new_duration, int oldId) throws SQLException, IOException {
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE  songs SET track_name=?, artist=?, duration=? WHERE id=?"
        );
            preparedStatement.setString(1, new_track_name);
            preparedStatement.setString(2, new_artist);
            preparedStatement.setInt(3, new_duration);
            preparedStatement.setInt(4, oldId);

            if (preparedStatement.executeUpdate() == 1) {
                System.out.println("The value updated succesfully");
                logger.log("update");
            } else {
                System.out.println("The value is not updated");
            }
    }
    protected void read() throws SQLException {
        String sql = "SELECT * FROM songs";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String track_name = resultSet.getString("track_name");
                String artist = resultSet.getString("artist");
                String duration = resultSet.getString("duration");
                System.out.println("track_name: " + track_name + ", artist: " + artist + ", duration: " + duration + "\n");
        }
    }
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
