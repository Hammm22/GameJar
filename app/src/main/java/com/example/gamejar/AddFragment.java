package com.example.gamejar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gamejar.DataBase.DBManager;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class AddFragment extends Fragment {

    EditText etNama, etHarga, etTabung;
    MaterialButtonToggleGroup toggleGroupPlan;
    Button btnTambah;
    DBManager dbManager;

    public AddFragment() {
        super(R.layout.fragment_add);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNama = view.findViewById(R.id.TambahNama);
        etHarga = view.findViewById(R.id.TambahHarga);
        etTabung = view.findViewById(R.id.TambahTabung);
        toggleGroupPlan = view.findViewById(R.id.toggleGroupPlan);
        btnTambah = view.findViewById(R.id.Tambah);

        dbManager = new DBManager(getActivity());

        btnTambah.setOnClickListener(v -> {
            String nama = etNama.getText().toString().trim();
            String harga = etHarga.getText().toString().trim();
            String tabungan = etTabung.getText().toString().trim();

            // Validasi form
            if(TextUtils.isEmpty(nama) || TextUtils.isEmpty(harga) || TextUtils.isEmpty(tabungan)){
                Toast.makeText(getActivity(), "Isi semua field!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ambil plan
            int selectedId = toggleGroupPlan.getCheckedButtonId();
            String plan = "";

            if(selectedId == R.id.btnHarian) plan = "Harian";
            else if(selectedId == R.id.btnMingguan) plan = "Mingguan";
            else if(selectedId == R.id.btnBulanan) plan = "Bulanan";

            if(plan.isEmpty()){
                Toast.makeText(getActivity(), "Pilih rencana nabung!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Simpan wishlist (userPhone = default)
            boolean inserted = dbManager.addWishlist("default", nama, harga, tabungan, plan);

            if(inserted){
                Toast.makeText(getActivity(), "Wishlist berhasil ditambahkan!", Toast.LENGTH_SHORT).show();

                // Reset form
                etNama.setText("");
                etHarga.setText("");
                etTabung.setText("");
                toggleGroupPlan.clearChecked();

            } else {
                Toast.makeText(getActivity(), "Gagal menambahkan wishlist!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
