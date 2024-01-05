package com.taotao;

import com.taotao.pojo.Tb_book_info;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookSearchService {
    /*
    图书查询功能
    通过调用内部book_search，传入参数input
    进行数据库内容检索，
    如果成功匹配内容，那么会返回该图书的信息
    如果没有匹配成功，返回一本book_id为“1111”的图书信息
    返回类型为Tb_book_info
     */

    //查询图书信息
    public Tb_book_info searchBook(String bookName) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("book_name", bookName);

            // 执行查询操作
            List<Tb_book_info> bookSearch = sqlSession.selectList("search_book.search_book", params);
            if (bookSearch.isEmpty()) {
                Tb_book_info impossible = new Tb_book_info();
                impossible.setId("1111");
                System.out.println("该书不存在");
                return impossible;
            } else {
                return bookSearch.get(0);
            }
        }
    }
    //插入图书信息
    public void insertBook(String book_name,String category,String book_id,Integer unit_price,String author,String introduce,Integer inventory,String picture) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("book_name", book_name);
            params.put("category",category);
            params.put("book_id",book_id);
            params.put("unit_price",unit_price);
            params.put("author",author);
            params.put("introduce",introduce);
            params.put("inventory",inventory);
            params.put("picture",picture);

            // 执行查询操作
            sqlSession.insert("book.insert_book_info", params);
            sqlSession.commit();
        }
    }
    public void updateBook(String book_id, String book_name, String category, Integer unit_price, String author, String introduce, Integer inventory, String picture) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("book_id", book_id);
            params.put("book_name", book_name);
            params.put("category", category);
            params.put("unit_price", unit_price);
            params.put("author", author);
            params.put("introduce", introduce);
            params.put("inventory", inventory);
            params.put("picture", picture);

            sqlSession.update("book.update_book_info", params);
            sqlSession.commit();
        }
    }
    public void deleteBook(String bookId) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            sqlSession.delete("book.delete_book_info", bookId);
            sqlSession.commit();
        }
    }


    // 发货
    public void updateBookQuantity(String userId, String bookId, int quantityPurchased) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("book_id", bookId);
            params.put("user_id", userId);
            params.put("quantity_purchased", quantityPurchased);
            sqlSession.update("book.update_book_num", params);
            sqlSession.commit();
        }
    }

    // 补货
    public void addBookInventory(String bookId, int addInventory) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("book_id", bookId);
            params.put("add_inventory", addInventory);
            sqlSession.update("book.add_book_inventory", params);
            sqlSession.commit();
        }
    }
}
