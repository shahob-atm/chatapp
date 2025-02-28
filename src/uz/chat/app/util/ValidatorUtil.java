package uz.chat.app.util;

import java.util.regex.Pattern;

public class ValidatorUtil {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
    private static final String NAME_REGEX = "^[A-Z][a-z]+$";

    public static boolean isValidEmail(String email) {
        return !Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidFullName(String name) {
            return !Pattern.matches(NAME_REGEX, name);
    }

    public static boolean isValidPassword(String password) {
        return Pattern.matches(PASSWORD_REGEX, password);
    }
}
