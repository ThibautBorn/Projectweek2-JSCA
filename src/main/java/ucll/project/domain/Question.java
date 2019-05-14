package ucll.project.domain;

import ucll.project.db.DatabaseEntity;
import java.util.ArrayList;
import java.util.List;

public class Question extends DatabaseEntity<Integer> {
    private int id;
    private int tentId;
    private String value;
    private Grade grade;
    private List<Answer> answers = new ArrayList<>();

    public Question(String value, Grade grade) {
        this.setGrade(grade);
        this.setValue(value);
    }

    public Question(int id, String value, Grade grade, int tentId) {
        this(value, grade);
        setId(id);
        setTentId(tentId);
    }

    public int getTentId() {
        return this.tentId;
    }

    public void setTentId(int tent) {
        this.tentId = tent;
    }

    public String getValue() {
        return this.value;
    }

    public Grade getGrade() {
        return this.grade;
    }

    public void setValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new DomainException("Value can't be empty");
        }
        this.value = value;
    }

    public void setGrade(Grade grade) {
        if (grade == null) {
            throw new DomainException("Grade can't be empty");
        }
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Question) {
            Question q = (Question)o;
            boolean b = true;
            if (this.getId() != q.getId()) b = false;
            if (!this.getValue().equals(q.getValue())) b = false;
            if (!this.getGrade().equals(q.getGrade())) b = false;
            if (this.getTentId() != q.getTentId()) b = false;
            return b;
        } else return false;
    }

    @Override
    public String toString() {
        return this.getValue()+" - "+this.getGrade().toString()+" - "+this.answers.size()+" answers";
    }
}
