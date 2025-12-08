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
            int hargaInt = Integer.parseInt(harga);
            int tabungInt = Integer.parseInt(tabungan);

            if (tabungInt <= 0) return -1;

            // Jika Harian → hitung hari
            if (plan.equalsIgnoreCase("Harian")) {
                return (int) Math.ceil((double) hargaInt / tabungInt);
            }

            // Jika Mingguan → hitung minggu
            if (plan.equalsIgnoreCase("Mingguan")) {
                return (int) Math.ceil((double) hargaInt / tabungInt);
            }

            // Jika Bulanan → hitung bulan
            if (plan.equalsIgnoreCase("Bulanan")) {
                return (int) Math.ceil((double) hargaInt / tabungInt);
            }

            return -1;

        } catch (Exception e) {
            return -1;
        }
    }

}
