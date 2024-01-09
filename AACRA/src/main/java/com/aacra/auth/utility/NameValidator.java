package com.aacra.auth.utility;

public class NameValidator {
    public boolean isAlphabetic(String name) {
        // Using regular expression to check if the name contains only alphabet characters
        // If you want to allow spaces in the name, you can modify the regex to include "\\s" as well
        return name.matches("^[a-zA-Z]+$");
    }
}