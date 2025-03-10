import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Scanner scanner;
    private final String fileName = "src/data/data.txt";

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        String pilihan, username, password;
        User user = new User();

        while (true) {
            System.out.println("Halo, selamat datang di aplikasi kami!\nSilahkan pilih menu:\n1. Daftar\n2. Login\n3. Keluar");
            pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                System.out.println("=== DAFTAR ===");
                System.out.print("Masukkan Username: ");
                username = scanner.nextLine();
                System.out.print("Masukkan Password: ");
                password = scanner.nextLine();

                user.setUsername(username);
                user.setPassword(password);

                main.registerUser(user);
            } else if (pilihan.equals("2")) {
                System.out.println("=== LOGIN ===");
                System.out.print("Masukkan Username: ");
                username = scanner.nextLine();
                System.out.print("Masukkan Password: ");
                password = scanner.nextLine();

                user.setUsername(username);
                user.setPassword(password);

                main.loginUser(user);
            } else if (pilihan.equals("3")) {
                System.out.println("Terima kasih telah menggunakan aplikasi!");
                break;
            } else {
                System.out.println("Input tidak valid, silakan coba lagi.");
            }
        }
    }

    // Fungsi untuk mendaftarkan user baru ke dalam file
    private void registerUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String template = "Username: " + username + "\nPassword: " + password + "\n";

        try (FileWriter fileWriter = new FileWriter(fileName, true)) { // Mode append agar data lama tidak terhapus
            fileWriter.write(template);
            System.out.println("[DEBUG] Data berhasil disimpan: " + template); // Debug: Menampilkan data yang disimpan
            System.out.println("Pendaftaran berhasil!");
        } catch (IOException e) {
            System.out.println("[ERROR] Terjadi kesalahan saat menyimpan data: " + e.getMessage()); // Debug: Menampilkan error jika gagal menyimpan
        }
    }

    // Fungsi untuk login user dengan mencocokkan data dalam file
    private void loginUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        System.out.println("[DEBUG] Mencari user dengan Username: " + username + " dan Password: " + password);

        List<String> searchData = new ArrayList<>();
        searchData.add("Username: " + username);
        searchData.add("Password: " + password);

        try {
            File file = new File(fileName);
            scanner = new Scanner(file);
            boolean usernameFound = false;
            boolean passwordFound = false;

            // Membaca file baris per baris untuk mencocokkan username dan password
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println("[DEBUG] Membaca baris: " + line); // Debug: Menampilkan baris yang sedang dibaca

                if (line.equals(searchData.get(0))) { // Mencocokkan username
                    usernameFound = true;
                }
                if (line.equals(searchData.get(1))) { // Mencocokkan password
                    passwordFound = true;
                }
            }

            // Menentukan apakah login berhasil berdasarkan hasil pencocokan
            if (usernameFound && passwordFound) {
                System.out.println("Login berhasil! Selamat datang, " + username);
            } else {
                System.out.println("[ERROR] Username atau password salah."); // Debug: Menampilkan pesan jika login gagal
            }
        } catch (IOException e) {
            System.out.println("[ERROR] File tidak ditemukan: " + e.getMessage()); // Debug: Menampilkan error jika file tidak ditemukan
        }
    }
}
