package com.travelone.web.servlet;

import com.alibaba.fastjson.JSON;
import com.travelone.domain.ResultCode;
import com.travelone.domain.ResultInfo;
import com.travelone.domain.User;
import com.travelone.service.UserService;
import com.travelone.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode = (String) session.getAttribute("checkcode");
        session.removeAttribute("checkcode");
        if(!checkcode.equalsIgnoreCase(check)){
            ResultInfo resultInfo = new ResultInfo(ResultCode.CHECKCODENOTEQUAL,null,ResultCode.CHECKCODENOTEQUALMSG);
            String json = JSON.toJSONString(resultInfo);
            response.getWriter().print(json);
        }else {
            Map<String, String[]> parameterMap = request.getParameterMap();
            User user = new User();
            try {
                BeanUtils.populate(user,parameterMap);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            UserService userService = new UserServiceImpl();
            ResultInfo resultInfo = userService.addUser(user);
            String json = JSON.toJSONString(resultInfo);
            response.getWriter().print(json);
        }
    }
}
