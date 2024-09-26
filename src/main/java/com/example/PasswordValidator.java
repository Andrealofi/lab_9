package com.example;

public class PasswordValidator {

    private NotificationService notificationService;

    // Constructor that takes NotificationService as a dependency
    public PasswordValidator(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public boolean isValidPassword(String password) {

        // Chack for null or empty password:
        if (isNullOrEmpty(password)) {

            return false;
        } 

        return hasMinimumLength(password) && hasUppercaseLetter(password);

        
    }

    // Method that checks if the password is null or empty.
    private boolean isNullOrEmpty(String password) {
        return password == null || password.isEmpty();
    }

    // Method that checks if the password meets the minimum length requirement.
    private boolean hasMinimumLength(String password) {
        return password.length() >= 8 ;
    }

    // Method that checks if the password contains at least one uppercase letter.
    private boolean hasUppercaseLetter(String password) {
        return password.matches(".*[A-Z].*");
    }

    // Method to validate and send a notification.
    public void validatePasswordAndNotify(String password) {
        if (isValidPassword(password)) {
            notificationService.sendNotification("Password is valid.");

        } else {

            notificationService.sendNotification("Password is invalid.");
        }
    }


}
