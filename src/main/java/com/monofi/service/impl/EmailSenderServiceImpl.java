package com.monofi.service.impl;

import com.monofi.exception.EmailSendingException;
import com.monofi.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailSenderServiceImpl implements EmailSenderService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmailWithAttachment(String toEmail,
                          String subject,
                          String message,
                          MultipartFile attachment) throws Exception{
        if (attachment.getSize()<1048576 || attachment.getSize()>20971520){
            throw new EmailSendingException("File size must be between 1mb and 20mb");
        }
        if (toEmail.isEmpty()||subject.isEmpty()||message.isEmpty()||attachment.isEmpty()){
            throw new EmailSendingException("Please fill all fields");
        }
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("MonoFi");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), attachment);
        }catch (Exception e){
            throw new EmailSendingException("An error occur while sending email");
        }
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmail(String toEmail, String subject, String message) throws Exception {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom("MonoFi");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.setSubject(subject);
        }catch (Exception e){
            throw new EmailSendingException("An error occur while sending confirmation email");
        }
        javaMailSender.send(mimeMessage);
    }
}
