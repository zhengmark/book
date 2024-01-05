package com.taotao;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RemoveFromCartServlet", urlPatterns = {"/RemoveFromCartServlet"})
public class RemoveFromCartServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 解析请求中的JSON数据
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String requestData = sb.toString();

        Gson gson = new Gson();
        Map<String, Object> requestDataMap = gson.fromJson(requestData, Map.class);

        String userId = (String) requestDataMap.get("userId");
        String bookId = (String) requestDataMap.get("bookId");

        ShoppingCartManager manager = new ShoppingCartManager();
        manager.removeItemFromCart(userId, bookId);

        // 响应
        Map<String, String> result = new HashMap<>();
        result.put("message", "商品已从购物车中删除");
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(gson.toJson(result));
        }
    }
}
