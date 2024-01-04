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
}
