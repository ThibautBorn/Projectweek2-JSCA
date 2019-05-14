package ucll.project.controller;

import ucll.project.domain.user.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class UserController extends BaseController {

    public UserController(UserRepository userRepository) {
        super(userRepository);
    }

    public void getLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    public void postLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (username == null || password == null) {
                request.setAttribute("error", "Invalid login");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            try {
                User user = getUserRepository().loginUser(username, password);
                //request.getSession().setAttribute("userid", user.getUserId());
                request.setAttribute("user", user);
                response.sendRedirect("/");
            } catch (InvalidLogin error) {
                request.setAttribute("username", username);
                request.setAttribute("error", error.getMessage());
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("userid");
        try {
            response.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSignup(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setAttribute("genders", Gender.values());
            //request.setAttribute("roles", Role.values());
            if (request.getMethod().equals("GET")) {
                request.getRequestDispatcher("/signup.jsp").forward(request, response);
            } else if (request.getMethod().equals("POST")) {
                ArrayList<String> errors = new ArrayList<>();
                try {
                    String email = request.getParameter("email");
                    String userName = request.getParameter("userName");
                    String firstName = request.getParameter("firstName");
                    String lastName = request.getParameter("lastName");
                    String genderinput = request.getParameter("gender");
                    //String roleinput = request.getParameter("role");
                    String password = request.getParameter("password");
                    String passwordRepeat = request.getParameter("passwordRepeat");

                    request.setAttribute("email", email);
                    request.setAttribute("userName", userName);
                    request.setAttribute("firstName", firstName);
                    request.setAttribute("lastName", lastName);
                    request.setAttribute("gender", genderinput);
                    //request.setAttribute("role", roleinput);

                    if (email == null || email.length() == 0) errors.add("Email is required");
                    if (userName == null || userName.length() == 0) errors.add("User name is required");
                    if (firstName == null || firstName.length() == 0) errors.add("First name is required");
                    if (lastName == null || lastName.length() == 0) errors.add("Last name is required");
                    if (genderinput == null) errors.add("Gender is required");
                    //if (roleinput == null) errors.add("Role is required");
                    if (password == null || password.length() == 0) errors.add("Password is required");
                    if (password != null && password.length() < 4) errors.add("Password too short");
                    if (passwordRepeat == null) errors.add("Password repeat is required");
                    if (!password.equals(passwordRepeat)) errors.add("Passwords don't match");

                    if (errors.size() == 0) {
                        try {
                            Gender gender = Gender.valueOf(genderinput);
                            //Role role = Role.valueOf(roleinput);

                            User user = new Student(userName, firstName, lastName, email, gender);

                            getUserRepository().createUser(user, password);

                            response.sendRedirect("/user/login");
                        } catch (Exception e) {
                            errors.add(e.getMessage());
                            request.setAttribute("errors", errors);
                            request.getRequestDispatcher("/signup.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("errors", errors);
                        request.getRequestDispatcher("/signup.jsp").forward(request, response);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
