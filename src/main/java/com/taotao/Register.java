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

public class Register {
    /*
    注册功能
    通过调用内部register，传入参数username，password
    进行数据库内容检索，
    如果没有成功匹配内容，那么返回True，并且随机生成六位id作为用户编码
    如果匹配成功，返回False
    返回类型为Boolean
     */

    public Boolean register(String name, String pwd)throws IOException {

        //1.加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml"; //相对路径
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2.获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Map<String, Object> params = new HashMap<>();
        params.put("user_name", name);
        params.put("password", pwd);

// 执行插入操作
        List<Tb_user> log = sqlSession.selectList("book.search_name", params);
        if (log.isEmpty()){
            Map<String, Object> params_re = new HashMap<>();
            params_re.put("user_name", name);
            params_re.put("password", pwd);
            Tb_user user = new Tb_user();
            Random random = new Random();
            // 用于存储随机数字的字符串
            StringBuilder randomNumbers = new StringBuilder();
            // 生成六个随机数字，并将它们附加到字符串中
            for (int i = 0; i < 6; i++) {
                int randomNumber = random.nextInt(10); // 生成 0 到 9 之间的随机整数
                randomNumbers.append(randomNumber);
            }
            // 将随机数字的字符串打印出来
            String id = randomNumbers.toString();
            params_re.put("user_id",id);
            sqlSession.insert("book.insert_info", params_re);
            sqlSession.commit();
            sqlSession.close();
            return Boolean.TRUE;
        }
        else {
            sqlSession.commit();
            sqlSession.close();
            return Boolean.FALSE;
        }
    }


}
