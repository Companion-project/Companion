package com.example.companion.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

//메일링 시 마다 사용하므로 모듈화로 진행
@Service
public class EmailSendService {
    @Autowired
    JavaMailSender mailSender;
    public void mailsend(String html, String subject, String fromEmail, String toEmail){
        //메일링 기본문법
        MimeMessage msg = mailSender.createMimeMessage();
        try {
            msg.setHeader("content-type", "text/html; charset=UTF-8");
            msg.setContent(html, "text/html; charset=UTF-8"); //내용
            msg.setSubject(subject); //제목
            msg.setFrom(new InternetAddress(fromEmail)); //보내는 사람
            msg.setRecipient(MimeMessage.RecipientType.TO,
                    new InternetAddress(toEmail)); //받는사람
            mailSender.send(msg); //이메일전송
        }catch ( MessagingException e){
            e.printStackTrace();
        }
    }
}
