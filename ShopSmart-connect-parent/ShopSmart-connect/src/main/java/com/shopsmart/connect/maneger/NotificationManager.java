package com.shopsmart.connect.maneger;

import com.shopsmart.connect.beans.notification.MailNotification;
import com.shopsmart.connect.beans.notification.Notification;

public interface NotificationManager {
    void text(Notification notification);
    void email(MailNotification mailNotification);
}
