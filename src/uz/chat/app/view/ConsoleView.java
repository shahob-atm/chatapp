package uz.chat.app.view;

import uz.chat.app.controller.MessageController;
import uz.chat.app.controller.UserController;
import uz.chat.app.dto.LoginDto;
import uz.chat.app.dto.MessageDto;
import uz.chat.app.dto.RegisterDto;
import uz.chat.app.model.Message;
import uz.chat.app.model.User;
import uz.chat.app.util.InputUtil;
import uz.chat.app.util.LoggerUtil;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class ConsoleView {
    private final UserController userController = new UserController();
    private final MessageController messageController = new MessageController();
    private final Scanner scanner = new Scanner(System.in);

    public void showMenu() {
        while (true) {
            System.out.println("\n📌 Chat App");
            System.out.println("1️⃣ Ro'yxatdan o'tish");
            System.out.println("2️⃣ Kirish");
            System.out.println("3️⃣ Chiqish");
            System.out.print("👉 Tanlang: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    boolean success = register();
                    if (success) {
                        System.out.println("✅ Muvaffaqiyatli ro'yxatdan o'tdingiz!");
                        LoggerUtil.logInfo("✅ User muvaffaqiyatli ro'yxatdan o'tdi!");
                    } else {
                        System.out.println("❌ Ro'yxatdan o'tishda xatolik! ");
                        LoggerUtil.logInfo("❌ Ro'yxatdan o'tishda xatolik!");
                    }
                }
                case 2 -> {
                    User user = login();
                    if (user != null) {
                        System.out.println("✅ Muvaffaqiyatli login dan o'tdingiz!");
                        LoggerUtil.logInfo("✅ User login boldi! " + user.getFirstName());
                        System.out.println("------------firstName: " + user.getFirstName() + "----------------");
                        chatMenu(user);
                    } else {
                        System.out.println("❌ login da xatolik! ");
                        LoggerUtil.logInfo("❌ login da xatolik!");
                    }
                }
                case 3 -> {
                    System.out.println("🚪 Chiqish...");
                    return;
                }
                default -> System.out.println("❌ Xato tanlov! Qayta urinib ko'ring.");
            }
        }
    }

    private void chatMenu(User user) {
        while (true) {
            System.out.println("\n📌 Chat Menu");
            System.out.println("1️⃣ Kiruvchi message lar");
            System.out.println("2️⃣ Message yuborish");
            System.out.println("3️⃣ Chiqish");
            System.out.print("👉 Tanlang: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    showMessages(user);
                }
                case 2 -> {
                    boolean message = sendMessage(user);
                    if (message){
                        System.out.println("Message yuborildi!");
                        LoggerUtil.logInfo("✅ Message yuborildi!");
                    }else {
                        System.out.println("Message yuborishda xatolik!");
                        LoggerUtil.logInfo("Message yuborishda xatolik!");
                    }
                }
                case 3 -> {
                    LoggerUtil.logInfo("User chat ni tark etdi!");
                    System.out.println("🚪 Chiqish...");
                    return;
                }
                default -> System.out.println("❌ Xato tanlov! Qayta urinib ko'ring.");
            }
        }
    }

    private boolean register() {
        String email = InputUtil.getValidEmail();
        if (email == null) {
            return false;
        }

        String firstName = InputUtil.getValidFirstName();
        if (firstName == null) {
            return false;
        }

        String lastName = InputUtil.getValidLastName();
        if (lastName == null) {
            return false;
        }

        String password = InputUtil.getValidPassword();
        if (password == null) {
            return false;
        }

        RegisterDto registerDto = new RegisterDto(firstName, lastName, email, password, LocalDateTime.now());
        return userController.register(registerDto);
    }

    private User login() {
        String emailForLogin = InputUtil.getValidEmailForLogin();
        if (emailForLogin == null) {
            return null;
        }

        String password = InputUtil.getValidPassword();
        if (password == null) {
            return null;
        }

        LoginDto loginDto = new LoginDto(emailForLogin, password);
        return userController.login(loginDto);
    }

    private void showMessages(User user) {
        List<Message> allInvoiceMessages = messageController.getAllInvoiceMessages(user);
        for (Message message : allInvoiceMessages) {
            System.out.println("Author: " + message.getFromEmail() + " | Text: " + message.getText());
        }
    }

    private boolean sendMessage(User user) {
        System.out.println("To email kiriting: ");
        String emailForLogin = InputUtil.getValidEmailForLogin();
        if (emailForLogin == null) {
            return false;
        }

        System.out.print("Text ni kiriting: ");
        String text = scanner.nextLine();

        MessageDto messageDto = new MessageDto(text, LocalDateTime.now(), user.getEmail(), emailForLogin);
        return messageController.sendMessage(messageDto);
    }
}
