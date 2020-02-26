package com.k.logininfo;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragment extends Fragment {

    private TextView registerTextView;
    OnLogInFormActivationListener logInFormActivationListener;

    private EditText input_username, input_user_password;
    private Button loginBtn;
    DBHelper sqliteHelper;

    public interface OnLogInFormActivationListener {
        public void performRegister();

        public void performLogIn(String user_name);
        void validate(String user_name);
    }

    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        registerTextView = view.findViewById(R.id.register_user);
        sqliteHelper = new DBHelper(getActivity());
        input_username = view.findViewById(R.id.input_username);
        input_user_password = view.findViewById(R.id.input_user_password);
        loginBtn = view.findViewById(R.id.login_btn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//              performLogin();
                if (validate()) {
                    String user_name = input_username.getText().toString();
                    String user_password = input_user_password.getText().toString();
                    User curruntUser = sqliteHelper.Authenticate(new User(null, user_name, null, user_password));

                    if (curruntUser != null) {
                        Snackbar.make(loginBtn, "Login Success!", Snackbar.LENGTH_LONG).show();
                        WelcomeFragment nextFrag = new WelcomeFragment();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer,nextFrag).addToBackStack(null).commit();
                    } else {
                        Snackbar.make(loginBtn, "Error Logging In", Snackbar.LENGTH_LONG).show();
                    }
                }
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

    public boolean validate() {
        boolean valid = false;

        String user_name = input_username.getText().toString();
        String user_password = input_user_password.getText().toString();

        if (TextUtils.isEmpty(user_name)) {
            Toast.makeText(getActivity(), "Enter Username..", Toast.LENGTH_LONG).show();
        } else {
            valid = true;
        }
        if (user_password.isEmpty()) {
            valid = false;
            Toast.makeText(getActivity(), "Enter Password", Toast.LENGTH_LONG).show();
        } else {
            if (user_password.length() > 4) {
                valid = true;
            } else {
                valid = false;
                Toast.makeText(getActivity(), "Enter Complete password...", Toast.LENGTH_LONG).show();
            }
        }

        return valid;
    }

//    private void performLogin() {
//        String user_name = input_username.getText().toString();
//        String user_password = input_user_password.getText().toString();


//        if (TextUtils.isEmpty(user_name)){
//            MainActivity.prefConfig.displayToast("Enter name");
//        }else if(user_password.length() < 2){
//            MainActivity.prefConfig.displayToast("Password is not sufficiently long..");
//        }else{
//            Call<User> userCall = MainActivity.apiInterface.performUserLogIn(user_name,user_password);
//            userCall.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    if (response.body().getResponse().equals("ok")){
//                        MainActivity.prefConfig.writeLogInStatus(true);
//                        logInFormActivationListener.performLogIn(
//                                response.body().getUserName());
//                    }else if (response.body().getResponse().equals("failed..")){
//                        MainActivity.prefConfig.displayToast("Error Logging In");
//                        input_username.setText("");
//                        input_user_password.setText("");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Log.e("onFailureCalled","fail to get response",t);
//                }
//            });
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logInFormActivationListener = (OnLogInFormActivationListener) activity;
    }

}
