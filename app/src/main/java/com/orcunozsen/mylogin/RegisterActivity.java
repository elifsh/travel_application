package com.orcunozsen.mylogin;

import android.os.Bundle;
import android.util.Patterns;
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

        btnRegister.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            if (isValidName(name) && isValidEmail(email) && isValidPassword(password)) {
                if (password.equals(confirmPassword)) {
                    // Kayıt işlemi burada yapılacak
                    Toast.makeText(RegisterActivity.this, "Kayıt başarılı", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Şifreler uyuşmuyor", Toast.LENGTH_SHORT).show();
                }
            } else {
                if (name.isEmpty()) {
                    etName.setError("Ad ve soyad girin");
                }
                if (!isValidEmail(email)) {
                    etEmail.setError("Geçerli bir e-posta adresi girin (örneğin: example@example.com)");
                }
                if (!isValidPassword(password)) {
                    etPassword.setError("Güvenli bir şifre girin (en az 8 karakter)");
                }
            }
        });
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







