package com.taotao;

import com.google.gson.Gson;

import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class RecoverPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 读取请求数据
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String requestData = sb.toString();

        // 解析JSON
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> requestDataMap = gson.fromJson(requestData, mapType);
        String email = requestDataMap.get("email");

        // TODO: 实际业务逻辑
        // 例如，验证电子邮件，生成重置链接等
        boolean isEmailValid = validateEmail(email); // 模拟电子邮件验证
        Map<String, String> result = new HashMap<>();

        if (isEmailValid) {
            String resetToken = generateResetToken(); // 生成重置令牌
            // 发送电子邮件（假设sendEmail方法已实现）
            boolean emailSent = sendEmail(email, resetToken);
            if (emailSent) {
                result.put("message", "重置密码的链接已发送至您的邮箱。");
            } else {
                result.put("message", "发送邮件失败，请稍后重试。");
            }
        } else {
            result.put("message", "提供的电子邮件地址无效。");
        }

        // 发送响应
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(gson.toJson(result));
        }
    }

    private boolean validateEmail(String email) {
        // 在此实现电子邮件验证逻辑
        return email != null && email.contains("@");
    }

    private String generateResetToken() {
        // 生成一个安全的重置令牌
        // 在实际应用中，应当使用更安全的令牌生成方式
        return Long.toHexString(Double.doubleToLongBits(Math.random()));
    }

    private boolean sendEmail(String email, String resetToken) {
        // 实际发送电子邮件的逻辑
        // 这里只是模拟发送电子邮件的过程
        System.out.println("Sending reset token " + resetToken + " to " + email);
        return true; // 假设邮件发送总是成功
    }
}

