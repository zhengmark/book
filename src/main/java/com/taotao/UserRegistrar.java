package com.taotao;

import com.taotao.pojo.Tb_user;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UserRegistrar {
    /*
    注册功能
    通过调用内部register，传入参数username，password
    进行数据库内容检索，
    如果没有成功匹配内容，那么返回True，并且随机生成六位id作为用户编码
    如果匹配成功，返回False
    返回类型为Boolean
     */

    public Boolean registerUser(String name, String pwd) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_name", name);
            params.put("password", pwd);

            // 执行查询操作
            List<Tb_user> userList = sqlSession.selectList("book.search_name", params);
            if (userList.isEmpty()) {
                String userId = generateUserId(); // 生成随机用户ID
                params.put("user_id", userId);
                sqlSession.insert("book.insert_info", params);
                sqlSession.commit();
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        }
    }

    private String generateUserId() {
        Random random = new Random();
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(10);
            randomNumbers.append(randomNumber);
        }
        return randomNumbers.toString();
    }
}
