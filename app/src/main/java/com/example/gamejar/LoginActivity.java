package com.example.gamejar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.gamejar.DataBase.DBManager;
import android.os.Handler;
import android.os.Looper;

public class LoginActivity extends AppCompatActivity {

    private FrameLayout containerTop, containerBottom;
    private LinearLayout ticketStack;
    private Button loginButton, toSign;
    private EditText etName, etPassword;
    private TicketAnimator ticketAnimator;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        // View Binding
        containerTop = findViewById(R.id.containerTop);
        containerBottom = findViewById(R.id.containerBottom);
        ticketStack = findViewById(R.id.ticketStack);
        loginButton = findViewById(R.id.loginButton);
        toSign = findViewById(R.id.toSign);
        etName = findViewById(R.id.loginName);
        etPassword = findViewById(R.id.loginPass);

        // DB & Animator
        dbManager = new DBManager(this);
        ticketAnimator = new TicketAnimator();

        // ➤ Button to SignUp
        toSign.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            Log.d("KeSign", "ke Sign Page");
        });

        // ➤ Button Login
        loginButton.setOnClickListener(v -> {
            String nameOrPhone = etName.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            // ===== VALIDASI INPUT =====
            if (TextUtils.isEmpty(nameOrPhone)) {
                etName.setError("Username / Phone is required");
                etName.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Password is required");
                etPassword.requestFocus();
                return;
            }

            // ===== CHECK LOGIN =====
            boolean success = dbManager.loginUser(nameOrPhone, password);

            if (success) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();

                // ===== SIMPAN USER KE SHAREDPREFERENCES =====
                SharedPreferences prefs = getSharedPreferences("USER_DATA", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("phone", nameOrPhone);   // penting!
                editor.apply();
                // ============================================

                // ANIMASI TICKET
                ticketAnimator.startTearAnimation(containerTop, containerBottom);

                // DELAY 2 DETIK LALU MASUK MAIN PAGE
                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }, 1000);

            } else {
                Toast.makeText(this, "Username / Phone or Password is incorrect", Toast.LENGTH_SHORT).show();
            }

            Log.d("Login", "Login clicked");
        });
    }
}
