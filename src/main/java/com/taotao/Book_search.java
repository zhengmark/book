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

public class Book_search {
    /*
    图书查询功能
    通过调用内部book_search，传入参数input
    进行数据库内容检索，
    如果成功匹配内容，那么会返回该图书的信息
    如果没有匹配成功，返回一本book_id为“1111”的图书信息
    返回类型为Tb_book_info
     */
    public Tb_book_info book_search(String input)throws IOException {

        //1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml"; //相对路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Random random = new Random();


        Map<String, Object> params = new HashMap<>();
        params.put("book_name", input);


// 执行插入操作
        List<Tb_book_info> book_search = sqlSession.selectList("search_book.search_book", params);
        if (book_search.isEmpty()) {
            Tb_book_info impossible = new Tb_book_info();
            impossible.setId("1111");
            System.out.println("该书不存在");
            sqlSession.commit();
            sqlSession.close();
            return impossible;
        } else {
            sqlSession.commit();
            sqlSession.close();
            return book_search.get(0);
        }
    }
}
