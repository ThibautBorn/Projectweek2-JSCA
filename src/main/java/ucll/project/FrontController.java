package ucll.project;

import ucll.project.controller.UserController;
import ucll.project.db.DatabaseService;
import ucll.project.db.RealDummyData;
import ucll.project.domain.Answer;
import ucll.project.domain.Grade;
import ucll.project.domain.Question;
import ucll.project.domain.StudentClass;
import ucll.project.domain.Tent;
import ucll.project.domain.user.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

/**
 * This is the FrontController
 * All requests will go trough here, and this class will
 * decide which controller to call.
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class FrontController extends HttpServlet {
    private UserRepository userRepository;

    private DatabaseService service;

    public void init() throws ServletException {
        super.init();
        // You could switch here based on config
        userRepository = new UserRepositoryMemory();

        ServletContext context = getServletContext();
        Properties properties = new Properties();
        Enumeration<String> parameterNames = context.getInitParameterNames();
        while (parameterNames.hasMoreElements()){
            String propertyName = parameterNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }

        service = new DatabaseService(properties, false);

    }

    public void destroy() {
        service.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handle(request, response);
    }

    private void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the request URI and parse it
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String requestResource;
        String requestAction = "";
        if (requestURI.equals("/"))
            requestResource = "index";
        else {
            String[] requestURIparts = requestURI.split("/");
            requestResource = requestURIparts[1];
            requestAction = requestURIparts.length > 2 ? requestURIparts[2] : "";
        }
        /** Eg.: GET /user/login
         *  method=GET
         *  requestResource=user
         *  requestAction=login
         */

        // controllers
        UserController userController = new UserController(userRepository);

        if (requestResource.equals("login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
                User user = service.getUser(username);
                if (user != null && user.isValidPassword(password)) {
                    if (user instanceof Student) {
                        Student student = (Student) user;
                        Grade grade = service.getStudentClassByStudent(student).getGrade();

                        request.getSession().setAttribute("grade", grade);
                    }
                    //request.getSession().setAttribute(user);
                } else {
                    List<String> errors = Arrays.asList("Incorrecte gebruikersnaam of paswoord");
                    request.setAttribute("errors", errors);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    return;
                }
                request.getSession().setAttribute("loggedIn", user);
                requestResource = "index";
            }else {
                List<String> errors = Arrays.asList("Incorrecte gebruikersnaam of paswoord");
                request.setAttribute("errors", errors);
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
        }

        if (requestResource.equals("logout")) {
            request.getSession().setAttribute("loggedIn", null);
        }

        if (!this.userIsLoggedIn(request)) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
            return;
        } else {
            request.setAttribute("userrole", getUserRole(request));

        }

        if (request.getSession().getAttribute("userid") != null) {
            int userId = (int) request.getSession().getAttribute("userid");
            request.setAttribute("user", userRepository.get(userId));
        }


        // logic to handle what controller
        System.out.println(String.format("%s %s\nResource = %s, Action = %s",
                request.getMethod(), requestURI,
                requestResource, requestAction
        ));


        if (method.equals("GET") && requestResource.equals("user") && requestAction.equals("login")) {
            userController.getLogin(request, response);
            return; // stop execution
        }
        if (method.equals("POST") && requestResource.equals("user") && requestAction.equals("login")) {
            userController.postLogin(request, response);
            return;
        }
        if (/* any method */         requestResource.equals("user") && requestAction.equals("signup")) {
            userController.handleSignup(request, response);
            return;
        }

        if (/* any method */         requestResource.equals("user") && requestAction.equals("logout")) {
            userController.handleLogout(request, response);
            return;
        }

        User user = (User) request.getSession().getAttribute("loggedIn");
        request.setAttribute("username", user.getUserName());

        if (requestResource.equals("index")) {
            user = (User) request.getSession().getAttribute("loggedIn");
            request.setAttribute("name", user.getFullName());
            request.setAttribute("username", user.getUserName());
            if (user instanceof  Student) {
                request.setAttribute("welcomeText", RealDummyData.STUDENT_WELCOME);
            } else if (user instanceof Teacher) {
                request.setAttribute("welcomeText", RealDummyData.TEACHER_WELCOME);
            }


            request.getRequestDispatcher("/index.jsp").forward(request, response);

            return;
        } else if (requestResource.equals("competences")) {
            request.getRequestDispatcher(handleCompetences(request, response)).forward(request, response);
            return;

        } else if (requestResource.equals("studentResults")) {
            User teacher = (Teacher)request.getSession().getAttribute("loggedIn");
            List<StudentClass> classGroups = this.service.getTeachersStudentGroups(teacher);
            request.setAttribute("classGroups", classGroups);
            request.setAttribute("competences", service.getAllTenten());
            Map<StudentClass, Map<Student, Map<Tent, Double>>> resultsPerStudents = new HashMap<>();
            Map<StudentClass, Map<Tent, Double>> resultsAvarage = new HashMap<>();

            for (StudentClass sc : classGroups) {
                resultsPerStudents.put(sc, sc.getScoresOfStudents(service));
                resultsAvarage.put(sc, sc.getAvarageResultOfStudentsPerTent(service));
            }

            request.setAttribute("resultsAvarageOfStudents", resultsAvarage);
            request.setAttribute("resultsPerStudents", resultsPerStudents);
            request.getRequestDispatcher("/studentResults.jsp").forward(request, response);
            return;

        } else if (requestResource.equals("test")) {
            request.setAttribute("buttonLabel", "Volgende");
            try {
                Tent tent;
                User student = (Student)getUser(request);
                String placeNumber = (String)request.getParameter("tent");
                if (placeNumber == null) {
                        ((Student) student).addAttempt();

                    tent = service.getTent(0);
                } else {
                    Map<Question, Answer> choices = new HashMap<>();

                    for (Question q : service.getQuestions(Integer.parseInt(placeNumber), (Grade)request.getSession().getAttribute("grade"))) {
                        String answer = request.getParameter("" + q.getId());
                        if (answer != null && !answer.equals("")) {
                            int answerId = Integer.parseInt(answer);
                            Answer a = service.getAnswer(answerId);
                            if(service.getAnswers(q.getId()).contains(a)) {
                                choices.put(q, a);
                            }
                        }
                    }
                    ((Student) getUser(request)).addAnswerQuestionMapToAttempt(service.getTent(Integer.parseInt(placeNumber)), choices);
                    tent = service.getTent(Integer.parseInt( placeNumber ) + 1);

                    if (Integer.parseInt( placeNumber ) == this.service.getAllTenten().size() - 2)
                    {
                        request.setAttribute("buttonLabel", "Indienen");
                    }
                }
                if(tent == null)
                {
                    Student tempStudent = (Student) student;
                    if (tempStudent.getAttempt() != null ) {
                        Grade grade = (Grade)request.getSession().getAttribute("grade");
                        Map<Tent, Double> test =  tempStudent.getAttempt().getTotalPercentagesPerTent(service, grade);
                        request.setAttribute("LijstTentenPercentage",test);
                    }

                    request.setAttribute("competences", service.getAllTenten());
                    request.setAttribute("attemptTotalScore", ((Student) student).getAttempt().getTotalScore());
                    request.setAttribute("attemptTotalAttempted", ((Student) student).getAttempt().getTotalQuestionsAnswered());
                    request.getRequestDispatcher("/result.jsp").forward(request, response);
                    return;
                }
                request.setAttribute("tentName", tent.getName());
                request.setAttribute("tentNumber", tent.getId());
                request.setAttribute("tentQuestions", service.getQuestions(tent.getId(), (Grade)request.getSession().getAttribute("grade")));
                request.setAttribute("service", service);
                request.getRequestDispatcher("/test.jsp").forward(request, response);
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            return;
        } else if (requestResource.equals("addQuestion")) {
            if (userIsAdmin(request)) {
                String placenumber = request.getParameter("question");
                Tent tent;
                try {
                    tent = service.getTent(Integer.parseInt(placenumber));
                    request.setAttribute("tent", tent);

                    request.getRequestDispatcher("/addQuestion.jsp").forward(request, response);
                    return;
                } catch(NumberFormatException ex) {
                    ex.printStackTrace();
                }

            } else {
                request.getRequestDispatcher("/index.jsp").forward(request, response);
                return;
            }

        } else if (requestResource.equals("submitQuestion")) {

            request.getRequestDispatcher(handleSubmitQuestion(request, response)).forward(request, response);
            return;

        }
        // if no route was found, show error. Make sure to return after each forward!
        request.getRequestDispatcher("/error.jsp").forward(request, response);

    }

    private User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("loggedIn");
    }

    private String handleCompetences(HttpServletRequest request, HttpServletResponse response) {
        User x = getUser(request);
        Student student= null;
        if (x instanceof Student) {
             student =(Student) x;

            if (student.getAttempt() != null ) {
                Grade grade = (Grade)request.getSession().getAttribute("grade");
                Map<Tent, Double> test =  student.getAttempt().getTotalPercentagesPerTent(service, grade);
                request.setAttribute("LijstTentenPercentage",test);
            }
        }

        request.setAttribute("competences", service.getAllTenten());

        if (userIsAdmin(request)) {
            System.out.println("user is admin");
            request.setAttribute("isAdmin", true);
        }

        return "/competences.jsp";
    }

    private String handleSubmitQuestion(HttpServletRequest request, HttpServletResponse response) {
        Tent tent = service.getTent(Integer.parseInt(request.getParameter("placenumber")));

        String questionvalue = request.getParameter("questionvalue");
        Grade grade = Grade.valueOf(request.getParameter("grade"));
        if (questionvalue != null && !questionvalue.trim().isEmpty() && grade != null) {
            Question question = new Question(questionvalue, grade);
            List<Answer> answers = new ArrayList<>();

            for (int i = 0; i < 6; i++) {
                int value;
                try {
                    String pointrequest = "point" +  String.valueOf(i + 1);
                    String points = request.getParameter(pointrequest);
                    value = Integer.parseInt(points);
                } catch (NumberFormatException ex) {
                    value = -1;
                }

                String answerstring = request.getParameter("answer" + String.valueOf(i+1));

                if (answerstring != null && !answerstring.trim().isEmpty() && value != -1) {
                    answers.add(new Answer(answerstring, value));
                }
            }
            if (answers.size() > 0) {
                int questionId = service.addQuestion(tent.getId(), question);
                for (Answer answer: answers) {
                    service.addAnswer(questionId, answer);
                }


                return handleCompetences(request, response);
            }
        }

        return "/index.jsp";
    }

    private boolean userIsLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("loggedIn") == null) {
            return false;
        } else {
            if ((User)session.getAttribute("loggedIn") instanceof Teacher) {
                request.setAttribute("teacher", "true");
            } else request.setAttribute("teacher", null);
            return true;
        }
    }

    private boolean userIsAdmin(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loggedIn");
        return (user instanceof Admin);
    }

    private boolean userIsStudent(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loggedIn");
        return (user instanceof Student);
    }

    private boolean userIsTeacher(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("loggedIn");
        return (user instanceof Teacher);
    }

    private String getUserRole(HttpServletRequest request) {
        if (userIsAdmin(request)) return "admin";
        if (userIsTeacher(request)) return "teacher";
        if (userIsStudent(request)) return "student";
        return "";
    }
}
