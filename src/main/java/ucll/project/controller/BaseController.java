package ucll.project.controller;

import ucll.project.domain.user.User;
import ucll.project.domain.user.UserRepository;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    private UserRepository userRepository;

    public BaseController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    protected boolean isLoggedIn(HttpServletRequest request) {
        if (request.getSession().getAttribute("userid") == null) {
            return false;
        }
        else {
            int userId = (int)request.getSession().getAttribute("userid");
            User user = getUserRepository().get(userId);
            request.setAttribute("user", user);
            return true;
        }
    }

    protected User getUser(HttpServletRequest request){
        if (request.getSession().getAttribute("userid") == null) {
            return null;
        }
        else {
            int userId = (int)request.getSession().getAttribute("userid");
            return getUserRepository().get(userId);
        }
    }

    protected boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public UserRepository getUserRepository() {
        return this.userRepository;
    }
}
