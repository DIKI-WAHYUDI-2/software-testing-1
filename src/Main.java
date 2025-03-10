import model.User;

import java.io.*;
import java.util.*;

public class Main {
    private final String fileName = "src/data/data.txt";

    public static void main(String[] args) {
        Main main = new Main();
        Scanner scanner = new Scanner(System.in);
        String pilihan, username, password;
        User user = new User();

        while (true) {
            System.out.println("Halo, selamat datang di aplikasi kami!");
            System.out.println("Silahkan pilih menu: ");
            System.out.println("1. Daftar");
            System.out.println("2. Login");
            System.out.println("3. Keluar");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextLine();

            if (pilihan.equals("1")) {
                System.out.println("\n=== REGISTRASI ===");

                System.out.print("Masukkan Username: ");
                username = scanner.nextLine();

                System.out.print("Masukkan Password: ");
                password = scanner.nextLine();

                user.setUsername(username);
                user.setPassword(password);

                main.registerUser(user);
            } else if (pilihan.equals("2")) {
                System.out.println("\n=== LOGIN ===");

                System.out.print("Masukkan Username: ");
                username = scanner.nextLine();

                System.out.print("Masukkan Password: ");
                password = scanner.nextLine();

                user.setUsername(username);
                user.setPassword(password);

                main.loginUser(user);
            } else if (pilihan.equals("3")){
                System.out.println("Terima Kasih sudah menggunakan layanan kami.");
                return;
            }
        }
    }

    private void registerUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String data = username + "," + password; // Perbaikan format penyimpanan

        try (FileWriter fileWriter = new FileWriter(fileName, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

            bufferedWriter.write(data);
            bufferedWriter.newLine(); // Tambah baris baru agar user berikutnya ada di baris selanjutnya
            System.out.println("[DEBUG] Data berhasil ditulis ke file: " + data);
            System.out.println("Registrasi berhasil! Silahkan login.");

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menyimpan data: " + e.getMessage());
        }
    }

    private void loginUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String searchUser = username + "," + password; // Format login harus sesuai dengan penyimpanan

        try (Scanner scanner = new Scanner(new File(fileName))) {
            boolean found = false;
            int lineNumber = 0;

            while (scanner.hasNextLine()) {
                lineNumber++;
                String line = scanner.nextLine();

                System.out.println("[DEBUG] Membaca baris ke-" + lineNumber + ": " + line); // Debug print

                if (line.equals(searchUser)) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("Login berhasil! Selamat datang, " + username);
            } else {
                System.out.println("Username atau password salah.");
            }

        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membaca file: " + e.getMessage());
        }
    }
}
