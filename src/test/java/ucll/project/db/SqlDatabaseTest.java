package ucll.project.db;

import org.junit.*;
import ucll.project.db.sql.SQLDatabase;
import ucll.project.db.sql.TentSqlDatabase;
import ucll.project.domain.Tent;

import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SqlDatabaseTest {

    private static IDatabase<Tent, Integer> sqlDatabase;

    @BeforeClass
    public static void initialize() throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("url", "jdbc:postgresql://databanken.ucll.be:51819/hakkaton?currentSchema=competenties-VerplichtAanwezig");
        properties.setProperty("user", "hakkaton_02");
        properties.setProperty("password", "OveechaetgegkM8o");
        properties.setProperty("ssl", "false");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmodel", "prefer");

        sqlDatabase = new TentSqlDatabase(properties);
    }

    @Before
    public void setUp() {
        sqlDatabase.drop();
    }

    @AfterClass
    public static void tearDown() {
        sqlDatabase.close();
    }

    @Test
    public void testAdd() {
        Tent tent = new Tent("test", "beschrijving");
        int id = sqlDatabase.add(tent);
        assertEquals(1, sqlDatabase.getCount());
    }


    @Test
    public void testGet(){
        Tent tent = new Tent(0,"test", "beschrijving");
        int id = sqlDatabase.add(tent);
        Tent retrievedObject = sqlDatabase.get(id);
        assertEquals(tent, retrievedObject);
    }

    @Test
    public void testGetAll(){
        Tent tent1 = new Tent(0,"test", "beschrijving");
        Tent tent2 = new Tent(1,"test", "beschrijving");
        int id1 = sqlDatabase.add(tent1);
        int id2 = sqlDatabase.add(tent2);
        List<Tent> retrievedObjects = sqlDatabase.getAll();

        assertEquals(tent1, retrievedObjects.get(0));
        assertEquals(tent2, retrievedObjects.get(1));
    }

    @Test
    public void testUpdate() {
        //
    }

    @Test
    public void testContains() {
        assertFalse(sqlDatabase.contains(0));
        Tent testobject = new Tent("name", "description");
        int id = sqlDatabase.add(testobject);
        assertTrue(sqlDatabase.contains(id));
    }

    @Test
    public void testCount() {
        assertEquals(0, sqlDatabase.getCount());
        Tent testobject = new Tent("name", "description");
        int id = sqlDatabase.add(testobject);
        assertEquals(1, sqlDatabase.getCount());
    }

    @Test
    public void testDelete() {
        Tent testobject = new Tent("name", "description");
        int id = sqlDatabase.add(testobject);
        assertEquals(1, sqlDatabase.getCount());
        sqlDatabase.drop();
        assertEquals(0, sqlDatabase.getCount());
    }

}
