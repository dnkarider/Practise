package org.example;

import java.io.IOException;
import java.sql.SQLException;

class Main {

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        Thread thread = createNewThread();
        thread.start();
        thread.join();

        SongsSQL_Methods sql = new SongsSQL_Methods("identifier.sqlite");
        InsertParsedJSON insertParsedJSON = new InsertParsedJSON();
        insertParsedJSON.insertParsedJSON("C:\\Java_Projects_DLYA_LAIFA\\Testovoe\\src\\main\\resources\\data\\songs.json", sql);
        sql.close();
    }

    private static Thread createNewThread() throws SQLException, IOException {
        return new Thread(
                () -> {
                    try {
                        SongsSQL_Methods sql = new SongsSQL_Methods("identifier.sqlite");
                        System.out.println("бэк поток в работе\n");
                        sql.insert("Новый пират", "Александр Пистолетов", 3);
                        sql.close();
                    } catch (SQLException | IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("бэк поток завершил работу\n");
                });
    }
}