package com.k.logininfo;

import com.google.gson.annotations.SerializedName;

public class User {


    @SerializedName("response")
    private String response;
    @SerializedName("name")
    private String name;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("user_password")
    private String user_password;


    public String getResponse() {
        return response;
    }

    public String getUserName() {
        return user_name;
    }

    public String getName(){
        return name;
    }

    public String getUserpassword() {
        return user_password;
    }

    public String toString(){
        return "User{" +
                "response='" + response + '\''+
                ", name='" + name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                '}';

    }

}
