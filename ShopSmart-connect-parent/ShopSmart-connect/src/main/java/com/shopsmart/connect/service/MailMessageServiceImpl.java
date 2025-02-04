package com.shopsmart.connect.service;

import com.shopsmart.connect.beans.AttachmentType;
import com.shopsmart.connect.beans.InlineAttachment;
import com.shopsmart.connect.beans.message.MailMessage;
import com.shopsmart.connect.beans.message.Message;
import com.shopsmart.connect.exception.MailMessageAttachmentfailedException;
import jakarta.mail.MessagingException;
import jakarta.mail.util.ByteArrayDataSource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@AllArgsConstructor
@Slf4j
public class MailMessageServiceImpl implements MessageService<MailMessage>{
    private JavaMailSender javaMailSender;

    @Override
    public void dispatch( final MailMessage message) {
        if (log.isDebugEnabled()) {
            log.debug("in dispatch()");
        }
        javaMailSender.send((mimeMessage) -> {
            log.info("preparing the mimeMessage to dispatch()");
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(message.getFrom());
            mimeMessageHelper.setTo(message.getTo());
            mimeMessageHelper.setText(message.getText(),true);
            mimeMessageHelper.setSubject(message.getSubject());

            if(message.getAcctachment() != null && !message.getAcctachment().isEmpty()){
                message.getAcctachment().forEach(attachment -> {
                    if(attachment.getAttachmentType()== AttachmentType.ATTACHMENT){
                        try {
                            mimeMessageHelper.addAttachment(attachment.getFilename(), new ByteArrayDataSource(attachment.getContent(),
                                    attachment.getMediaType()));
                        } catch (MessagingException e) {
                            throw new MailMessageAttachmentfailedException("unable to add attachment", e);
                        }
                    }
                    else if(attachment.getAttachmentType()==AttachmentType.INLINE){
                        try {
                            mimeMessageHelper.addInline(((InlineAttachment)attachment).getCId(),
                                    new ByteArrayDataSource(attachment.getContent(), attachment.getMediaType()));
                        } catch (MessagingException e) {
                            throw new MailMessageAttachmentfailedException("unable to add attachment", e);
                        }
                    }
                });
            }
        });
    }
}
