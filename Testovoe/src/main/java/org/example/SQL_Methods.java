package org.example;

import java.sql.*;

public class SQL_Methods {

    public static final String URL = "jdbc:sqlite:identifier.sqlite";
    Logger logger = new Logger();

    protected void find(String trackName) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "select * from songs where track_name=?"
             )) {
            preparedStatement.setString(1, trackName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String track_name = resultSet.getString("track_name");
                String artist = resultSet.getString("artist");
                String duration = resultSet.getString("duration");
                System.out.println("id: " + id + ", track_name: " + track_name + ", artist: " + artist + ", duration: " + duration);
            }
        }
    }

    protected void insert(String track_name, String artist, int duration) {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO songs (track_name, artist, duration) VALUES (?, ?, ?)"
             )) {
            preparedStatement.setString(1, track_name);
            preparedStatement.setString(2, artist);
            preparedStatement.setInt(3, duration);

            if(preparedStatement.executeUpdate() == 1) {
                System.out.println("The value inserted succesfully");
                logger.log("insert");
            } else {
                System.out.println("The value is not inserted");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected void delete(String track_name) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE from songs WHERE track_name=?"
             )) {
            preparedStatement.setString(1, track_name);

            if(preparedStatement.executeUpdate() == 1) {
                System.out.println("The value deleted succesfully");
                logger.log("delete");
            } else {
                System.out.println("The value is not deleted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected void update(String new_track_name, String new_artist, int new_duration, String old_track_name) throws SQLException {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "UPDATE  songs SET track_name=?, artist=?, duration=? WHERE track_name=?"
             )) {
            preparedStatement.setString(1, new_track_name);
            preparedStatement.setString(2, new_artist);
            preparedStatement.setInt(3, new_duration);
            preparedStatement.setString(4, old_track_name);

            if(preparedStatement.executeUpdate() == 1) {
                System.out.println("The value updated succesfully");
                logger.log("update");
            } else {
                System.out.println("The value is not updated");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    protected void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS songs (\n" +
                "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    track_name text not null,\n" +
                "    artist text not null,\n" +
                "    duration int not null\n" +
                ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table is created or already exists.");
            logger.log("createTable");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
