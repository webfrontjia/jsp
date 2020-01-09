package com.travelone.web.servlet;


import com.alibaba.fastjson.JSON;
import com.travelone.domain.ResultCode;
import com.travelone.domain.ResultInfo;
import com.travelone.service.UserService;
import com.travelone.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String check = request.getParameter("check");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String autologin = request.getParameter("autologin");

        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("checkcode");

        if(check == null || "".equals(check)){
            ResultInfo resultInfo = new ResultInfo(ResultCode.CHECKCODEISNULL,null,ResultCode.CHECKCODEISNULLMSG);
            String json = JSON.toJSONString(resultInfo);
            response.getWriter().print(json);
        }else {
            if(!checkcode.equalsIgnoreCase(check)){
                ResultInfo resultInfo = new ResultInfo(ResultCode.CHECKCODENOTEQUAL,null,ResultCode.CHECKCODENOTEQUALMSG);
                String json = JSON.toJSONString(resultInfo);
                response.getWriter().print(json);
            }else {
                UserService userService = new UserServiceImpl();
                ResultInfo resultInfo = userService.login(username, password);
                if(resultInfo.getCode() == 2000){
                    session.setAttribute("username",username);
                    if("islogin".equals(autologin)){
                        session.setAttribute("islogin",true);

                        Cookie cookie = new Cookie("JSESSIONID", session.getId());
                        cookie.setMaxAge(60*60);
                        response.addCookie(cookie);
                    }
                }
                String json = JSON.toJSONString(resultInfo);
                response.getWriter().print(json);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
