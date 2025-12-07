package com.example.gamejar;

import android.content.Intent;
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

        // Bind view
        containerTop = findViewById(R.id.containerTop);
        containerBottom = findViewById(R.id.containerBottom);
        ticketStack = findViewById(R.id.ticketStack);
        loginButton = findViewById(R.id.loginButton);
        toSign = findViewById(R.id.toSign);
        etName = findViewById(R.id.loginName);
        etPassword = findViewById(R.id.loginPass);

        // Inisialisasi DBManager & TicketAnimator
        dbManager = new DBManager(this);
        ticketAnimator = new TicketAnimator();

        // Tombol navigasi ke SignUp
        toSign.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            Log.d("KeSign", "ke Sign Page");
        });

        // Tombol Login
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


            boolean success = dbManager.loginUser(nameOrPhone, password);
            if (success) {
                Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show();
                ticketAnimator.startTearAnimation(containerTop, containerBottom);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }

                }, 2000);
            } else {
                    Toast.makeText(this, "Username / Phone or Password is incorrect", Toast.LENGTH_SHORT).show();
                }

            Log.d("Login", "Login clicked");
        });
    }
}
