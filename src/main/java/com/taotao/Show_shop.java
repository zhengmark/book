package com.taotao;

import com.taotao.pojo.Tb_appraise;
import com.taotao.pojo.Tb_shop;
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

public class Show_shop {
    public void show_shop(String user_id,Integer order)throws IOException {
        //1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml"; //相对路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Random random = new Random();

        if (order == 0){
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", user_id);

            List<Tb_shop> show_shop = sqlSession.selectList("book.search_shop", params);
            System.out.println(show_shop);
            sqlSession.commit();
            sqlSession.close();
        }
    }
}
