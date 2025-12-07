package com.example.gamejar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamejar.Adapter.GameSearchAdapter;
import com.example.gamejar.GameModel.GameSearchModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText searchEditText;
    private RecyclerView recyclerView;
    private GameSearchAdapter adapter;
    private List<GameSearchModel> gameList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        searchEditText = view.findViewById(R.id.searchGame);
        recyclerView = view.findViewById(R.id.gameBySearch);

        // Load JSON
        gameList = loadGamesFromJSON();

        adapter = new GameSearchAdapter(gameList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        // Search realtime
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }
            @Override public void afterTextChanged(Editable s) { }
        });

        return view;
    }

    private List<GameSearchModel> loadGamesFromJSON() {
        List<GameSearchModel> list = new ArrayList<>();
        try {
            InputStream is = getContext().getAssets().open("GameData.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String jsonStr = new String(buffer, StandardCharsets.UTF_8);

            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                String name = obj.getString("Name");
                String link = obj.getString("Link");
                String harga = obj.getString("Harga");
                list.add(new GameSearchModel(name, link, harga));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
