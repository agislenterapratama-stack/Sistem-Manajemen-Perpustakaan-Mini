package library.model;
public class Book {
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private String kategori;
    private boolean statusKetersediaan;
    private int totalDipinjam; // Untuk analisis

    public Book(String judul, String penulis, int tahunTerbit, String kategori) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.kategori = kategori;
        this.statusKetersediaan = true;
        this.totalDipinjam = 0;
    }

    public String getJudul() { return judul; }
    public String getKategori() { return kategori; }
    public boolean isStatusKetersediaan() { return statusKetersediaan; }
    public int getTotalDipinjam() { return totalDipinjam; }

    public void setStatusKetersediaan(boolean statusKetersediaan) {
        this.statusKetersediaan = statusKetersediaan;
    }

    public void incrementPinjam() {
        this.totalDipinjam++;
    }

    @Override
    public String toString() {
        return judul + " | " + penulis + " (" + tahunTerbit + ") - " + kategori
                + " | Status: " + (statusKetersediaan ? "Tersedia" : "Dipinjam");
    }
}