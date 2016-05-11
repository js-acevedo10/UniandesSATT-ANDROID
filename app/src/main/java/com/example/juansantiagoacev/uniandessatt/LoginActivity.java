package com.example.juansantiagoacev.uniandessatt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText login_input_email, login_input_password;
    private Button login_button_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        prepareLayout();
    }

    public void prepareLayout() {
        login_input_email = (EditText) findViewById(R.id.login_input_email);
        login_input_password = (EditText) findViewById(R.id.login_input_password);
        login_button_login = (Button) findViewById(R.id.login_button_login);
        login_button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CLIC", "BOTON");
                submitForm();
            }
        });

    }

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty() || email.equals("") || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            login_input_email.setError(getString(R.string.login_err_email));
            requestFocus(login_input_email);
            return false;
        }
        return true;
    }

    public boolean validatePassword(String password) {
        if(password == null || password.isEmpty() || password.equals("")) {
            login_input_password.setError(getString(R.string.login_err_password));
            requestFocus(login_input_password);
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void submitForm() {

        String email = login_input_email.getText().toString().trim();
        String password = login_input_password.getText().toString();
        if(validateEmail(email) && validatePassword(password)) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://uniandes-sattjs.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            APIService service = retrofit.create(APIService.class);

            HashMap<String, String> map = new HashMap();
            map.put("email", email);
            map.put("password", password);
            Call<String> loginCall = service.login(map);
            loginCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccess()) {

                    } else {
                        Log.d("ERROR", response.code() + ": " + response.message());
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("ERROR", t.toString());
                }
            });
        }
    }
}
