package com.taotao;

import com.sun.org.apache.xpath.internal.operations.Bool;
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
import java.util.Random;

public class Update_user_info {
    /*

     */
    public Boolean update (String user_id,String password,String phone_num,String address,String birth)throws IOException {
        String resource = "mybatis-config.xml"; //相对路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Map<String, Object> params = new HashMap<>();
        params.put("user_id", user_id);
        params.put("phone_num", phone_num);
        params.put("address", address);
        params.put("birth", birth);
        params.put("password",password);
        try {
            int result = sqlSession.insert("update_user_info", params);
            sqlSession.commit(); // 提交事务

            if (result > 0) {
                return true; // 插入成功
            } else {
                return false; // 插入失败
            }
        } catch (Exception e) {
            sqlSession.rollback(); // 回滚事务
            return false; // 插入失败
        } finally {
            sqlSession.close();
        }
    }
}
