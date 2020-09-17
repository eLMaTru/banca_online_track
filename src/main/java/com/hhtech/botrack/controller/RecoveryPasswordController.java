package com.hhtech.botrack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;

import com.hhtech.botrack.config.CustomPropertyConfig;
import com.hhtech.botrack.model.Mail;
import com.hhtech.botrack.service.EmailSenderService;

@Controller
public class RecoveryPasswordController {

    private EmailSenderService emailSenderService;
    private CustomPropertyConfig customPropertyConfig;

    public RecoveryPasswordController(EmailSenderService emailSenderService,
            CustomPropertyConfig customPropertyConfig) {
        this.emailSenderService = emailSenderService;
        this.customPropertyConfig = customPropertyConfig;
    }

    private String sendMail(String to) throws MessagingException {
        Mail mail = getMail(to);
        emailSenderService.sendEmail(mail);
        return "Check your email";

    }

    private Mail getMail(String to) {
        Mail mail = new Mail();
        mail.setFrom(customPropertyConfig.mailFrom);
        mail.setTo(to);
        mail.setSubject("Simple mail with AWS SES and Spring Boot");
        Map<String, Object> model = new HashMap<>();
        model.put("personName", "Pedro");// traer nombre desde la db
        mail.setModel(model);
        return mail;
    }

    @GetMapping("/recovery-password")
    public String recoveryPassword(Mail mail) {

        return "recoverpw";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute Mail mail, BindingResult errors, Model model)
            throws MessagingException {
        model.addAttribute("email", mail.getTo());
        sendMail(mail.getTo());
        return "confirm-mail";
    }

}
