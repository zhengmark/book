package com.taotao;

import com.taotao.pojo.Tb_book_info;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/BookManagementServlet") // 设置 Servlet 的 URL
public class BookManagementServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JSONObject requestData = MyUtil.parseJSONRequest(request);
        JSONObject responseJson = new JSONObject();
        BookSearchService service = new BookSearchService();

        String action = requestData.optString("action");

        try {
            switch (action) {
                case "insert":
                    service.insertBook(
                            requestData.optString("book_name"),
                            requestData.optString("category"),
                            requestData.optString("book_id"),
                            requestData.optInt("unit_price"),
                            requestData.optString("author"),
                            requestData.optString("introduce"),
                            requestData.optInt("inventory"),
                            requestData.optString("picture")
                    );
                    responseJson.put("message", "图书添加成功");
                    break;
                case "update":
                    // ÌáÈ¡±ØÒªµÄ²ÎÊý
                    String book_id = requestData.optString("book_id");
                    String book_name = requestData.optString("book_name");
                    String category = requestData.optString("category");
                    Integer unit_price = requestData.optInt("unit_price");
                    String author = requestData.optString("author");
                    String introduce = requestData.optString("introduce");
                    Integer inventory = requestData.optInt("inventory");
                    String picture = requestData.optString("picture");

                    // µ÷ÓÃ updateBook ·½·¨
                    service.updateBook(book_id, book_name, category, unit_price, author, introduce, inventory, picture);
                    responseJson.put("message", "图书更新成功");
                    break;

                case "delete":
                    // Ìí¼ÓÉ¾³ýÍ¼ÊéÐÅÏ¢µÄÂß¼­
                    service.deleteBook(requestData.optString("book_id"));
                    responseJson.put("message", "图书删除功能");
                    break;
                case "search":
                    // Ìí¼Ó²éÑ¯Í¼ÊéÐÅÏ¢µÄÂß¼­
                    Tb_book_info book = service.searchBook(requestData.optString("book_name"));
                    responseJson.put("book_info", book); // 需要将tb_book_info对象转换为json
                    break;
                default:
                    responseJson.put("message", "未知操作");
            }
        } catch (Exception e) {
            responseJson.put("message", "操作失败: " + e.getMessage());
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseJson.toString());
    }
}


