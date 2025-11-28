package com.example.gamejar;

import static com.example.gamejar.R.layout.login_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;

public class SignUpActivity extends AppCompatActivity {

    private FrameLayout containerTop, containerBottom;
    private Button signupButton, toLogin;
    private TicketAnimator ticketAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page); // your sign-up XML layout

        containerTop = findViewById(R.id.containerTop);
        containerBottom = findViewById(R.id.containerBottom);
        signupButton = findViewById(R.id.signButton);
        toLogin = findViewById(R.id.toLogin);

        toLogin.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            Log.d("KeLogin", "ke Login Page ");
        });

        ticketAnimator = new TicketAnimator();

        signupButton.setOnClickListener(v -> {
            ticketAnimator.startTearAnimation(containerTop, containerBottom);
            Log.d("Sign", "SignUp ");
        });
    }
}
