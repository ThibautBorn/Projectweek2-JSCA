package ucll.project.db;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.Data;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InMemoryDatabaseTest {

    private IDatabase<DatabasEntityTestClass, String> inMemoryDatabase;

    @Before
    public void setUp() {
        inMemoryDatabase = new InMemoryDatabase<>();
    }

    @After
    public void tearDown() {
        inMemoryDatabase = null;
    }

    @Test
    public void testGet(){
        DatabasEntityTestClass testobject = new DatabasEntityTestClass("testKey");
        int id = inMemoryDatabase.add(testobject);
        DatabasEntityTestClass retrievedObject = inMemoryDatabase.get(id);
        assertEquals(testobject, retrievedObject);
    }

    /**@Test
    public void testGetByUniqueField() {
        DatabasEntityTestClass testobject = new DatabasEntityTestClass("testKey");
        inMemoryDatabase.add(testobject);
        DatabasEntityTestClass retrievedObject = inMemoryDatabase.getByUniqueField("name", "testKey");
        assertEquals(testobject, retrievedObject);
    }**/

    @Test
    public void testGetAll(){
        List<DatabasEntityTestClass> testObjects = new ArrayList<>();
        for(int i=0;i<10;i++) {
            DatabasEntityTestClass testobject = new DatabasEntityTestClass(Integer.toString(i));
            testObjects.add(testobject);
            inMemoryDatabase.add(testobject);
        }


        List<DatabasEntityTestClass> retrievedTestObjects = inMemoryDatabase.getAll();
        assertTrue(retrievedTestObjects.containsAll(testObjects));
    }

    @Test
    public void testUpdate() {
        DatabasEntityTestClass testobject = new DatabasEntityTestClass("testKey");
        int id = inMemoryDatabase.add(testobject);
        assertNull(testobject.getAttribute());

        // Modify object
        testobject.setAttribute("testValue");
        inMemoryDatabase.update(testobject);

        DatabasEntityTestClass retrievedObject = inMemoryDatabase.get(id);
        assertEquals(retrievedObject.getAttribute(), "testValue");
    }

    @Test
    public void testContains() {
        assertFalse(inMemoryDatabase.contains(0));
        DatabasEntityTestClass testobject = new DatabasEntityTestClass("testKey");
        int id = inMemoryDatabase.add(testobject);
        assertTrue(inMemoryDatabase.contains(id));
    }

    @Test
    public void testCount() {
        assertEquals(inMemoryDatabase.getCount(), 0);
        for(int i=0;i<10;i++) {
            DatabasEntityTestClass testobject = new DatabasEntityTestClass(Integer.toString(i));
            inMemoryDatabase.add(testobject);
        }
        assertEquals(inMemoryDatabase.getCount(), 10);
    }

    @Test
    public void testDelete() {
        DatabasEntityTestClass testobject = new DatabasEntityTestClass("testKey");
        int id = inMemoryDatabase.add(testobject);
        assertTrue(inMemoryDatabase.contains(id));

        inMemoryDatabase.delete(id);
        assertFalse(inMemoryDatabase.contains(id));
    }

}
