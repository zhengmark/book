package com.taotao;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "registerServlet", urlPatterns = {"/RegisterServlet"})
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // 解析请求中的JSON数据
        JSONObject requestData = MyUtil.parseJSONRequest(request);

        String username = (String) requestData.get("username");
        String password = (String) requestData.get("password");
        String phone = (String) requestData.get("phone");
        String address = (String) requestData.get("address");
        String birth = (String) requestData.get("birth");

        UserInfoUpdater updater = new UserInfoUpdater();
        JSONObject jsonResponse = new JSONObject();
        try {
            if (updater.updateUserInformation(username, password, phone, address, birth)) {
                jsonResponse.put("success", true);
            } else {
                jsonResponse.put("success", false);
                jsonResponse.put("message", "无法更新用户信息");
            }
        } catch (Exception e) {
            jsonResponse.put("success", false);
            jsonResponse.put("message", "服务器错误: " + e.getMessage());
        }

        out.print(jsonResponse);
        out.flush();
    }
}