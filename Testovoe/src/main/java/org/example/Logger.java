package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    Date current = new Date();
    public void log(String operation) throws IllegalArgumentException, IOException {
        try (FileWriter writer = new FileWriter("log.config", true)) {
            switch (operation) {
                case "insert":
                    String addText = "Added row -" + current + "\n";
                    writer.write(addText);
                    writer.flush();
                    break;
                case "delete":
                    String deleteText = "Deleted row -" + current + "\n";
                    writer.write(deleteText);
                    writer.flush();
                    break;
                case "createTable":
                    String createTableText = "Created Table [songs] -" + current + "\n";
                    writer.write(createTableText);
                    writer.flush();
                    break;
                case "update":
                    String updateText = "Updated row -" + current + "\n";
                    writer.write(updateText);
                    writer.flush();
                    break;
                default:
                    throw new IllegalArgumentException("No such operation -" + current + "\n");
            }
        }
    }
}
