package com.k.logininfo.Model;

import com.google.gson.annotations.SerializedName;

public class User {


    public String id;
    public String name;
    public String userName;
    public String password;

    public User(String id, String userName, String name, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

//    @SerializedName("response")
//    private String response;
//    @SerializedName("name")
//    private String name;
//    @SerializedName("user_name")
//    private String user_name;
//    @SerializedName("user_password")
//    private String user_password;
//
//
//    public String getResponse() {
//        return response;
//    }
//
//    public String getUserName() {
//        return user_name;
//    }
//
//    public String getName(){
//        return name;
//    }
//
//    public String getUserpassword() {
//        return user_password;
//    }
//
//    public String toString(){
//        return "User{" +
//                "response='" + response + '\''+
//                ", name='" + name + '\'' +
//                ", user_name='" + user_name + '\'' +
//                ", user_password='" + user_password + '\'' +
//                '}';
//
//    }

}
