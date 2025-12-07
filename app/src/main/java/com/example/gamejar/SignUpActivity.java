package com.example.gamejar;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.gamejar.DataBase.DBManager;

public class SignUpActivity extends AppCompatActivity {

    private FrameLayout containerTop, containerBottom;
    private Button signupButton, toLogin;
    private EditText etName, etPhone, etPassword;
    private CheckBox checkBox;
    private TicketAnimator ticketAnimator;
    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        // Bind view
        containerTop = findViewById(R.id.containerTop);
        containerBottom = findViewById(R.id.containerBottom);
        signupButton = findViewById(R.id.signButton);
        toLogin = findViewById(R.id.toLogin);
        etName = findViewById(R.id.signupName);
        etPhone = findViewById(R.id.signupPhone);
        etPassword = findViewById(R.id.signupPass);
        checkBox = findViewById(R.id.Check); // tambahkan id di XML jika belum ada

        dbManager = new DBManager(this);
        ticketAnimator = new TicketAnimator();


        toLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            Log.d("KeLogin", "ke Login Page");
        });


        signupButton.setOnClickListener(v -> {

            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!checkBox.isChecked()) {
                Toast.makeText(this, "You must agree to Terms & Conditions", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = dbManager.registerUser(name, phone, password);
            if (inserted) {
                Toast.makeText(this, "SignUp Successful!", Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Phone number already exists", Toast.LENGTH_SHORT).show();
            }

            Log.d("Sign", "SignUp clicked");
        });
    }
}
