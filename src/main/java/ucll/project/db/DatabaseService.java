package ucll.project.db;

import ucll.project.db.sql.AnswerSqlDatabase;
import ucll.project.db.sql.QuestionSqlDatabase;
import ucll.project.db.sql.TentSqlDatabase;
import ucll.project.domain.*;
import ucll.project.domain.user.Student;
import ucll.project.domain.user.User;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class DatabaseService {

    IDatabase<Tent, Integer> competences;
    IDatabase<Question, Integer> questions;
    IDatabase<Answer, Integer> answers;
    IDatabase<User, Integer> users;
    IDatabase<StudentClass, Integer> classGroups;

    public DatabaseService(Properties properties, boolean useSql) {

        // Use either SQL or InMemory
        if (useSql) {
            try {
                competences = new TentSqlDatabase(properties);
                questions = new QuestionSqlDatabase(properties);
                answers = new AnswerSqlDatabase(properties);

                // Remove all existing data and add dummy data again
                competences.drop();
                questions.drop();
                answers.drop();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            competences = new InMemoryDatabase<Tent,Integer>();
            questions = new InMemoryDatabase<Question,Integer>();
            answers = new InMemoryDatabase<Answer,Integer>();
            users = new InMemoryDatabase<User, Integer>();
            classGroups = new InMemoryDatabase<StudentClass, Integer>();
        }

        RealDummyData.addData(this);
    }

    public void close() {
        competences.close();
        questions.close();
        answers.close();
        users.close();
        classGroups.close();
    }

    public void addStudentClass(StudentClass studentClass) {
        classGroups.add(studentClass);

        addUser(studentClass.getTeacher());

        for (Student s: studentClass.getStudents()) {
            users.add(s);
        }
    }

    public User getUser(String username) {
        for (User u: users.getAll()) {
            if (u.getUserName().equals(username)) return u;
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }


    public Tent getTent(int placeNumber) {
        return competences.get(placeNumber);
    }

    public Tent getNextTent(Tent tent) {
        try {
            return competences.get(tent.getId());
        } catch (Exception ex) {
            return null;
        }
    }

    public List<StudentClass> getStudentGroups() {
        return classGroups.getAll();
    }

    public List<Tent> getAllTenten() {
        return competences.getAll();
    }

    public void removeAllTenten() {
        competences.drop();
    }

    public Answer getAnswer(int primaryKey) {
        return answers.get(primaryKey);
    }

    public List<Answer> getAnswers(int questionId) {
        List<Answer> questionAnswers = new ArrayList<>();

        for (Answer answer : answers.getAll()) {
            if (answer.getQuestionId() == questionId) {
                questionAnswers.add(answer);
            }
        }
        return questionAnswers;
    }

    public Question getQuestion(int primaryKey) {
        return questions.get(primaryKey);
    }

    public int addTent(Tent tent) {
        if (this.competences.contains(tent)) {
            throw new DomainException("Competence already exists");
        } else {
            int id = competences.add(tent);
            return id;
        }
    }

    public void updateTent(Tent t) {
        competences.update(t);
    }

    public int addQuestion(int competenceId, Question question) {
        if (this.questions.contains(question)) {
            throw new DomainException("Question already exists");
        } else {
            question.setTentId(competenceId);
            int id = questions.add(question);
            return id;
        }
    }

    public int addAnswer(int questionId, Answer answer) {
        if (this.answers.contains(answer)) {
            throw new DomainException("Answer already exists");
        } else {
            answer.setQuestionId(questionId);
            int id = answers.add(answer);
            return id;
        }
    }

    public boolean containsTent(int primaryKey) {
        return questions.contains(primaryKey);
    }

    public boolean containsAnswer(int primaryKey) {
        return answers.contains(primaryKey);
    }

    public boolean containsQuestion(int primaryKey) {
        return questions.contains(primaryKey);
    }

    public boolean containsTent(Tent tent) {
        return competences.contains(tent);
    }

    public boolean containsAnswer(Answer answer) {
        return answers.contains(answer);
    }

    public boolean containsQuestion(Question question) {
        return questions.contains(question);
    }


    public int getNrOfTenten() {
        return competences.getCount();
    }

    public List<Question> getQuestions(int tentId, Grade grade) {
        if (grade == null) {
            throw new DomainException("Grade can't be null");
        }

        List<Question> returnQuestions = new ArrayList<>();

        for (Question q : this.questions.getAll()) {

            if (q.getGrade().equals(grade) && q.getTentId() == tentId) {
                returnQuestions.add(q);
            }

        }

        return returnQuestions;
    }


    public List<StudentClass> getTeachersStudentGroups(User teacher)
    {
        List<StudentClass> groups = new ArrayList<>();
        for(StudentClass studentClass : this.getStudentGroups()) {
            if (studentClass.getTeacher() != null && studentClass.getTeacher().equals(teacher)) {
                groups.add(studentClass);
            }
        }
        return  groups;
    }


    public StudentClass getStudentClassByStudent(Student student) {
        for (StudentClass studentclass : this.getStudentGroups()) {
            if (studentclass.getStudents().contains(student)) {
                return studentclass;
            }
        }
        return null;
    }
    public int getPossibleAmountOfPoints(int tentId, Grade grade) {
        int score = 0;
        for(Question q : getQuestions(tentId, grade)) {
            int max = 0;
            for (Answer a : getAnswers(q.getId())) {
                if (a.getPoint() > max) max=a.getPoint();
            }
            score+=max;
        }
        return score;
    }

}
