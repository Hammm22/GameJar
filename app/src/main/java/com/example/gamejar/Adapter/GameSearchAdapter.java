package com.example.gamejar.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamejar.GameModel.GameSearchModel;
import com.example.gamejar.R;

import java.util.ArrayList;
import java.util.List;

public class GameSearchAdapter extends RecyclerView.Adapter<GameSearchAdapter.ViewHolder> {

    private final List<GameSearchModel> gameList;
    private final List<GameSearchModel> gameListFull;

    public GameSearchAdapter(List<GameSearchModel> gameList) {
        this.gameList = gameList;
        this.gameListFull = new ArrayList<>(gameList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameSearchModel game = gameList.get(position);
        holder.name.setText(game.GetNama());
        holder.price.setText(game.getHarga());
        holder.link.setText(game.GetLink());

        // Klik item untuk buka Steam link
        holder.itemView.setOnClickListener(v -> {
            String url = game.GetLink();
            if (!url.isEmpty()) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.searchGameNama);
            price = itemView.findViewById(R.id.searchGameHarga);
            link = itemView.findViewById(R.id.searchGameLink);
        }
    }

    // Filter method
    public void filter(String text) {
        gameList.clear();
        if (text.isEmpty()) {
            gameList.addAll(gameListFull);
        } else {
            text = text.toLowerCase();
            for (GameSearchModel game : gameListFull) {
                if (game.GetNama().toLowerCase().contains(text)) {
                    gameList.add(game);
                }
            }
        }
        notifyDataSetChanged();
    }
}
