package ucll.project.domain;

import ucll.project.db.DatabaseEntity;
import ucll.project.db.DatabaseService;
import ucll.project.domain.user.Student;
import ucll.project.domain.user.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentClass extends DatabaseEntity {
    private Teacher teacher;
    private List<Student> students = new ArrayList<Student>();
    private String name;
    private Grade grade;

    public StudentClass(Integer primaryKey, Teacher teacher, String name, Grade grade) {
        super(primaryKey);
        setTeacher(teacher);
        setName(name);
        this.grade = grade;
    }

    public StudentClass(Integer primaryKey, String name, Grade grade) {
        super(primaryKey);
        setName(name);
        this.teacher = null;
        this.grade = grade;
    }
    public Teacher getTeacher() {
        return teacher;
    }

    public void removeStudent(Student s) {
        if (s == null) {
            throw new DomainException("Cant remove null from class");
        }
        if (this.students.contains(s)) {
            this.students.remove(s);
        } else {
            throw new DomainException("Student not in class");
        }
    }

    public void setTeacher(Teacher teacher) {
        if (teacher == null) {
            throw new DomainException("Teacher can't be empty");
        }
        this.teacher = teacher;
    }

    public void addStudent(Student s) {
        if (s == null) {
            throw new DomainException("Student can't be empty");
        }
        if (this.students.contains(s)) {
            throw new DomainException("Student already in class!");
        }
        this.students.add(s);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudents(List<Student> students) {
        if (students == null || students.isEmpty()) {
            throw new DomainException("can't add empty list of students");
        }
        for (Student s : students) {
            this.addStudent(s);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException("name can't be empty");
        }
        this.name = name;
    }

    public void setGrade(Grade g) {
        if (g == null) {
            throw new DomainException("Grade can't be empty");
        }

        this.grade = g;
    }

    public Map<Student, Map<Tent, Double>> getScoresOfStudents(DatabaseService service) {
        Map<Student, Map<Tent, Double>> results = new HashMap<>();

        for (Student s : getStudents()) {
            if (s.getAttempt() != null) {
                Map<Tent, Double> result = s.getAttempt().getTotalPercentagesPerTent(service, this.getGrade());
                results.put(s, result);
            }
        }

        return results;
    }

    public Map<Tent, Double> getAvarageResultOfStudentsPerTent(DatabaseService service) {
        Map<Student, Map<Tent, Double>> studentResults = this.getScoresOfStudents(service);
        Map<Tent, Double> result = new HashMap<>();
        Map<Tent, Integer> teller = new HashMap<>();
        for (Map.Entry<Student, Map<Tent, Double>> entry : studentResults.entrySet()) {
            for (Map.Entry<Tent, Double> entry2: entry.getValue().entrySet()) {

                if (result.get(entry2.getKey()) == null) result.put(entry2.getKey(), 0.0);
                if (teller.get(entry2.getKey()) == null) teller.put(entry2.getKey(), 0);
                result.put(entry2.getKey(), result.get(entry2.getKey())+entry2.getValue());
                teller.put(entry2.getKey(), teller.get(entry2.getKey())+1);
            }
        }
        for (Map.Entry<Tent, Double> entry : result.entrySet()) {
            result.put(entry.getKey(), entry.getValue()/teller.get(entry.getKey()));
        }
        return result;
    }



    public Grade getGrade() {
        return this.grade;
    }
}
