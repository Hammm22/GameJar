package com.example.gamejar;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamejar.Adapter.WishListAdapter;
import com.example.gamejar.DataBase.DBManager;
import com.example.gamejar.DataBase.DataBaseHelper;
import com.example.gamejar.GameModel.WishListModel;
import com.example.gamejar.R;

import java.util.ArrayList;

public class ProgressFragment extends Fragment {

    RecyclerView rvWishlist;
    ArrayList<WishListModel> list;
    WishListAdapter adapter;
    DBManager dbManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_progress, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvWishlist = view.findViewById(R.id.rvWishlist);
        rvWishlist.setLayoutManager(new LinearLayoutManager(getActivity()));

        dbManager = new DBManager(getActivity());
        list = new ArrayList<>();

        loadWishlist();
    }

    private void loadWishlist() {
        Cursor cursor = dbManager.getWishlist("default");
        list.clear();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelper.Col_Wish_ID));
                String game = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.Col_Game_Name));
                String harga = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.Col_Game_Price));
                String tabung = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.Col_Saving_Per_Day));
                String plan = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.Col_Saving_Plan));

                list.add(new WishListModel(id, game, harga, tabung, plan));
            } while (cursor.moveToNext());
            cursor.close();
        }

        adapter = new WishListAdapter(list, new WishListAdapter.OnWishAction() {
            @Override
            public void onEdit(WishListModel model, int position) {
                // Buat Bundle untuk bawa data ke EditFragment
                Bundle bundle = new Bundle();
                bundle.putInt("id", model.getId());
                bundle.putString("nama", model.getNama());
                bundle.putString("harga", model.getHarga());
                bundle.putString("tabungan", model.getTabungan());
                bundle.putString("plan", model.getPlan());

                // Buat instance EditFragment dan set arguments
                EditFragment editFragment = new EditFragment();
                editFragment.setArguments(bundle);

                // Ganti fragment
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentContainer, editFragment) // ganti fragment_container sesuai layout
                        .addToBackStack(null)
                        .commit();
            }

            @Override
            public void onDelete(WishListModel model, int position) {
                dbManager.deleteWish(model.getId()); // Hapus dari database
                list.remove(position);               // Hapus dari array
                adapter.notifyItemRemoved(position); // Update UI
            }
        });


        rvWishlist.setAdapter(adapter);
    }
}
