package com.travelone.web.servlet;

import com.travelone.domain.User;
import com.travelone.service.UserService;
import com.travelone.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/active")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByCode(code);
        if(user != null){
            user.setStatus("yes");
            int i = userService.updateUserStatus(user);
            if(i == 1){
                response.sendRedirect("login.html");
            }else {
                response.sendRedirect("error.html");
            }
        }else {
            response.sendRedirect("error.html");
        }
    }
}
