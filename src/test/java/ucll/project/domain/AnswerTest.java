package ucll.project.domain;

import org.junit.Test;
import ucll.project.domain.Answer;
import ucll.project.domain.DomainException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AnswerTest {

    @Test
    public void createAnswerTest() {
        Answer answer = new Answer( "antwoord", 5);

        assertEquals("antwoord", answer.getValue());
        assertEquals(5, answer.getPoint());
    }

    @Test(expected = DomainException.class)
    public void createAnswerWithoutValueThrowsExceptionTest() {
        Answer answer = new Answer( null, 5);
    }

    @Test(expected = DomainException.class)
    public void createAnswerWithNegativePointThrowsExceptionTest() {
        Answer answer = new Answer( "antwoord", -1);
    }

    @Test(expected = DomainException.class)
    public void createAnswerWithEmptyValueThrowsExceptionTest() {
        Answer answer = new Answer( "", 5);
    }

    @Test
    public void equalsMethodReturnsTrueTest() {
        Answer answer1 = new Answer(1, "antwoord", 5, 1);
        Answer answer2 = new Answer(1, "antwoord", 5, 1);

        assertTrue(answer1.equals(answer2));
    }

    @Test
    public void equalsMethodReturnsFalseTest() {
        Answer answer1 = new Answer(1, "antwoord", 5, 1);
        Answer answer2 = new Answer(1, "iets", 5, 1);

        assertFalse(answer1.equals(answer2));
    }
}
