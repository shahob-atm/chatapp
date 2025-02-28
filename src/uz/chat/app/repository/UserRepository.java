package uz.chat.app.repository;

import uz.chat.app.model.User;
import uz.chat.app.util.LoggerUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String USERS_FILE = "db/users.txt";

    public static boolean isEmailTaken(String email) {
        List<User> users = readUsers();
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean saveUser(User user) {
        boolean fileExists = new File(USERS_FILE).exists();
        try (ObjectOutputStream oos = fileExists
                ? new User.AppendObjectOutputStream(new FileOutputStream(USERS_FILE, true))
                : new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(user);
            LoggerUtil.logInfo("✅ Yangi user qo‘shildi: " + user.getEmail());
            return true;
        } catch (IOException e) {
            LoggerUtil.logSevere("❌ User faylga saqlanayotganda xatolik: " + e.getMessage());
            System.out.println("❌ Xatolik, user malumotlarini qo'shishda " + e.getMessage());
        }
        return false;
    }

    public static List<User> readUsers() {
        List<User> users = new ArrayList<>();

        File file = new File(USERS_FILE);
        if (!file.exists() || file.length() == 0) {
            return users;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    User user = (User) ois.readObject();
                    users.add(user);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            LoggerUtil.logSevere("❌ Userlarni fayldan o‘qishda xatolik: " + e.getMessage());
            System.out.println("❌ Xatolik, userlar malumotlarini o'qishda " + e.getMessage());
        }
        return users;
    }
}
