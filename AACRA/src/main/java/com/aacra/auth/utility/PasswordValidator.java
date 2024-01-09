package com.aacra.auth.utility;

public class PasswordValidator {
    public boolean isPasswordValid(String password) {
        // Check for at least one special character
        boolean hasSpecialCharacter = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        // Check for minimum length of 8 characters
        boolean hasMinimumLength = password.length() >= 8;

        // Check for at least one capital letter
        boolean hasCapitalLetter = password.matches(".*[A-Z].*");

        // Check for at least one small letter
        boolean hasSmallLetter = password.matches(".*[a-z].*");

        // Return true if all criteria are met
        return hasSpecialCharacter && hasMinimumLength && hasCapitalLetter && hasSmallLetter;
    }
}
