package library.service;

import library.exception.*;
import library.model.Book;
import library.model.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LibraryService {
    private ArrayList<Book> books;
    private HashMap<String, Member> members;
    private int totalPeminjamanSistem;

    public LibraryService() {
        books = new ArrayList<>();
        members = new HashMap<>();
        totalPeminjamanSistem = 0;
    }

    // Manipulasi Character (1): Memastikan nama anggota berawalan huruf kapital
    public void registerMember(String id, String nama) {
        if (nama != null && !nama.isEmpty()) {
            char firstChar = Character.toUpperCase(nama.charAt(0));
            nama = firstChar + nama.substring(1).toLowerCase();
        }
        members.put(id, new Member(id, nama));
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void listBooks() {
        System.out.println("\n--- Daftar Semua Buku ---");
        for (Book b : books) {
            System.out.println(b.toString());
        }
    }

    // Manipulasi String (2): toLowerCase dan contains
    public void searchBook(String keyword) {
        System.out.println("\n--- Hasil Pencarian untuk: '" + keyword + "' ---");
        keyword = keyword.toLowerCase();
        boolean found = false;
        
        for (Book b : books) {
            if (b.getJudul().toLowerCase().contains(keyword) || b.getKategori().toLowerCase().contains(keyword)) {
                System.out.println(b.toString());
                found = true;
            }
        }
        if (!found) System.out.println("Buku tidak ditemukan.");
    }

    public void borrowBook(String memberId, String judulBuku)
            throws BookNotFoundException, BookUnavailableException, BorrowLimitExceededException {
        Member member = members.get(memberId);
        
        // Assertion: Memastikan member valid dan ditemukan sebelum melanjutkan
        assert member != null : "FATAL ERROR: Data anggota null, registrasi bermasalah!";
        if (member == null) {
            throw new IllegalArgumentException("Member ID tidak terdaftar.");
        }

        if (member.getDaftarPinjaman().size() >= 3) {
            throw new BorrowLimitExceededException("Anggota "
                    + member.getNama() + " sudah mencapai batas maksimal 3 peminjaman.");
        }

        Book targetBook = null;
        for (Book b : books) {
            if (b.getJudul().equalsIgnoreCase(judulBuku)) {
                targetBook = b;
                break;
            }
        }

        if (targetBook == null) {
            throw new BookNotFoundException("Buku dengan judul '" + judulBuku + "' tidak ada di sistem.");
        }
        if (!targetBook.isStatusKetersediaan()) {
            throw new BookUnavailableException("Buku '" + judulBuku + "' sedang dipinjam orang lain.");
        }

        // Proses pinjam
        targetBook.setStatusKetersediaan(false);
        targetBook.incrementPinjam();
        member.tambahPinjaman(targetBook);
        totalPeminjamanSistem++;
        System.out.println("Berhasil: " + member.getNama() + " meminjam " + targetBook.getJudul());
    }

    public void returnBook(String memberId, String judulBuku) throws BookNotFoundException {
        Member member = members.get(memberId);
        if (member == null) return;

        Book targetBook = null;
        for (Book b : member.getDaftarPinjaman()) {
            if (b.getJudul().equalsIgnoreCase(judulBuku)) {
                targetBook = b;
                break;
            }
        }

        if (targetBook == null) {
            throw new BookNotFoundException("Member ini tidak meminjam buku berjudul '" + judulBuku + "'.");
        }

        targetBook.setStatusKetersediaan(true);
        member.hapusPinjaman(targetBook);
        System.out.println("Berhasil dikembalikan: " + targetBook.getJudul());
    }

    // Analisis Aktivitas
    public void generateReport() {
        System.out.println("\n===== LAPORAN ANALISIS PERPUSTAKAAN =====");
        System.out.println("Total transaksi peminjaman: " + totalPeminjamanSistem);

        // Cari buku paling sering dipinjam & hitung kategori
        Book mostBorrowed = null;
        HashMap<String, Integer> categoryCount = new HashMap<>();
        
        for (Book b : books) {
            if (mostBorrowed == null || b.getTotalDipinjam() > mostBorrowed.getTotalDipinjam()) {
                mostBorrowed = b;
            }
            categoryCount.put(b.getKategori(), categoryCount.getOrDefault(b.getKategori(), 0) + 1);
        }

        if (mostBorrowed != null && mostBorrowed.getTotalDipinjam() > 0) {
            System.out.println("Buku paling sering dipinjam: "
                    + mostBorrowed.getJudul() + " (" + mostBorrowed.getTotalDipinjam() + " kali)");
        }

        // Kategori paling populer
        String popCat = "";
        int maxCat = 0;
        System.out.println("\nJumlah buku per Kategori:");
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " buku");
            if (entry.getValue() > maxCat) {
                maxCat = entry.getValue();
                popCat = entry.getKey();
            }
        }
        System.out.println("Kategori Paling Dominan: " + popCat);

        // Anggota paling aktif
        Member mostActive = null;
        for (Member m : members.values()) {
            if (mostActive == null || m.getTotalAktivitas() > mostActive.getTotalAktivitas()) {
                mostActive = m;
            }
        }
        
        if (mostActive != null && mostActive.getTotalAktivitas() > 0) {
            System.out.println("\nAnggota paling aktif: " 
                    + mostActive.getNama() + " (" + mostActive.getTotalAktivitas() + " kali meminjam)");
        }
        System.out.println("=========================================");
    }
}