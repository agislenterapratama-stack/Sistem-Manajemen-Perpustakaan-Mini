# Sistem Manajemen Perpustakaan Mini 📚

Sebuah aplikasi *Command Line Interface* (CLI) berbasis Java untuk mengelola sistem perpustakaan mini. Proyek ini dibangun untuk mendemonstrasikan implementasi mendalam dari prinsip *Object-Oriented Programming* (OOP) serta berbagai fitur lanjutan di ekosistem Java.

## ✨ Fitur Utama
* **Manajemen Data Buku:** Tambah dan tampilkan koleksi buku perpustakaan.
* **Pencarian Pintar:** Cari buku berdasarkan Judul atau Kategori (*case-insensitive* menggunakan manipulasi String).
* **Sistem Transaksi:** Peminjaman dan pengembalian buku dengan validasi keamanan.
* **Analisis Data:** Laporan otomatis mengenai total transaksi, buku terpopuler, kategori dominan, dan anggota paling aktif.
* **Keamanan Sistem:** Pencegahan *error* menggunakan *Custom Exception* dan *Assertion*.

## 🛠️ Teknologi & Konsep yang Digunakan
* **Bahasa:** Java
* **Paradigma:** Object-Oriented Programming (Class, Object, Encapsulation, Method, Constructor, Package)
* **Struktur Data:** Primitive Types, Reference Types (`ArrayList`, `HashMap`)
* **Kontrol Alur:** Kondisional (`if-else`, `switch-case`) & Looping (`while`, `for-each`)
* **Fitur Lanjutan Java:**
  * Manipulasi `String` dan `Character`
  * *Exception Handling* dengan Custom Exception (`BookNotFoundException`, dll.)
  * Validasi internal menggunakan `assert`

## 📂 Struktur Proyek
```text
library/
├── exception/
│   ├── BookNotFoundException.java
│   ├── BookUnavailableException.java
│   └── BorrowLimitExceededException.java
├── main/
│   └── MainApp.java
├── model/
│   ├── Book.java
│   └── Member.java
└── service/
    └── LibraryService.java
