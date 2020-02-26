package com.k.logininfo;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFragment extends Fragment {

    private EditText input_name, input_username, input_user_password;
    private Button btnRegister;
    DBHelper sqliteHelper;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        sqliteHelper = new DBHelper(getActivity());
        input_name = view.findViewById(R.id.name);
        input_username = view.findViewById(R.id.input_username);
        input_user_password = view.findViewById(R.id.input_user_password);
        btnRegister = view.findViewById(R.id.register_btn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                performRegistration();
                Log.e("reg button", "clicked");
                if (validate()){
                    String name = input_name.getText().toString();
                    String user_name = input_username.getText().toString();
                    String user_password = input_user_password.getText().toString();

                    if (!sqliteHelper.isUserNameExists(user_name)) {
                        sqliteHelper.addUser(new User(null, user_name, name, user_password));
                        Snackbar.make(btnRegister, "User created successfully! Please Login", Snackbar.LENGTH_LONG).show();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                finish();
//                            }
//                        }
//                        , Snackbar.LENGTH_LONG);
                    } else {
                        Snackbar.make(btnRegister, "User already Exists..", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view;
    }

    public boolean validate() {
        boolean valid = false;
        String Name = input_name.getText().toString();
        String Username = input_username.getText().toString();
        String Password = input_user_password.getText().toString();
        if (Name.isEmpty()) {
            valid = false;
            Toast.makeText(getActivity(),"Name can't empty",Toast.LENGTH_SHORT);
        } else {
            valid = true;
        }
        if (Username.isEmpty()) {
            valid = false;
            Toast.makeText(getActivity(),"UserName can't empty",Toast.LENGTH_SHORT);
        } else {
            valid = true;
        }
        if (Password.isEmpty()) {
            valid = false;
            Toast.makeText(getActivity(),"Password can't empty",Toast.LENGTH_SHORT);
        } else {
            if (Password.length() > 4) {
                valid = true;
            } else {
                valid = false;
                Toast.makeText(getActivity(),"Password is not sufficiently long..",Toast.LENGTH_SHORT);
            }
        }
        return valid;

    }


//    public void performRegistration() {
//        String name = input_name.getText().toString();
//        String user_name = input_username.getText().toString();
//        String user_password = input_user_password.getText().toString();

//        if (TextUtils.isEmpty(name)) {
//            MainActivity.prefConfig.displayToast("Enter your name.");
//        } else if (TextUtils.isEmpty(user_name)) {
//            MainActivity.prefConfig.displayToast("Enter your Username");
//        } else if (TextUtils.isEmpty(user_password)) {
//            MainActivity.prefConfig.displayToast("Enter the password..");
//        } else if (user_password.length() < 2) {
//            MainActivity.prefConfig.displayToast("Create a strong Password.");
//        } else {
//            Call<User> userCall = MainActivity.apiInterface.performRegistration(name, user_name, user_password);
//            userCall.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    if (response.body().getResponse().equals("inserted")) {
//                        Log.e("response", response.body().getResponse());
//                        input_username.setText("");
//                        input_name.setText("");
//                        input_user_password.setText("");
//                        MainActivity.prefConfig.displayToast("Registered successfully..");
//                    } else if (response.body().getResponse().equals("exist..")) {
//                        MainActivity.prefConfig.displayToast("User already exists");
//                    } else if (response.body().getResponse().equals("error")) {
//                        MainActivity.prefConfig.displayToast("something went wrong..");
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Log.e("onFailureRegCalled","onFailure",t);
//                }
//            });
//        }

    }


