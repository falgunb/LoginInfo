package com.k.logininfo.Model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.annotations.SerializedName;

public class User {

    Context context;
    SharedPreferences sharedPreferences;
    public String id;
    public String name;
    public String userName;
    public String password;

    public void removeUser() {
        sharedPreferences.edit().clear().commit();
    }


    public String getUserName() {
        userName = sharedPreferences.getString("userdata", "");
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        sharedPreferences.edit().putString("userdata", userName).commit();
    }

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
