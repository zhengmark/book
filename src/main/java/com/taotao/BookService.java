package com.taotao;

import com.taotao.pojo.Tb_book_info;
import org.apache.ibatis.session.SqlSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
    // Ê¾Àý·½·¨£ºÌí¼ÓÍ¼ÊéÐÅÏ¢
    public void insertBookInfo(Map<String, Object> bookInfo) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            sqlSession.insert("book.insert_book_info", bookInfo);
            sqlSession.commit();
        }
    }

    // Ê¾Àý·½·¨£ºÉ¾³ýÍ¼ÊéÐÅÏ¢
    public void deleteBookInfo(Map<String, Object> bookInfo) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            sqlSession.delete("book.delete_book_info", bookInfo);
            sqlSession.commit();
        }
    }

    // Ê¾Àý·½·¨£º¸üÐÂÍ¼Êé¿â´æ
    public void updateBookInventory(Map<String, Object> inventoryInfo) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            sqlSession.update("book.add_book_inventory", inventoryInfo);
            sqlSession.commit();
        }
    }

    // Ê¾Àý·½·¨£º²éÑ¯Í¼Êé
    public List<Tb_book_info> searchBook(String bookName) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            return sqlSession.selectList("book.search_book", bookName);
        }
    }

    // ¸ü¶à·½·¨ÊµÏÖ...
    // Äú¿ÉÒÔ¸ù¾ÝÐèÒªÌí¼Ó¸ü¶àµÄ·½·¨£¬ÀýÈçÓÃ»§×¢²á¡¢µÇÂ¼¡¢ÆÀÂÛ¡¢¶©µ¥´¦ÀíµÈ¡£
}
