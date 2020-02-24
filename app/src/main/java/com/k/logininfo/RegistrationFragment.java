package com.k.logininfo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment {

    private EditText input_name, input_username, input_user_password;
    private Button btnRegister;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        input_name = view.findViewById(R.id.name);
        input_username = view.findViewById(R.id.input_username);
        input_user_password = view.findViewById(R.id.input_user_password);
        btnRegister = view.findViewById(R.id.register_btn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performRegistration();
                Log.e("reg button", "clicked");
            }
        });

        return view;
    }

    public void performRegistration() {
        String name = input_name.getText().toString();
        String user_name = input_username.getText().toString();
        String user_password = input_user_password.getText().toString();


        if (TextUtils.isEmpty(name)) {
            MainActivity.prefConfig.displayToast("Enter your name.");
        } else if (TextUtils.isEmpty(user_name)) {
            MainActivity.prefConfig.displayToast("Enter your Username");
        } else if (TextUtils.isEmpty(user_password)) {
            MainActivity.prefConfig.displayToast("Enter the password..");
        } else if (user_password.length() < 2) {
            MainActivity.prefConfig.displayToast("Create a strong Password.");
        } else {
            Call<User> userCall = MainActivity.apiInterface.performRegistration(name, user_name, user_password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body().getResponse().equals("inserted")) {
                        Log.e("response", response.body().getResponse());
                        input_username.setText("");
                        input_name.setText("");
                        input_user_password.setText("");
                        MainActivity.prefConfig.displayToast("Registered successfully..");
                    } else if (response.body().getResponse().equals("exist..")) {
                        MainActivity.prefConfig.displayToast("User already exists");
                    } else if (response.body().getResponse().equals("error")) {
                        MainActivity.prefConfig.displayToast("something went wrong..");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("onFailureRegCalled","onFailure",t);
                }
            });
        }


//        try {
//            Call<User> call = MainActivity.apiInterface.performRegistration(Name,UserName,UserPassword);
//            call.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    if (response.body().getResponse().equals("ok")){
//                        MainActivity.prefConfig.displayToast("Registration Success..");
//                    }
//                    else if (response.body().getResponse().equals("exist..")){
//                        MainActivity.prefConfig.displayToast("User already Exists..");
//                    }
//                    else if (response.body().getResponse().equals("error")){
//                        MainActivity.prefConfig.displayToast("User not found..");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Log.e("TAG1","failure");
//                }
//            });
//            Log.d("TAG","onResponse()");
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//
//        name.setText("");
//        Username.setText("");
//        Userpassword.setText("");

    }

}
