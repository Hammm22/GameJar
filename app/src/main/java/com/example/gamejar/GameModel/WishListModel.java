package com.example.gamejar.GameModel;

public class WishListModel {

    private int id;
    private String nama;
    private String harga;
    private String tabungan;
    private String plan;

    public WishListModel(int id, String nama, String harga, String tabungan, String plan) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.tabungan = tabungan;
        this.plan = plan;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getHarga() {
        return harga;
    }

    public String getTabungan() {
        return tabungan;
    }

    public String getPlan() {
        return plan;
    }

    // ============================
    //   HITUNG BERAPA HARI
    // ============================
    public int getWaktuTercapai() {
        try {
            int hargaInt = Integer.parseInt(harga.replaceAll("[^0-9]", ""));
            int tabungInt = Integer.parseInt(tabungan.replaceAll("[^0-9]", ""));

            if (tabungInt <= 0) return -1;

            int hari = hargaInt / tabungInt;

            switch (plan) {
                case "Harian": return hari;
                case "Mingguan": return (int) Math.ceil(hari / 7.0);
                case "Bulanan": return (int) Math.ceil(hari / 30.0);
                default: return -1;
            }
        } catch (Exception e) {
            return -1;
        }
    }


}
