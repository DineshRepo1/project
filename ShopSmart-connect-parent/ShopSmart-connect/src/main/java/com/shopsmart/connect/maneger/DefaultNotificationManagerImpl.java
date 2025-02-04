package com.shopsmart.connect.maneger;

import com.shopsmart.connect.beans.message.MailMessage;
import com.shopsmart.connect.beans.message.Message;
import com.shopsmart.connect.beans.notification.MailNotification;
import com.shopsmart.connect.beans.notification.Notification;
import com.shopsmart.connect.preparater.MessagePreparater;
import com.shopsmart.connect.service.MailMessageServiceImpl;
import com.shopsmart.connect.service.TextMessageServiceImple;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;

@AllArgsConstructor
@Slf4j
public class DefaultNotificationManagerImpl implements NotificationManager{
    private final MessagePreparater messagePreparater;
    private final MailMessageServiceImpl mailMessageService;
    private final TextMessageServiceImple textMessageServiceImple;

    @Override
    public void text(Notification notification) {
        Message message = new Message();
        String processedMessage = null;

        processedMessage = messagePreparater
                .prepare(notification.getTemplateName(), notification.getTokens());
        message.setFrom(notification.getFrom());
        message.setTo(notification.getTo());
        message.setText(processedMessage);

        log.info("dispatching the message to : {} using textMessageService",
                message.getTo());

        textMessageServiceImple.dispatch(message);
    }

    @Override
    public void email(MailNotification mailNotification) {
        MailMessage mailMessage = new MailMessage();
        String processedMessage = null;

        processedMessage = messagePreparater
                .prepare(mailNotification.getTemplateName(), mailNotification.getTokens());
        mailMessage.setFrom(mailNotification.getFrom());
        mailMessage.setText(processedMessage);
        mailMessage.setTo(mailNotification.getTo());
        mailMessage.setSubject(mailNotification.getSubject());
        mailMessage.setAcctachment(mailNotification.getAttachments());

        log.info("dispatching the message to : {} using mailMessageService",
                mailMessage.getTo());

        mailMessageService.dispatch(mailMessage);
    }
}
