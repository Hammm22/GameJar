package com.example.gamejar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private FrameLayout containerTop, containerBottom;
    private LinearLayout ticketStack;
    private Button loginButton, toSign;
    private TicketAnimator ticketAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        containerTop = findViewById(R.id.containerTop);
        containerBottom = findViewById(R.id.containerBottom);
        ticketStack = findViewById(R.id.ticketStack);
        loginButton = findViewById(R.id.loginButton);
        toSign = findViewById(R.id.toSign);


        toSign.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
         startActivity(intent);

            Log.d("KeSign", "ke Sign Page ");
        });

        ticketAnimator = new TicketAnimator();

        loginButton.setOnClickListener(v -> {
            ticketAnimator.startTearAnimation(containerTop, containerBottom);
            Log.d("Login", "Tombol Login ");
        });



    }
}
