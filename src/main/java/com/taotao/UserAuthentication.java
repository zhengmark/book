package com.taotao;

import com.taotao.pojo.Tb_user;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
登录功能
通过调用内部logIn，传入参数username，password
进行数据库内容检索，
如果成功匹配内容，那么会返回该用户的id
如果没有匹配成功，返回“111111”
返回类型为String
 */
public class UserAuthentication {
    public String authenticateUser(String username, String password) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_name", username);
            params.put("password", password);

            // 执行查询操作
            List<Tb_user> userList = sqlSession.selectList("log.search_name", params);
            if (userList.isEmpty()) {
                return "111111";
            } else {
                Tb_user user = userList.get(0); // 假设只有一个匹配的用户
                return user.getUser_id();
            }
        }
    }
}
