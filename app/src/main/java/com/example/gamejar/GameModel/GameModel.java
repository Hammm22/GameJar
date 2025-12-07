package com.example.gamejar.GameModel;

public class GameModel {

    String nama;
    String Harga;
    int Icon;

public GameModel(String nama, String Harga, int Icon){

    this.nama = nama;
    this.Harga = Harga;
    this.Icon = Icon;
}


public String GetNama(){
    return nama;
}

public String GetHarga(){
    return Harga;
}

public int GetIcon(){return Icon;}

}
