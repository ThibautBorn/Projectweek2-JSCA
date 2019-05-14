package ucll.project.domain.unitTests;

import org.junit.Before;
import org.junit.Test;
import ucll.project.domain.DomainException;
import ucll.project.domain.Grade;
import ucll.project.domain.StudentClass;
import ucll.project.domain.user.Gender;
import ucll.project.domain.user.Student;
import ucll.project.domain.user.Teacher;
import ucll.project.domain.user.User;

import static org.junit.Assert.assertEquals;

public class UserTest {

    private Student student;
    private Teacher teacher;

    @Before
    public void setUp() {
        student = new Student(1, "username", "Bob","Marley", "iets@iets.iets", Gender.MALE);
        teacher = new Teacher(1, "username", "Bob","Marley", "iets@iets.iets", Gender.MALE);
    }

    @Test
    public void createStudentTest() {
        User student = new Student(1, "username", "Bob","Marley", "iets@iets.iets", Gender.MALE);

        assertEquals("username", student.getUserName());
        assertEquals("Bob", student.getFirstName());
        assertEquals("Marley", student.getLastName());
        assertEquals("iets@iets.iets", student.getEmail());
        assertEquals(Gender.MALE, student.getGender());
    }

    @Test(expected = DomainException.class)
    public void createStudentWithoutUsernameThrowsExceptionTest() {
        User student = new Student(1, null, "Bob","Marley", "iets@iets.iets", Gender.MALE);
    }

    @Test(expected = DomainException.class)
    public void createStudentWithoutFirstNameThrowsExceptionTest() {
        User student = new Student(1, "username", null,"Marley", "iets@iets.iets", Gender.MALE);
    }

    @Test(expected = DomainException.class)
    public void createStudentWithoutLastNameThrowsExceptionTest() {
        User student = new Student(1, "username", "Bob",null, "iets@iets.iets", Gender.MALE);
    }

    @Test(expected = DomainException.class)
    public void createStudentWithoutEmailThrowsExceptionTest() {
        User student = new Student(1, "username", "Bob","Marley", null, Gender.MALE);
    }

    @Test(expected = DomainException.class)
    public void createStudentWithoutGenderThrowsExceptionTest() {
        User student = new Student(1, "username", "Bob","Marley", "iets@iets.iets", null);
    }

    @Test(expected = DomainException.class)
    public void createStudentWithEmptyUsernameThrowsExceptionTest() {
        User student = new Student(1, "", "Bob","Marley", "iets@iets.iets", Gender.MALE);
    }

    @Test(expected = DomainException.class)
    public void createStudentWithEmptyFirstNameThrowsExceptionTest() {
        User student = new Student(1, "username", "","Marley", "iets@iets.iets", Gender.MALE);
    }

    @Test(expected = DomainException.class)
    public void createStudentWithEmptyLastNameThrowsExceptionTest() {
        User student = new Student(1, "username", "Bob","", "iets@iets.iets", Gender.MALE);
    }

    @Test(expected = DomainException.class)
    public void createStudentWithEmptyEmailThrowsExceptionTest() {
        User student = new Student(1, "username", "Bob","Marley", "", Gender.MALE);
    }
}
