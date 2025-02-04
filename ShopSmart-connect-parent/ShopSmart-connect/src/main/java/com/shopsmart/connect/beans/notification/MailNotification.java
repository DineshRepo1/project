package com.shopsmart.connect.beans.notification;

import com.shopsmart.connect.beans.Attachment;
import lombok.Data;

import java.util.List;
@Data
public class MailNotification extends Notification{
    private  String subject;
    List<Attachment>attachments;
}
