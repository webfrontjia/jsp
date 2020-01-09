package com.travelone.web.servlet;

import com.alibaba.fastjson.JSON;
import com.travelone.domain.ResultCode;
import com.travelone.domain.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/showusername")
public class ShowUnameServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        if(username != null ){
            ResultInfo<String> resultInfo = new ResultInfo<>(ResultCode.SUCCESS, username, null);
            String json = JSON.toJSONString(resultInfo);
            response.getWriter().print(json);
        }else {
            ResultInfo<String> resultInfo = new ResultInfo<>(ResultCode.NOTLOGIN, null, null);
            String json = JSON.toJSONString(resultInfo);
            response.getWriter().print(json);
        }

    }
}
