package com.taotao;

import com.taotao.pojo.Tb_book_info;
import com.taotao.pojo.Tb_user;
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

public class ShoppingCartManager {
        /*
    购物车下单功能
    通过调用内部user_appraise，传入参数user_id,book_id,appraise
    在评价表中插入以上信息
     */
        public void addItemsToCart(String user_id, String book_id, Integer quantity_purchased)throws IOException {

            //1.加载mybatis的核心配置文件，获取 SqlSessionFactory
            String resource = "mybatis-config.xml"; //相对路径
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //2.获取SqlSession对象，用它来执行sql
            SqlSession sqlSession = sqlSessionFactory.openSession();

// 执行插入操作
            Book_search book_search = new Book_search();
//            (user_id,book_id,unit_price,inventory,quantity_purchased,shipments)

            Tb_book_info book_info = book_search.book_search(book_id);
//            System.out.println(book_info);
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", user_id);
            params.put("book_id", book_id);
            params.put("unit_price", book_info.getUnit_price());
            params.put("inventory", book_info.getInventory());
            params.put("quantity_purchased", quantity_purchased);
            sqlSession.insert("book.order_on_shop", params);
            sqlSession.commit();
            sqlSession.close();
        }

        /*
        购物车删除功能，
        传入参数user_id，book_id
        将数据库中的对应行删除
         */
        public void deleteItemsCart(String user_id, String book_id)throws IOException {
            String resource = "mybatis-config.xml"; //相对路径
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            //2.获取SqlSession对象，用它来执行sql
            SqlSession sqlSession = sqlSessionFactory.openSession();

// 执行插入操作
            Book_search book_search = new Book_search();
            Tb_book_info book_info = book_search.book_search(book_id);
            Map<String, Object> params = new HashMap<>();
            params.put("user_id",user_id);
            params.put("book_id",book_id);
            sqlSession.delete("book.delete_shop_cart", params);

            sqlSession.commit();
            sqlSession.close();
        }
}
