package com.taotao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserInfoUpdater {

    private static final Logger logger = LogManager.getLogger(UserInfoUpdater.class);

    public Boolean updateUserInformation(String user_id, String password, String phone_num, String address, String birth) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", user_id);
            params.put("phone_num", phone_num);
            params.put("address", address);
            params.put("birth", birth);
            params.put("password", password);

            int result = sqlSession.update("update_user_info", params);
            sqlSession.commit();

            return result > 0;
        } catch (Exception e) {
            logger.error("Error updating user info", e);
            return false;
        }
    }
}
