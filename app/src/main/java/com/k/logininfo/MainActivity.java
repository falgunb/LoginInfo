package com.k.logininfo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLogInFormActivationListener {


//    public static PrefConfig prefConfig;
//    public static ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        prefConfig = new PrefConfig(this);
//        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        if (findViewById(R.id.fragmentContainer) != null) {
            if (savedInstanceState != null) {
                return;
            }
//            if (prefConfig.readLogInStatus()) {
//                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new WelcomeFragment()).commit();
//            }
            else {
                getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer, new LoginFragment()).commit();
            }
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
