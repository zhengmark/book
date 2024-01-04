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

public class User_appraise {
    /*
    用户评价功能
    通过调用内部user_appraise，传入参数user_id,book_id,appraise
    在评价表中插入以上信息
     */
    public void user_appraise(String user_id,String book_id,String appraise)throws IOException {

        //1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml"; //相对路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Random random = new Random();


        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("book_id",book_id);
        params.put("appraise",appraise);


// 执行插入操作
        List<Tb_appraise> user_appraise = sqlSession.selectList("book.insert_appraise", params);
        sqlSession.commit();
        sqlSession.close();
    }
}
