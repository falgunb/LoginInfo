package com.k.logininfo;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private TextView registerTextView;
    OnLogInFormActivationListener logInFormActivationListener;

    private EditText input_username, input_user_password;
    private Button loginBtn;

    public interface OnLogInFormActivationListener {
        public void performRegister();

        public void performLogIn(String user_name);
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        registerTextView = view.findViewById(R.id.register_user);

        input_username = view.findViewById(R.id.input_username);
        input_user_password = view.findViewById(R.id.input_user_password);
        loginBtn = view.findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });

        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logInFormActivationListener.performRegister();
            }
        });
        return view;
    }

    private void performLogin() {
        String user_name = input_username.getText().toString();
        String user_password = input_user_password.getText().toString();

        if (TextUtils.isEmpty(user_name)){
            MainActivity.prefConfig.displayToast("Enter name");
        }else if(user_password.length() < 2){
            MainActivity.prefConfig.displayToast("Password is not sufficiently long..");
        }else{
            Call<User> userCall = MainActivity.apiInterface.performUserLogIn(user_name,user_password);
            userCall.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body().getResponse().equals("ok")){
                        MainActivity.prefConfig.writeLogInStatus(true);
                        logInFormActivationListener.performLogIn(
                                response.body().getUserName());
                    }else if (response.body().getResponse().equals("failed..")){
                        MainActivity.prefConfig.displayToast("Error Logging In");
                        input_username.setText("");
                        input_user_password.setText("");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.e("onFailureCalled","fail to get response",t);
                }
            });
        }



//        Call<User> call = MainActivity.apiInterface.performUserLogIn(username, userpassword);
//        try {
//            call.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//
//                    if (response.body().getResponse().equals("ok")) {
//
//                        MainActivity.prefConfig.writeLogInStatus(true);
//                        logInFormActivationListener.performLogIn(response.body().getUserName());
//                    } else if (response.body().getResponse().equals("failed..")) {
//                        MainActivity.prefConfig.displayToast("Login Failed..");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Log.e("TAG2", "failure");
//                }
//            });
//            Log.d("TAG", "onLoginResponse()");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        User_Name.setText("");
//        User_Password.setText("");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logInFormActivationListener = (OnLogInFormActivationListener) activity;
    }

}
