package com.example.gamejar;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.gamejar.DataBase.DBManager;
import com.example.gamejar.DataBase.DataBaseHelper;

import android.content.Intent;
import android.widget.Button;

public class ProfileFragment extends Fragment {

    TextView tvName, tvPhone;
    DBManager dbManager;
    Button BtnLogout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvName = view.findViewById(R.id.ProfileNama);
        tvPhone = view.findViewById(R.id.ProfileNoTelp);
        BtnLogout = view.findViewById(R.id.TombolLogOut);

        dbManager = new DBManager(getActivity());

        SharedPreferences prefs = getActivity().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE);
        String phone = prefs.getString("phone", null);

        if(phone != null) {
            Cursor cursor = dbManager.getUser(phone);
            if (cursor != null && cursor.moveToFirst()) {

                String name = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.Col_Name));
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.Col_Phone ));

                tvName.setText(name);
                tvPhone.setText(phoneNumber);

                cursor.close();
            }
        }


        BtnLogout.setOnClickListener(v -> {

            SharedPreferences.Editor editor = getActivity().getSharedPreferences("USER_DATA", Context.MODE_PRIVATE).edit();
            editor.clear();
            editor.apply();


            Intent intent = new Intent(getActivity(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });

        return view;
    }
}
