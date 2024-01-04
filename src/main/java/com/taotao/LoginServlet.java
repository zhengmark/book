package com.taotao;

import com.taotao.UserAuthentication;
import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserAuthentication auth = new UserAuthentication();
        String userId = auth.authenticateUser(username, password);

        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("userId", userId);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());

        if (!userId.equals("111111")) {
            Cookie loginCookie = new Cookie("userId", userId);
            loginCookie.setMaxAge(30 * 60); // 设置Cookie有效期为30分钟
            response.addCookie(loginCookie);
        }
    }
}
