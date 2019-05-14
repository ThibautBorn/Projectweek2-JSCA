package ucll.project.domain.user;

import ucll.project.db.DatabaseService;
import ucll.project.domain.Grade;
import ucll.project.domain.StudentClass;

public class UserFactory {

    /*

    Temporary factory for creating dummy users when logging in!
    (Story 6)

    will be made better soon™

     */
    public static User createUser(String type, Grade g, DatabaseService service) {
        User user = null;
        StudentClass sclass = service.getStudentGroups().get(0);
        switch(type) {
            case "Student":
                user = new Student("student_01", "studentsname", "studentslastname", "a@b.be", Gender.MALE);
                sclass.addStudent((Student)user);
                break;
            case "Teacher":
                user = sclass.getTeacher();
                break;
            case "Admin":
                user = new Admin("admin_01", "adminsname", "adminslastname", "a@b.be", Gender.FEMALE);
                break;
            default:
                break;
        }
        return user;
    }
}
