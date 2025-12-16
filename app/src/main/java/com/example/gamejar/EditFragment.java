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
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;

public class EditFragment extends Fragment {

    EditText etNama, etHarga, etTabung;
    MaterialButtonToggleGroup toggleGroupPlan;
    Button btnUbah;
    DBManager dbManager;

    private int wishId; // id wishlist yang akan diedit
    private String nama, harga, tabungan, plan;

    public EditFragment() {
        super(R.layout.fragment_edit); // pastikan layout XML ini

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNama = view.findViewById(R.id.TambahNama);
        etHarga = view.findViewById(R.id.TambahHarga);
        etTabung = view.findViewById(R.id.TambahTabung);
        toggleGroupPlan = view.findViewById(R.id.toggleGroupPlan);
        btnUbah = view.findViewById(R.id.Ubah);

        dbManager = new DBManager(getActivity());

        // isi data lama
        etNama.setText(nama);
        etHarga.setText(harga);
        etTabung.setText(tabungan);

        switch (plan) {
            case "Harian": toggleGroupPlan.check(R.id.btnHarian); break;
            case "Mingguan": toggleGroupPlan.check(R.id.btnMingguan); break;
            case "Bulanan": toggleGroupPlan.check(R.id.btnBulanan); break;
        }

        btnUbah.setOnClickListener(v -> {
            String newNama = etNama.getText().toString().trim();
            String newHarga = etHarga.getText().toString().trim();
            String newTabung = etTabung.getText().toString().trim();

            if (TextUtils.isEmpty(newNama) || TextUtils.isEmpty(newHarga) || TextUtils.isEmpty(newTabung)) {
                Toast.makeText(getActivity(), "Isi semua field!", Toast.LENGTH_SHORT).show();
                return;
            }

            int selectedId = toggleGroupPlan.getCheckedButtonId();
            String newPlan = "";
            if(selectedId == R.id.btnHarian) newPlan = "Harian";
            else if(selectedId == R.id.btnMingguan) newPlan = "Mingguan";
            else if(selectedId == R.id.btnBulanan) newPlan = "Bulanan";

            if(newPlan.isEmpty()) {
                Toast.makeText(getActivity(), "Pilih rencana nabung!", Toast.LENGTH_SHORT).show();
                return;
            }

            // update di database
            boolean updated = dbManager.updateWishlist(wishId, newNama, newHarga, newTabung, newPlan);

            if(updated) {
                Toast.makeText(getActivity(), "Wishlist berhasil diubah!", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack(); // kembali ke ProgressFragment
            } else {
                Toast.makeText(getActivity(), "Gagal mengubah wishlist!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
