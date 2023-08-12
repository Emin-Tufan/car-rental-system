package com.emintufan.carrentalsystem.services.concretes;

import com.emintufan.carrentalsystem.dao.UserDao;
import com.emintufan.carrentalsystem.entities.User;
import com.emintufan.carrentalsystem.services.abstracts.EmailService;
import com.emintufan.carrentalsystem.util.EmailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;
    private UserDao userDao;
    private EmailUtil emailUtil;

    @Autowired
    public EmailServiceImpl(JavaMailSender javaMailSender, UserDao userDao,EmailUtil emailUtil) {
        this.javaMailSender = javaMailSender;
        this.userDao = userDao;
        this.emailUtil = emailUtil;
    }


    @Override
    public String forgotPassword(String to){

        User user = userDao.findByEmail(to).orElseThrow(()->new RuntimeException("User not found with this email  !"+to));
        try {
            emailUtil.sendSetPasswordEmail(to);
        } catch (MessagingException e) {
            throw new RuntimeException("Unable to send set password email please try again !");
        }
        return "Please check your email to set new password to your account !";
    }
}
