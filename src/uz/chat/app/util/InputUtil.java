package uz.chat.app.util;

import uz.chat.app.repository.UserRepository;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getValidEmail() {
        String email;
        while (true) {
            System.out.print("📩 Email kiriting (orqaga qaytish uchun 0): ");
            email = scanner.nextLine().trim();

            if (email.equals("0") || email.equalsIgnoreCase("exit")) {
                LoggerUtil.logInfo("🔙 Foydalanuvchi email kiritishdan voz kechdi.");
                return null;
            }

            if (ValidatorUtil.isValidEmail(email)) {
                LoggerUtil.logInfo("⚠️ Xato email kiritildi: " + email);
                System.out.println("❌ Noto‘g‘ri email! Qayta urinib ko‘ring.");
                continue;
            }

            if (UserRepository.isEmailTaken(email)) {
                LoggerUtil.logInfo("❌ Foydalanuvchi ro‘yxatdan o‘tmoqchi bo‘ldi, lekin email band: " + email);
                System.out.println("⚠️ Bu email allaqachon ro‘yxatdan o‘tgan! Yangi email kiriting.");
                continue;
            }

            return email;
        }
    }

    public static String getValidFirstName() {
        String firstName;
        while (true) {
            System.out.print("📩 FirstName kiriting (orqaga qaytish uchun 0): ");
            firstName = scanner.nextLine().trim();

            if (firstName.equals("0") || firstName.equalsIgnoreCase("exit")) {
                LoggerUtil.logInfo("🔙 Foydalanuvchi firstName kiritishdan voz kechdi.");
                return null;
            }

            if (ValidatorUtil.isValidFullName(firstName)) {
                LoggerUtil.logInfo("⚠️ Xato firstName kiritildi: " + firstName);
                System.out.println("❌ Noto‘g‘ri firstName! Qayta urinib ko‘ring.");
                continue;
            }

            return firstName;
        }
    }

    public static String getValidLastName() {
        String lastName;
        while (true) {
            System.out.print("📩 LastName kiriting (orqaga qaytish uchun 0): ");
            lastName = scanner.nextLine().trim();

            if (lastName.equals("0") || lastName.equalsIgnoreCase("exit")) {
                LoggerUtil.logInfo("🔙 Foydalanuvchi lastName kiritishdan voz kechdi.");
                return null;
            }

            if (ValidatorUtil.isValidFullName(lastName)) {
                LoggerUtil.logInfo("⚠️ Xato lastName kiritildi: " + lastName);
                System.out.println("❌ Noto‘g‘ri lastName! Qayta urinib ko‘ring.");
                continue;
            }

            return lastName;
        }
    }

    public static String getValidPassword() {
        String password;
        while (true) {
            System.out.print("📩 Password kiriting (orqaga qaytish uchun 0): ");
            password = scanner.nextLine().trim();

            if (password.equals("0") || password.equalsIgnoreCase("exit")) {
                LoggerUtil.logInfo("🔙 Foydalanuvchi password kiritishdan voz kechdi.");
                return null;
            }

            if (!ValidatorUtil.isValidPassword(password)) {
                LoggerUtil.logInfo("⚠️ Xato password kiritildi: " + password);
                System.out.println("❌ Noto‘g‘ri password! Qayta urinib ko‘ring.");
                continue;
            }

            return password;
        }
    }

    public static String getValidEmailForLogin() {
        String email;
        while (true) {
            System.out.print("📩 Email kiriting (orqaga qaytish uchun 0): ");
            email = scanner.nextLine().trim();

            if (email.equals("0") || email.equalsIgnoreCase("exit")) {
                LoggerUtil.logInfo("🔙 Foydalanuvchi email kiritishdan voz kechdi.");
                return null;
            }

            if (ValidatorUtil.isValidEmail(email)) {
                LoggerUtil.logInfo("⚠️ Xato email kiritildi: " + email);
                System.out.println("❌ Noto‘g‘ri email! Qayta urinib ko‘ring!");
                continue;
            }

            if (!UserRepository.isEmailTaken(email)) {
                LoggerUtil.logInfo("❌ Foydalanuvchi logindan o‘tmoqchi bo‘ldi, lekin email topilmadi: " + email);
                System.out.println("⚠️ Bu email topilmadi! Iltimos ro'yxatdan o'ting");
                continue;
            }

            return email;
        }
    }
}
