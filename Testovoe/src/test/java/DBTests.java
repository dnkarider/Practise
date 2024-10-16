import org.example.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBTests {
    private static final String TEST_DB_PATH = "testdb/test.db";
    private SongsSQL_Methods dbTests;

    @BeforeEach
    public void setUp() throws SQLException, IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        File testDBDir = new File("testdb");
        if (!testDBDir.exists()) {
            testDBDir.mkdirs();
        }
        dbTests = new SongsSQL_Methods(TEST_DB_PATH);
    }

    @AfterEach
    public void tearDown() throws Exception {
        dbTests.close();
        File testDBFile = new File(TEST_DB_PATH);
        if (testDBFile.exists()) {
            testDBFile.delete();
        }
        File testDBDir = new File("testdb");
        if (testDBDir.exists()) {
            testDBDir.delete();
        }
    }

    @Test
    public void testInsert() throws SQLException, IOException {
        dbTests.insert("Вокруг шум", "Каста", 3);
        ResultSet resultSet = dbTests.find(1);
        assertTrue(resultSet.next());
        assertEquals("Вокруг шум", resultSet.getString("track_name"));
    }

    @Test
    public void testUpdate() throws SQLException, IOException {
        dbTests.insert("Вокруг шум", "Каста", 3);
        dbTests.update("Ревность", "Каста", 4, 1);
        ResultSet resultSet = dbTests.find(1);
        assertTrue(resultSet.next());
        assertEquals("Ревность", resultSet.getString("track_name"));
    }

    @Test
    public void testDelete() throws SQLException, IOException {
        dbTests.insert("Вокруг шум", "Каста", 3);
        dbTests.delete(1);
        ResultSet resultSet = dbTests.find(1);
        assertFalse(resultSet.next()); // Проверяем, что запись не найдена
    }
    @Test
    public void testJsonParser() throws SQLException, IOException {
        SongsSQL_Methods sql = new SongsSQL_Methods("testdb/test.db");
        InsertParsedJSON insertParsedJSON = new InsertParsedJSON();
        insertParsedJSON.insertParsedJSON("C:\\Java_Projects_DLYA_LAIFA\\Testovoe\\src\\main\\resources\\data\\fakesongs.json", sql);
    }                            //Программа не падает, если подсунуть кривой JSON
}
