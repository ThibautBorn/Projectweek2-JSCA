package ucll.project.domain;

import java.util.ArrayList;
import java.util.List;

import ucll.project.db.DatabaseEntity;

public class Tent extends DatabaseEntity<Integer> {

    private String name;
    private String description;
    private List<Question> questions = new ArrayList<>();

    public Tent(String name, String description) {
        this.setName(name);
        this.setDescription(description);
    }

    public Tent(int id, String name, String description) {
        this(name, description);
        setId(id);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new DomainException("Name can't be empty");
        }
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new DomainException("Description can't be empty");
        }
        this.description = description;
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    public Question getQuestion(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new DomainException("value can't be empty");
        }

        for (Question q : this.questions) {
            if (q.getValue().equals(value)) {
                return q;
            }
        }
        throw new DomainException("Question not in tent");
    }

//    public void addQuestion(Question q) {
//        if (q == null) {
//            throw new DomainException("Question can't be null");
//        }
//        if (questions.contains(q)) {
//            throw new DomainException("Question already exists");
//        }
//        this.questions.add(q);
//    }



    public int getQuestionsSize() {
        return this.questions.size();
    }

//    public void addQuestions(List<Question> questions) {
//        if (questions == null || questions.isEmpty()) {
//            throw new DomainException("Cant add empty list of questions");
//        }
//
//        for(Question q : questions) {
//            if (!this.questions.contains(q)) {
//                this.addQuestion(q);
//            }
//        }
//    }

    @Override
    public String toString() {
        return this.name + ": " + this.description;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Tent) {
            Tent tent = (Tent)o;
            boolean b = true;
            if (this.getId()!=tent.getId()) b = false;
            if (!this.getName().equals(tent.getName())) b = false;
            if (!this.getDescription().equals(tent.getDescription())) b = false;
            return b;
        } else return false;
    }
}
