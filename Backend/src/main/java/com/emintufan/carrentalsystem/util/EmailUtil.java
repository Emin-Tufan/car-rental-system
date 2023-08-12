  package com.emintufan.carrentalsystem.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
public class EmailUtil {

  private JavaMailSender javaMailSender;

  @Autowired
  public EmailUtil(JavaMailSender javaMailSender) {
    this.javaMailSender = javaMailSender;
  }

  public void sendSetPasswordEmail(String email) throws MessagingException {
    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
    mimeMessageHelper.setTo(email);
    mimeMessageHelper.setSubject("Set Password");
    mimeMessageHelper.setText("""
        <div>
          <a href="http://localhost:3000/set-password?email=%s" target="_blank">Click the link below to enter your new password</a>
        </div>
        """.formatted(email), true);

    javaMailSender.send(mimeMessage);
  }
}
