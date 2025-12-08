package com.example.gamejar.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamejar.R;
import com.example.gamejar.GameModel.WishListModel;

import java.util.ArrayList;

public class WishListAdapter extends RecyclerView.Adapter<WishListAdapter.ViewHolder> {

    ArrayList<WishListModel> list;

    public WishListAdapter(ArrayList<WishListModel> list){
        this.list = list;
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

        // =============== HITUNG BERAPA HARI ===============
        int waktu = model.getWaktuTercapai();

        if (waktu == -1) {
            holder.txtHari.setText("Target tidak valid");
        } else {
            switch (model.getPlan()) {
                case "Harian":
                    holder.txtHari.setText("Butuh: " + waktu + " hari");
                    break;
                case "Mingguan":
                    holder.txtHari.setText("Butuh: " + waktu + " minggu");
                    break;
                case "Bulanan":
                    holder.txtHari.setText("Butuh: " + waktu + " bulan");
                    break;
            }
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNama, txtHarga, txtTabungan, txtPlan, txtHari;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            txtTabungan = itemView.findViewById(R.id.txtTabungan);
            txtPlan = itemView.findViewById(R.id.txtPlan);
            txtHari = itemView.findViewById(R.id.txtHari); // <-- Added
        }
    }
}
