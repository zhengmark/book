package com.taotao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class UserInfoUpdater {

    private static final Logger logger = LogManager.getLogger(UserInfoUpdater.class);

    public Boolean updateUserInformation(String userId, String password, String phoneNumber, String address, String birthDate) {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", userId);
            params.put("phone_num", phoneNumber);
            params.put("address", address);
            params.put("birth", birthDate);
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
