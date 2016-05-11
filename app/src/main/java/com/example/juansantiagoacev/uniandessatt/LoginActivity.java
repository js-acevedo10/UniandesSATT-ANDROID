package com.example.juansantiagoacev.uniandessatt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            Toast.makeText(this, "Bien", Toast.LENGTH_LONG).show();
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.login_input_email:
                    validateEmail(login_input_email.getText().toString().trim());
                    break;
                case R.id.login_input_password:
                    validatePassword(login_input_password.getText().toString());
                    break;
                default:
                    break;
            }
        }
    }
}
