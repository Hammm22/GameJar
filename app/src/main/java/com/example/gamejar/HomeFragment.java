package com.example.gamejar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamejar.Adapter.GameAdapter;
import com.example.gamejar.GameModel.GameModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    GameAdapter adapter;
    ImageView thumbnail;
    ArrayList<GameModel> gameList;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        thumbnail = view.findViewById(R.id.thumbnailAnnounce);
         thumbnail.setImageResource(R.drawable.thumbnail_announcement);

        recyclerView = view.findViewById(R.id.listGame);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);

        gameList = new ArrayList<>();


        gameList.add(new GameModel("NFS Heat", "Rp 750.000", R.drawable.icon_nfs_heat));
        gameList.add(new GameModel("GTA V", "Rp 155.000", R.drawable.icon_gtav));
        gameList.add(new GameModel("Minecraft", "Rp 429.000", R.drawable.icon_minecraft));
        gameList.add(new GameModel("Valorant", "Rp 0", R.drawable.icon_valorant));
        gameList.add(new GameModel("Among Us", "Rp 20.000", R.drawable.icon_amongus));
        gameList.add(new GameModel("CS:GO", "Free", R.drawable.icon_csgo));
        adapter = new GameAdapter(getContext(), gameList);
        recyclerView.setAdapter(adapter);
    }
}
