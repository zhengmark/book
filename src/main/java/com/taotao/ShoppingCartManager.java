package com.taotao;

import com.taotao.pojo.Tb_book_info;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class ShoppingCartManager {
    /*
    购物车下单功能
    通过调用内部user_appraise，传入参数user_id,book_id,appraise
    在评价表中插入以上信息
     */
    public void addItemsToCart(String user_id, String book_id, Integer quantity_purchased) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {

// 执行插入操作
            BookSearchService book_search = new BookSearchService();
            Tb_book_info book_info = book_search.searchBook(book_id);
//            System.out.println(book_info);

            sqlSession.commit();
        } catch (Exception e) {
            // 如果需要的话，这里可以处理或者重新抛出异常
            throw e;
        }
    }
}
