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

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.invalidate();
        ResultInfo<String> resultInfo = new ResultInfo<>(ResultCode.LOGOUT, null, null);

        String json = JSON.toJSONString(resultInfo);
        response.getWriter().print(json);
    }
}
