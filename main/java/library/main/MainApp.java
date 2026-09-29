package library.main;

import library.model.Book;
import library.service.LibraryService;

import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryService service = new LibraryService();

        // Data dummy untuk uji coba
        service.addBook(new Book("Pemrograman Java", "Budi", 2021, "Teknologi"));
        service.addBook(new Book("Struktur Data C++", "Agis", 2022, "Teknologi"));
        service.addBook(new Book("Filsafat Modern", "Seno", 2019, "Filsafat"));
        service.registerMember("M01", "agus"); // akan otomatis menjadi "Agus" berkat Character manipulasi
        service.registerMember("M02", "budi");

        boolean running = true;
        while (running) {
            System.out.println("\n=== MENU PERPUSTAKAAN MINI ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Daftar Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Pinjam Buku");
            System.out.println("5. Kembalikan Buku");
            System.out.println("6. Laporan Perpustakaan");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");
            
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    System.out.print("Judul: "); String judul = scanner.nextLine();
                    System.out.print("Penulis: "); String penulis = scanner.nextLine();
                    System.out.print("Tahun Terbit: "); int tahun = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Kategori: "); String kategori = scanner.nextLine();
                    service.addBook(new Book(judul, penulis, tahun, kategori));
                    System.out.println("Buku ditambahkan!");
                    break;
                case 2:
                    service.listBooks();
                    break;
                case 3:
                    System.out.print("Masukkan keyword (Judul / Kategori): ");
                    String keyword = scanner.nextLine();
                    service.searchBook(keyword);
                    break;
                case 4:
                    System.out.print("ID Anggota (contoh: M01): "); String idPinjam = scanner.nextLine();
                    System.out.print("Judul Buku: "); String judulPinjam = scanner.nextLine();
                    try {
                        service.borrowBook(idPinjam, judulPinjam);
                    } catch (Exception e) { // Menangkap custom exception
                        System.err.println("Gagal Meminjam: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("ID Anggota (contoh: M01): "); String idKembali = scanner.nextLine();
                    System.out.print("Judul Buku yang dikembalikan: "); String judulKembali = scanner.nextLine();
                    try {
                        service.returnBook(idKembali, judulKembali);
                    } catch (Exception e) {
                        System.err.println("Gagal Mengembalikan: " + e.getMessage());
                    }
                    break;
                case 6:
                    service.generateReport();
                    break;
                case 7:
                    running = false;
                    System.out.println("Terima kasih telah menggunakan sistem ini!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        scanner.close();
    }
}
