package com.example.gamejar;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gamejar.databinding.DashboardBinding;

public class MainActivity extends AppCompatActivity {

    private DashboardBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        bind = DashboardBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        // Set bottom app bar as toolbar
        setSupportActionBar(bind.bottomAppBar);

        // Default fragment
        loadFragment(new HomeFragment());

        // Bottom navigation listener
        bind.bottomNavbar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.Home) {
                loadFragment(new HomeFragment());
            }
            else if (id == R.id.Search) {
                loadFragment(new SearchFragment());
            }  else if (id == R.id.Progress) {
                loadFragment(new ProgressFragment());
            }
            else if (id == R.id.Profile) {
                loadFragment(new ProfileFragment());
            }

            return true;
        });


        // FAB action
        bind.fabAdd.setOnClickListener(v -> {
            loadFragment(new AddFragment());
        });

        // Realtime blur (Android 12+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            View blurView = bind.blurView;
            blurView.setRenderEffect(RenderEffect.createBlurEffect(
                    40f, 40f, Shader.TileMode.CLAMP
            ));
        }
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}
