package com.taotao;

import com.google.gson.Gson;
import com.taotao.pojo.Tb_book_info;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

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
        Integer quantityPurchased = ((Double) requestDataMap.get("quantityPurchased")).intValue();

        ShoppingCartManager manager = new ShoppingCartManager();
        manager.addItemsToCart(userId, bookId, quantityPurchased);

        // 响应
        Map<String, String> result = new HashMap<>();
        result.put("message", "商品已添加到购物车");
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println(gson.toJson(result));
        }
    }
}
