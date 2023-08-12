package com.emintufan.carrentalsystem.services.abstracts;

import jakarta.mail.MessagingException;

public interface EmailService  {
    String forgotPassword(String to) throws MessagingException;
}
