package com.taotao;

import com.taotao.pojo.Tb_appraise;
import com.taotao.pojo.Tb_shop;
import com.taotao.pojo.Tb_user;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyBatisDemo {
    public static void main(String[] args) throws IOException {
        try {
            // 使用 MyBatisUtil 获取 SqlSession
            try (SqlSession sqlSession = MyBatisUtil.getSqlSession()) {
                // 3.执行sql
                // 返回用户信息
                List<Tb_user> users = sqlSession.selectList("book.selectUser");

                List<Tb_appraise> appraise_all = sqlSession.selectList("book.selectAppraise");
                List<Tb_shop> shop = sqlSession.selectList("book.selectShop");
//              Tb_user new_user = new Tb_user();
//              String id = "123456";
//              String name = "客户";
//              String pwd = "20021114";
                String birth = "2002";
                String password = "0000";
                String address = "上海理工大学";
                String name_log = "zheng";
                String pwd_log = "22222";
                String book_name = "大数据可视化";
                String book_id = "123";
                String phone_num = "13256789";
                String user_id = "123456";
                String appraise = "这本书真好看";
                Integer quantity_purchased = 5;
                Integer order = 0;

//这是测试注册函数
//                Register re = new Register();
//                String name = "zheng";
//                String pwd = "22222";
//                boolean a1 = re.register(name,pwd);
//                System.out.println(a1);

//这是测试登录函数
//                Map<String, Object> params_log = new HashMap<>();
//
//                params_log.put("user_name", name_log);
//                params_log.put("password", pwd_log);
//                LogIn log = new LogIn();
//                String a = log.logIn(name_log, pwd_log);

//返回图书信息
//                    Map<String, Object> params_search_book = new HashMap<>();
//
//                    params_search_book.put("book_name",book_name);
//                    params_search_book.put("book_id",book_id);
//                    Book_search book_search = new Book_search();
//                    System.out.println(book_search.book_search(book_id));

//插入用户评价
//                    Map<String, Object> params_insert_appraise = new HashMap<>();
//
//                    params_insert_appraise.put("appraise",appraise);
//                    params_insert_appraise.put("book_id",book_id);
//                    params_insert_appraise.put("user_id",user_id);
//                    UserAppraiser user_appraise = new UserAppraiser();
//                    user_appraise.addUserBookReview(user_id,book_id,appraise);
//                    System.out.println(appraise_all);

////插入购物车信息
//                    Shop shop_search = new Shop();
//                    shop_search.shop(user_id,book_id,quantity_purchased);
//
////查询购物车信息
//                    Show_shop show_shop = new Show_shop();
//                    show_shop.show_shop(user_id,order);


////更改信息
//                    Update_user_info up = new Update_user_info();
//                    up.update(user_id,password,phone_num,address,birth);
//                    sqlSession.commit();

////购物车增加和删除
                ShoppingCartManager add = new ShoppingCartManager();
                add.addItemsToCart(user_id,book_id,5);
                add.deleteItemsCart("123456","123");


//4.释放资源
                    sqlSession.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // 可以处理异常，如重新尝试连接或者提示用户
        }
    }
}


