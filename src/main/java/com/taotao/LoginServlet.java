package com.taotao;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject jsonRequest = MyUtil.parseJSONRequest(request);
        String username = jsonRequest.optString("username");
        String password = jsonRequest.optString("password");

        UserAuthentication auth = new UserAuthentication();
        String userId = auth.authenticateUser(username, password);
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("user_id", userId);

        response.setContentType("application/json");
        response.getWriter().write(jsonResponse.toString());

        if (!userId.equals("111111")) {
            Cookie loginCookie = new Cookie("userId", userId);
            System.out.println(loginCookie);
            loginCookie.setPath("/");
            loginCookie.setMaxAge(30 * 60);
            response.addCookie(loginCookie);
        }
    }
}
