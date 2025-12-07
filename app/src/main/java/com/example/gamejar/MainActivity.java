package com.example.gamejar;

import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.gamejar.databinding.DashboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private DashboardBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate layout dengan ViewBinding
        bind = DashboardBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        // Default fragment
        loadFragment(new HomeFragment());

        // Bottom navigation listener
        bind.bottomNavbar.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.Home) {
                loadFragment(new HomeFragment());
            } else if (id == R.id.Search) {
                loadFragment(new SearchFragment());
            } else if(id == R.id.Add){
                loadFragment(new AddFragment());
            }else if (id == R.id.Progress) {
                loadFragment(new ProgressFragment());
            } else if (id == R.id.Profile) {
                loadFragment(new ProfileFragment());
            }

            return true;
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }
}
