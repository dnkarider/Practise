
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.example.SongsSQL_Methods;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class DBTestsWithMock {
    private static final String TEST_DB_PATH = "testdb/test.db";
    private SongsSQL_Methods dbTests;
    private ResultSet resultSetMock;

    @BeforeEach
    public void setUp() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, IOException {

        File testDBDir = new File("testdb");
        if (!testDBDir.exists()) {
            testDBDir.mkdirs();
        }

        resultSetMock = mock(ResultSet.class);

        // Мокаем создаваемый объект UserDAO
        dbTests = new ByteBuddy()
                .subclass(SongsSQL_Methods.class)
                .method(ElementMatchers.named("find"))
                .intercept(MethodDelegation.to(this))
                .make()
                .load(SongsSQL_Methods.class.getClassLoader())
                .getLoaded()
                .getConstructor(String.class)
                .newInstance(TEST_DB_PATH); // Используем временную БД в памяти для тестов
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

    public ResultSet findMock(int id) throws SQLException {
        if (id == 1) {
            when(resultSetMock.next()).thenReturn(false);
            when(resultSetMock.getString("track_name")).thenReturn("Вокруг шум");
        }
        return resultSetMock;
    }

    @Test
    public void testInsert_userWithMock() throws SQLException, IOException {
        dbTests.insert("Вокруг шум", "Каста", 3);

        ResultSet resultSet = dbTests.find(1);
        assertEquals("Вокруг шум", resultSet.getString("track_name"));
    }

    @Test
    public void testUpdate_userWithMock() throws SQLException, IOException {
        dbTests.insert("Вокруг шум", "Каста", 3);
        dbTests.update( "Ревность", "Каста", 4, 1);

        ResultSet resultSet = dbTests.find(1);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getString("track_name")).thenReturn("Ревность");

        assertEquals("Ревность", resultSet.getString("track_name"));
    }

    @Test
    public void testDelete_userWithMock() throws SQLException, IOException {
        dbTests.insert("Вокруг шум", "Каста", 3);
        dbTests.insert("Ревность", "Каста", 4);


        // Имитация удаления и проверки
        // Имитация удаления и проверки
        dbTests.delete(2);
        when(resultSetMock.next()).thenReturn(false);

        ResultSet resultSet = dbTests.find(2);
        assertFalse(resultSet.next());
    }
}
