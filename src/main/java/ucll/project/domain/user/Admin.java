package ucll.project.domain.user;

import ucll.project.domain.DomainException;
import ucll.project.domain.Grade;
import ucll.project.domain.StudentClass;

public class Admin extends User{
    public Admin(String userName, String firstName, String lastName, String email, Gender gender) {
        super(userName, firstName, lastName, email, gender);
    }

    public Admin(Integer key, String userName, String firstName, String lastName, String email, Gender gender) {
        super(key, userName, firstName, lastName, email, gender);
    }

    @Override
    public String toString() {
        return "Admin" + super.toString();
    }
}
