package library.model;
import java.util.ArrayList;

public class Member {
    private String id;
    private String nama;
    private ArrayList<Book> daftarPinjaman;
    private int totalAktivitas; // Untuk analisis anggota teraktif

    public Member(String id, String nama) {
        this.id = id;
        this.nama = nama;
        this.daftarPinjaman = new ArrayList<>();
        this.totalAktivitas = 0;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public ArrayList<Book> getDaftarPinjaman() { return daftarPinjaman; }
    public int getTotalAktivitas() { return totalAktivitas; }

    public void tambahPinjaman(Book book) {
        daftarPinjaman.add(book);
        totalAktivitas++;
    }

    public void hapusPinjaman(Book book) {
        daftarPinjaman.remove(book);
    }
}