package com.k.logininfo.Fragments;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.k.logininfo.MainActivity;
import com.k.logininfo.Model.User;
import com.k.logininfo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {

    private TextView loggedInUser;
    private Button btnLogout;
    OnLogoutInterface onLogoutInterface;


    public interface OnLogoutInterface {
        void performLogout();
    }

    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);


        loggedInUser = view.findViewById(R.id.name);
        String Name = "Hi, " + MainActivity.prefConfig.readName();
        loggedInUser.setText(Name);
        loggedInUser.setText("Welcome " + MainActivity.prefConfig.readName());
        btnLogout = view.findViewById(R.id.logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.prefConfig.removeUser();
                LoginFragment loginFragment = new LoginFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer,loginFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
//        onLogoutInterface = (OnLogoutInterface) activity;

    }
}
