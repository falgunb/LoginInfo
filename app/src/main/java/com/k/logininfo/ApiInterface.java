package com.k.logininfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("register.php")
    Call<User> performRegistration(@Query("name") String name, @Query("user_name") String user_name, @Query("user_password") String user_password);

    @GET("login.php")
    Call<User> performUserLogIn(@Query("user_name") String user_name,@Query("user_password") String user_password);

}
