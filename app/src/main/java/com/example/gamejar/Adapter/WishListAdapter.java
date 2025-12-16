package com.example.gamejar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamejar.R;
import com.example.gamejar.GameModel.WishListModel;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    ArrayList<WishListModel> list;
    OnWishAction listener;

    public interface OnWishAction {
        void onEdit(WishListModel model, int position);
        void onDelete(WishListModel model, int position);
    }

    public WishListAdapter(ArrayList<WishListModel> list, OnWishAction listener){
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_wishlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WishListModel model = list.get(position);

        holder.txtNama.setText(model.getNama());
        holder.txtHarga.setText("Harga: Rp " + model.getHarga());
        holder.txtTabungan.setText("Tabungan/Hari: Rp " + model.getTabungan());
        holder.txtPlan.setText("Plan: " + model.getPlan());

        holder.btnEdit.setOnClickListener(v -> {
            listener.onEdit(model, position);
        });

        holder.btnHapus.setOnClickListener(v -> {
            listener.onDelete(model, position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtHarga, txtTabungan, txtPlan, txtHari;
        Button btnEdit, btnHapus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txtNama);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            txtTabungan = itemView.findViewById(R.id.txtTabungan);
            txtPlan = itemView.findViewById(R.id.txtPlan);
            txtHari = itemView.findViewById(R.id.txtHari);

            btnEdit = itemView.findViewById(R.id.EditButton);
            btnHapus = itemView.findViewById(R.id.HapusButton);
        }
    }
}
