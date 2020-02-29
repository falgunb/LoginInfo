package com.k.logininfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.k.logininfo.Fragments.LoginFragment;
import com.k.logininfo.Fragments.RegistrationFragment;
import com.k.logininfo.Fragments.WelcomeFragment;
import com.k.logininfo.Model.User;
import com.k.logininfo.Utils.PrefConfig;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLogInFormActivationListener {


    public static PrefConfig prefConfig;
    User user;
//    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefConfig = new PrefConfig(this);
//        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragmentContainer) != null) {
                String readname = prefConfig.readName();
            if (readname == null){

                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,new LoginFragment()).commit();
            } else {
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new WelcomeFragment()).commit();
            }

//            if (savedInstanceState != null) {
//                return;
//            }
//            if (prefConfig.readLogInStatus()) {
//                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new WelcomeFragment()).commit();
//            }
//            else {
//                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new LoginFragment()).commit();
//            }
        }
    }

    @Override
    public void performRegister() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                new RegistrationFragment()).addToBackStack(null).commit();
    }

    @Override
    public void performLogIn(String user_name) {

//        prefConfig.writeName(user_name);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WelcomeFragment()).commit();

    }

    @Override
    public void validate(String user_name) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new WelcomeFragment()).commit();
    }

//    @Override
//    public void performLogout() {
//        prefConfig.writeLogInStatus(false);
//        prefConfig.writeName("Name ");
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fragmentContainer, new LoginFragment())
//                .commit();
//    }
}
