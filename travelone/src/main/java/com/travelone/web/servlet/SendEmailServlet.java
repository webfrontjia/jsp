package com.travelone.web.servlet;

import com.travelone.domain.User;
import com.travelone.service.UserService;
import com.travelone.service.impl.UserServiceImpl;
import com.travelone.utils.SendEmail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/sendemail")
public class SendEmailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String username = request.getParameter("username");

        UserService userService = new UserServiceImpl();
        User user = userService.findUserByUsername(username);

        UUID uuid = UUID.randomUUID();
        user.setCode(uuid.toString());
        int result = userService.updateUser(user);
        if(result==1){
            String title = "我是旅游网注册中心";
            String content = "欢迎注册旅游网，请点击调转 <a style='color:red;font-size:25px' " +
                    "href='http://localhost:8086/travelone/active?code=" +uuid.toString()+
                    "'>登录</a>" ;
            SendEmail sendEmail = new SendEmail(email, title, content);
            sendEmail.start();
            response.sendRedirect("register_ok.html");
        }else {
            response.sendRedirect("error.html");
        }

    }
}

