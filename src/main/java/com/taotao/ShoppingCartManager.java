package com.taotao;

import com.taotao.pojo.Tb_book_info;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class ShoppingCartManager {
    // 添加到购物车功能
    public void addItemsToCart(String userId, String bookId, Integer quantityPurchased) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            BookSearchService book_search = new BookSearchService();
            Tb_book_info book_info = book_search.searchBook(bookId);

            Map<String, Object> params = new HashMap<>();
            params.put("user_id", userId);
            params.put("book_id", bookId);
            params.put("unit_price", book_info.getUnit_price());
            params.put("inventory", book_info.getInventory());
            params.put("quantity_purchased", quantityPurchased);
            sqlSession.insert("book.order_on_shop", params);

            sqlSession.commit();
        }
    }

    // 购物车删除功能
    public void removeItemFromCart(String userId, String bookId) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", userId);
            params.put("book_id", bookId);
            sqlSession.delete("book.delete_shop_cart", params);

            sqlSession.commit();
        }
    }

    // 购物车下单功能
    public void placeOrder(String userId, String bookId) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", userId);
            params.put("book_id", bookId);
            sqlSession.update("book.check_order", params);

            sqlSession.commit();
        }
    }
}
