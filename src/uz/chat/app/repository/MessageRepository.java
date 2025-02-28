package uz.chat.app.repository;

import uz.chat.app.model.Message;
import uz.chat.app.util.LoggerUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageRepository {
    private static final String CHAT_FILE = "db/chats.txt";

    public static boolean saveMessage(Message message) {
        boolean fileExists = new File(CHAT_FILE).exists();
        try (ObjectOutputStream oos = fileExists
                ? new Message.AppendObjectOutputStream(new FileOutputStream(CHAT_FILE, true))
                : new ObjectOutputStream(new FileOutputStream(CHAT_FILE))) {
            oos.writeObject(message);
            LoggerUtil.logInfo("✅ Yangi meessage qo‘shildi: " + message.getText());
            return true;
        } catch (IOException e) {
            System.out.println("❌ Xatolik, message malumotlarini qo'shishda " + e.getMessage());
            LoggerUtil.logSevere("❌ Message faylga saqlanayotganda xatolik: " + e.getMessage());
        }
        return false;
    }

    public static List<Message> loadMessages() {
        List<Message> messages = new ArrayList<Message>();

        File file = new File(CHAT_FILE);
        if (!file.exists() || file.length() == 0) {
            return messages;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Message message = (Message) ois.readObject();
                    messages.add(message);
                } catch (EOFException e) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Xatolik, message lar malumotlarini o'qishda " + e.getMessage());
            LoggerUtil.logSevere("❌ Message larni fayldan o‘qishda xatolik: " + e.getMessage());
        }
        return messages;
    }
}
