package ucll.project.db;

import ucll.project.domain.*;
import ucll.project.domain.user.*;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class RealDummyData
{
    public static final String STUDENT_WELCOME = "De school wil je leren leven, leren leren en leren kiezen. We willen je kansen geven en steunen om voorbereid te zijn op jouw toekomst. " +
            "Voordat je kiezen wat je wil, moet je weten waar je al bent. Wat heb je al geleerd? " +
            "Door deze test in te vullen en te bespreken krijg je een beeld en kan je verder groeien. ";
    public static final String TEACHER_WELCOME = "De school wil leren leven, leren leren en leren kiezen. We willen kansen geven en steun verlenen zodat leerlingen voorbereid zijn op hun toekomst. " +
    "Om goede keuzes te kunnen maken, is het belangrijk te weten waar je staat. " +
    "Door de testresultaten met leerlingen coachend te bespreken helpen we ze groeien.";

    public static void addData(DatabaseService service)
    {
        addCompetence(service, "samenwerken", new String[]{"Bij een groepswerk kan ik goed de afspraken nakomen", "Ik speel eerlijk bij spel / sport", "Ik bespreek hoe ik een taak moet maken of moet studeren"});
        addCompetence(service, "probleem-oplossend denken", new String[]{"Ik kan een onderzoeksvraag stellen", "Als ik niet direct een oplossing vind, zoek ik verder", "Wanneer ik een moeilijke opdracht krijg, geef ik niet op"});
        addCompetence(service, "creativiteit", new String[]{"Ik waardeer kunst", "Ik durf mezelf creatief uiten", "Ik vind een originele oplossing voor problemen / taken"});
        addCompetence(service, "mediawijsheid / kritisch", new String[]{"Ik weet hoe ik juiste en betrouwbare informatie vind", "Ik gedraag me correct op sociale media", "Ik herken fake-news"});
        addCompetence(service, "leercompetenties", new String[]{"Ik maak een plan voor een opdracht", "Ik weet hoe ik best studeer, wat mijn talent is.", "Na een brainstorm kan ik een bruikbaar idee kiezen"});
        addCompetence(service, "zelfzorg", new String[]{"Ik kom op voor mezelf als ik me niet goed voel", "Ik leef gezond", "Ik kan EHBO toepassen"});
        addCompetence(service, "kennis / leerinhoud", new String[]{"Ik studeer voldoende voor de verschillende vakken", "Ik verbind wat ik leer met wat ik reeds weet ", "Ik maak oefeningen op de leerstof"});
        addCompetence(service, "betrokkenheid", new String[]{"Ik zet me in voor medeleerlingen", "Ik draag zorg voor het milieu", "Ik ben respectvol naar iedereen"});
        addCompetence(service, "ICT competenties", new String[]{"Ik kan met een pc een tekst / taak maken ", "Ik weet hoe ik een nieuw programma goed en snel leer gebruiken", "Informatie die ik op internet vind, kan op de correcte manier gebruiken"});
        addCompetence(service, "taal leren gebruiken", new String[]{"Ik lees graag", "Ik let op mijn taal bij taken", "Ik kan goed presenteren / vertellen"});

        Admin admin = new Admin("jpelgrims", "Jelle", "Pelgrims", "a#mail.com", Gender.MALE);
        admin.hashAndSetPassword("paswoord");

        service.addUser(admin);

        for (StudentClass c: getDummyDateClasses()) {
            service.addStudentClass(c);
        }
    }

    public static List<StudentClass> getDummyDateClasses() {
        List<StudentClass> classes = new ArrayList<>();

        classes.add(new StudentClass(1, "Klas 1", Grade.Grade1));
        classes.add(new StudentClass(2, "Klas 2", Grade.Grade1));
        classes.add(new StudentClass(3, "Klas 3", Grade.Grade2));
        classes.add(new StudentClass(4, "Klas 4", Grade.Grade2));
        classes.add(new StudentClass(5, "Klas 5", Grade.Grade3));
        classes.add(new StudentClass(6, "Klas 6", Grade.Grade3));

        Student s = new Student("thibaut", "Thibaut", "Born", "a@student.be",Gender.MALE);
        s.hashAndSetPassword("paswoord");
        classes.get(1).addStudent(s);

        Student m = new Student("melanie", "Melanie", "Van Vlasselaer", "a@student.be",Gender.MALE);
        m.hashAndSetPassword("paswoord");
        classes.get(2).addStudent(m);

        User teacher = new Teacher("florian", "Florian", "Berden", "a@b.be", Gender.MALE);
        teacher.hashAndSetPassword("paswoord");

        for (int i = 0; i < 6; i++) {
            classes.get(i).setTeacher((Teacher)teacher);
        }


        for (int i = 0; i < 6; i++) {
            Student student = new Student("studentUsername"+i, "studentFName"+i, "studentLName"+i, "a@student.be",Gender.MALE);
            student.hashAndSetPassword("aaaa");
            classes.get(i).addStudent(student);
        }

        return classes;
    }
    
    private static void addCompetence(DatabaseService service, String name, String[] questions)
    {
        Tent tent = new Tent(name, name);
        int tentId = service.addTent(tent);

        for (Grade grade: Grade.values()) {
            addQuestions(tentId, service, questions, grade);
        }
    }

    private static void addQuestions(int tentId, DatabaseService service, String[] questions, Grade grade)
    {
        for (String questionString : questions)
        {
            Question question = new Question(questionString, grade);
            int questionId = service.addQuestion(tentId, question);

            addGenericAnswers(questionId, service);
        }
    }

    private static void addGenericAnswers(int questionId, DatabaseService service)
    {
        service.addAnswer(questionId, new Answer("Ik ben hier niet mee bezig", 1));
        service.addAnswer(questionId, new Answer("Ik vind dit belangrijk en wil hieraan werken", 2));
        service.addAnswer(questionId, new Answer("Ik oefen hierop", 3));
        service.addAnswer(questionId, new Answer("Het lukt me meestal", 4));
        service.addAnswer(questionId, new Answer("Het lukt me vlot", 5));
        service.addAnswer(questionId, new Answer("Ik kan anderen helpen om dit ook te kunnen", 6));
    }
}
