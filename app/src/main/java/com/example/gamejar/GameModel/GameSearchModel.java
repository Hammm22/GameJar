package com.example.gamejar.GameModel;

public class GameSearchModel {
    private final String Name;
    private final String Link;
    private final String Harga;

    public GameSearchModel(String Nama, String Link, String Harga){
        this.Name = Nama;
        this.Link = Link;
        this.Harga = Harga;
    }

    public String GetNama(){ return Name; }
    public String GetLink(){ return Link; }
    public String getHarga(){ return Harga; }
}
