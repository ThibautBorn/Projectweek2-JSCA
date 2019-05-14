package ucll.project.domain.user;

import ucll.project.domain.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Student extends User{
    ArrayList<Attempt> attemptList;
    public Student(String userName, String firstName, String lastName, String email, Gender gender) {
        super(userName, firstName, lastName, email, gender);
        attemptList = new ArrayList<>();
    }

    public Student(int key, String userName, String firstName, String lastName, String email, Gender gender) {
        super(key, userName, firstName, lastName, email, gender);
        attemptList = new ArrayList<>();
    }

    public void addAttempt() {
        Date date = new Date();
        attemptList.add(new Attempt(date));
    }
    public void addAnswerQuestionMapToAttempt(Tent tent, Map<Question, Answer> set) {
        attemptList.get(attemptList.size() - 1).addChoicesPerTent(tent, set);
    }

    public Attempt getAttempt() {
        if (attemptList.size()>0) {
            return attemptList.get(attemptList.size() - 1);
        }
        return null;

    }
    @Override
    public String toString() {
        return "Student" + super.toString();
    }
}
