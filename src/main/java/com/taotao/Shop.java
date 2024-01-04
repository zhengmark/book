package com.taotao;

import com.taotao.pojo.Tb_appraise;
import com.taotao.pojo.Tb_book_info;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Shop {
        /*
    购物车下单功能
    通过调用内部user_appraise，传入参数user_id,book_id,appraise
    在评价表中插入以上信息
     */
        public void shop(String user_id,String book_id,Integer quantity_purchased)throws IOException {

            //1.加载mybatis的核心配置文件，获取 SqlSessionFactory
            String resource = "mybatis-config.xml"; //相对路径
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //2.获取SqlSession对象，用它来执行sql
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Random random = new Random();

// 执行插入操作
            Book_search book_search = new Book_search();
            Tb_book_info book_info = book_search.book_search(book_id);
//            System.out.println(book_info);

            sqlSession.commit();
            sqlSession.close();
        }
}
