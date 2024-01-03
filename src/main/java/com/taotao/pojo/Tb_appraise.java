package com.taotao.pojo;

public class Tb_appraise {
    private String user_id;
    private String book_id;
    private String appraise;

    public String getId() {
        return user_id;
    }

    public void setId(String id) {
        this.user_id = id;
    }

    public String getUsername() {
        return book_id;
    }

    public void setUsername(String username) {
        this.book_id = username;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {this.appraise = appraise;
    }

    @Override
    public String toString() {
        return "Tb_appraise{" +
                "user_id=" + user_id +
                ", book_id='" + book_id + '\'' +
                ", appraise='" + appraise + '\'' +
                '}';
    }
}

