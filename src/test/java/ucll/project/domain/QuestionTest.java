/**package ucll.project.domain;

import org.junit.Before;
import org.junit.Test;
import ucll.project.domain.Answer;
import ucll.project.domain.DomainException;
import ucll.project.domain.Grade;
import ucll.project.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuestionTest {

    Question question2;

    @Before
    public void setUp() {
        question2 = new Question( "iets", Grade.Grade1);
    }

    @Test
    public void createQuestionTest() {
        Question question = new Question( "iets", Grade.Grade1);

        assertEquals("iets", question.getValue());
        assertEquals(Grade.Grade1, question.getGrade());
    }

    @Test(expected = DomainException.class)
    public void createQuestionWithoutValueThrowsExceptionTest() {
        Question question = new Question(null, Grade.Grade1);
    }

    @Test(expected = DomainException.class)
    public void createQuestionWithoutGradeThrowsExceptionTest() {
        Question question = new Question( "iets", null);
    }

    @Test(expected = DomainException.class)
    public void createQuestionWithEmptyValueThrowsExceptionTest() {
        Question question = new Question( "", Grade.Grade1);
    }

    @Test
    public void addAnswerTest() {
        Answer answer = new Answer( "antwoord", 5);
        question2.addAnswer(answer);

        assertEquals(answer, question2.getAnswer("antwoord"));
    }

    @Test(expected = DomainException.class)
    public void addNullToAnswersThrowsExceptionTest() {
        question2.addAnswer(null);
    }

    @Test
    public void getAnswersTest() {
        Answer answer1 = new Answer("antwoord", 4);
        Answer answer2 = new Answer( "antwoord2", 4);

        question2.addAnswer(answer1);
        question2.addAnswer(answer2);

        List<Answer> answers = new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);

        assertEquals(answers, question2.getAnswers());
    }

    @Test(expected = DomainException.class)
    public void addSameAnswerTwiceThrowsExceptionTest() {
        Answer answer = new Answer( "antwoord", 3);
        question2.addAnswer(answer);
        question2.addAnswer(answer);
    }

    @Test
    public void equalsMethodReturnsTrueTest() {
        Question question1 = new Question( "antwoord", Grade.Grade1);
        Question question2 = new Question( "antwoord", Grade.Grade1);

        assertTrue(question1.equals(question2));
    }

    @Test
    public void equalsMethodReturnsFalseTest() {
        Question question1 = new Question( "antwoord", Grade.Grade1);
        Question question2 = new Question( "iets", Grade.Grade1);

        assertFalse(question1.equals(question2));
    }
}
**/