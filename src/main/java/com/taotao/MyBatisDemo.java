package com.taotao;

import com.taotao.pojo.Tb_appraise;
import com.taotao.pojo.Tb_book_info;
import com.taotao.pojo.Tb_shop;
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


public class MyBatisDemo {
    public static void main(String[] args) throws IOException {
        //1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml"; //相对路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.执行sql
        //返回用户信息
        List<Tb_user> users = sqlSession.selectList("user.selectUser");
        List<Tb_book_info> book_info = sqlSession.selectList("book_info.selectBook");
        List<Tb_appraise> appraise = sqlSession.selectList("appraise.selectAppraise");
        List<Tb_shop> shop = sqlSession.selectList("shop.selectShop");
        System.out.println(users);
        System.out.println(book_info);
        System.out.println(appraise);
        System.out.println(shop);

        //4.释放资源
        sqlSession.close();
    }
}


