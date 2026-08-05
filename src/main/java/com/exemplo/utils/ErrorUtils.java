package com.exemplo.utils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ErrorUtils {

    private static final ResourceBundle errors = ResourceBundle.getBundle("errors");

    public static String getErrorMessage(String key) {
        try {
            return errors.getString(key);
        } catch (Exception e) {
            return "An unknown error occurred.";
        }
    }

    public static String getErrorMessage(String key, Object... args) {
        try {
            String pattern = errors.getString(key);
            return MessageFormat.format(pattern, args);
        } catch (Exception e) {
            return "An unknown error occurred.";
        }
    }
}
