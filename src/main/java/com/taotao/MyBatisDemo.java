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
import java.util.ArrayList;
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
//        Tb_user new_user = new Tb_user();
//        String id = "123456";
//        String name = "客户";
//        String pwd = "20021114";
////这是测试注册函数
//        Register re = new Register();
//        String name = "zheng";
//        String pwd = "22222";
//        boolean a1 = re.register(name,pwd);
//        System.out.println(a1);
//这是测试登录函数
        Map<String, Object> params_log = new HashMap<>();
        String name_log = "zheng";
        String pwd_log = "22222";
        params_log.put("user_name", name_log);
        params_log.put("password", pwd_log);
        Log log = new Log();
        String a = log.log(name_log,pwd_log);
        System.out.println(a);


//        System.out.println(a);
// 提交事务（如果需要的话）
        sqlSession.commit();
//
//        System.out.println(users);
//        System.out.println(log);
//        System.out.println(book_info);
//        System.out.println(appraise);
//        System.out.println(shop);

        //4.释放资源
        sqlSession.close();
    }
}


