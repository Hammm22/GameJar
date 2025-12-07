package com.example.gamejar.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamejar.GameModel.GameModel;
import com.example.gamejar.R;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    Context context;
    List<GameModel> gameList;

    public GameAdapter(Context context, List<GameModel> gameList) {
        this.context = context;
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.game_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameModel game = gameList.get(position);

        holder.name.setText(game.GetNama());
        holder.price.setText(game.GetHarga());
        holder.icon.setImageResource(game.GetIcon()); // set icon
    }


    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;
        TextView name, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.gameIcon);
            name = itemView.findViewById(R.id.gameName);
            price = itemView.findViewById(R.id.gamePrice);
        }
    }
}
