package com.taotao;

import com.taotao.pojo.Tb_shop;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopViewer {
    public void show_shop(String user_id) throws IOException {
        try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
            Map<String, Object> params = new HashMap<>();
            params.put("user_id", user_id);

            List<Tb_shop> showShop = sqlSession.selectList("book.search_shop", params);
            System.out.println(showShop);
        }
    }
}
