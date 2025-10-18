package com.example.gamejar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

public class TIcketAnimator {
    private boolean isTorn = false;

    public void startTearAnimation(View topPart, View bottomPart) {
        if (isTorn) return; // prevent re-trigger
        isTorn = true;

        // 🎬 Move top up
        Animation moveUp = new TranslateAnimation(0, 0, 0, -500);
        moveUp.setDuration(700);
        moveUp.setFillAfter(true);

        // 🎬 Move bottom down
        Animation moveDown = new TranslateAnimation(0, 0, 0, 500);
        moveDown.setDuration(700);
        moveDown.setFillAfter(true);

        topPart.startAnimation(moveUp);
        bottomPart.startAnimation(moveDown);
    }
}
