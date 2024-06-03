package com.orcunozsen.mylogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.orcunozsen.mylogin.R;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    private MaterialButton btnRegister, btnLogin;
    private EditText etEmail, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnRegister = findViewById(R.id.btn_kayit);
        btnLogin = findViewById(R.id.btn_login);

        btnRegister = findViewById(R.id.btn_kayit);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });


        // Google Sign-In seçeneklerini yapılandırın
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        // GoogleSignInClient nesnesini oluşturun
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Google butonuna tıklama olayını ekleyin
        findViewById(R.id.google_sign_in_button).setOnClickListener(view -> signIn());
    }

    private void loginUser() {
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        SharedPreferences preferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String registeredEmail = preferences.getString("email", null);
        String registeredPassword = preferences.getString("password", null);

        if (email.equals(registeredEmail) && password.equals(registeredPassword)) {
            Intent intent = new Intent(MainActivity.this, TouristPlacesActivity.class);
            startActivity(intent);
            finish();
        } else {
            etPassword.setError("Invalid email or password");
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Google Sign-In'den dönen sonucu işleyin
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                // Google Sign-In başarılı
                // Burada gerekli işlemleri yapabilirsiniz
            } catch (ApiException e) {
                // Google Sign-In başarısız
                // Hata yönetimi yapabilirsiniz
            }
        }
    }
}