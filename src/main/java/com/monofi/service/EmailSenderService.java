package com.monofi.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailSenderService {
    void sendEmailWithAttachment(String toEmail, String subject, String message, MultipartFile attachment) throws Exception;
    void sendEmail(String toEmail, String subject, String message) throws Exception;
}
