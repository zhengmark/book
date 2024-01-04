package com.taotao;

import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class User_appraise {
    /*
    用户评价功能
    通过调用内部user_appraise，传入参数user_id, book_id, appraise
    在评价表中插入以上信息
     */
    public void user_appraise(String user_id, String book_id, String appraise) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", user_id);
            params.put("book_id", book_id);
            params.put("appraise", appraise);

            // 执行插入操作
            sqlSession.insert("book.insert_appraise", params);
            sqlSession.commit();
        }
    }
}
