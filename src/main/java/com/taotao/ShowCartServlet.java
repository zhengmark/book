package com.taotao;

import com.taotao.pojo.Tb_shop;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ShowCartServlet")
public class ShowCartServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("userId".equals(cookie.getName())) {
                    userId = cookie.getValue();
                    break;
                }
            }
        }

        System.out.println("user_id = "+ userId);

        // 如果没有找到 userId，返回错误
        if (userId == null || userId.equals("111111")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "未登录或用户ID无效");
            return;
        }

        JSONArray cartItems = new JSONArray();
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            List<Tb_shop> showShop = sqlSession.selectList("book.search_shop", userId);

            for (Tb_shop item : showShop) {
                JSONObject cartItem = new JSONObject();
                cartItem.put("bookId", item.getBookId());
                cartItem.put("quantity", item.getQuantityPurchased());
                cartItems.put(cartItem);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "服务器错误：" + e.getMessage());
            return;
        }

        JSONObject responseJson = new JSONObject();
        responseJson.put("cartItems", cartItems);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseJson.toString());
    }
}
