package com.dev.plant_management.service;

import com.dev.plant_management.entity.WateringNeed;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void sendWateringReminder(String to, WateringNeed need){
        SimpleMailMessage message =new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Arrosage nécessaire pour votre plante ");
        message.setText("Bonjour, \n\nIl est temps d'arroser votre plante : "
                + need.getPlant().getName() + "\n"
                + "Quantité à arroser : " + need.getQuantityInLiters() + " L\n"
                + "Merci de prendre soin de votre plante !");

        mailSender.send(message);
    }
}
