package com.example.gamejar;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

public class SignUpActivity extends AppCompatActivity {

    private FrameLayout containerTop, containerBottom;
    private Button signupButton;
    private TIcketAnimator ticketAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page); // your sign-up XML layout

        containerTop = findViewById(R.id.containerTop);
        containerBottom = findViewById(R.id.containerBottom);
        signupButton = findViewById(R.id.signButton);

        ticketAnimator = new TIcketAnimator();

        signupButton.setOnClickListener(v ->
                ticketAnimator.startTearAnimation(containerTop, containerBottom)
        );
    }
}
