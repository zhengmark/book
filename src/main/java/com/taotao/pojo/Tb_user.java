package com.taotao.pojo;

public class Tb_user {
    private String user_id;
    private String user_name;
    private String password;
    private String phone_num;
    private String address;
    private String birth;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String id) {this.user_id = id;
    }

    public String getUsername() {
        return user_name;
    }

    public void setUsername(String username) {
        this.user_name = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Tb_user{" +
                "id=" + user_id +
                ", name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", birth='" + birth + '\'' +
                '}';
    }
}

