package com.orcunozsen.mylogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword, etConfirmPassword;
    private MaterialButton btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.et_name);
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirm_password);
        btnRegister = findViewById(R.id.btn_register);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }
        private void registerUser() {
            String name = etName.getText().toString();
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            if (password.equals(confirmPassword)) {
                SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name", name);
                editor.putString("email", email);
                editor.putString("password", password);
                editor.apply();

                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                etConfirmPassword.setError("Passwords do not match");
            }
        }


    private boolean isValidName(String name) {
        return !name.isEmpty();
    }

    private boolean isValidEmail(String email) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // E-posta biçimsel olarak geçerli değil, kullanıcıya örnek bir e-posta adresi göster
            etEmail.setError("Geçerli bir e-posta adresi girin (örneğin: example@example.com)");
            return false;
        }
        return true;
    }


    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }
}







