package com.taotao;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "registerServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 设置响应类型和字符编码为UTF-8
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        // 使用UTF-8字符集读取请求体
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder requestDataBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestDataBuilder.append(line);
        }

        // 解析请求中的JSON数据
        JSONObject requestData = new JSONObject(requestDataBuilder.toString());

        String username = requestData.getString("username");
        String password = requestData.getString("password");

        UserRegistrar registrar = new UserRegistrar();
        JSONObject jsonResponse = new JSONObject();
        try {
            if (registrar.registerUser(username, password)) {
                jsonResponse.put("success", true);
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "用户名已存在");
            }
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "服务器错误: " + e.getMessage());
        }

        // 使用PrintWriter发送UTF-8编码的响应
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
    }
}
