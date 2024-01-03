package com.taotao;

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
//        String unername = "username";
//        String password = "123456";
//        List<Tb_user> users = sqlSession.selectList("test.selectAll");
        String username = "username";
        String password = "123456";

//      创建参数 Map
        Map<String, Object> params = new HashMap<>();
        params.put("name", username);
        params.put("password", password);

//      执行插入操作
        sqlSession.insert("user.insertUser", params);

//      提交事务（如果需要的话）
        sqlSession.commit();

        //4.释放资源
        sqlSession.close();
    }
}


