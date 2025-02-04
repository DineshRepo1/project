package com.shopsmart.connect.beans.message;

import com.shopsmart.connect.beans.Attachment;
import lombok.Data;

import java.util.List;
@Data
public class MailMessage extends Message {
    private String subject;
    List<Attachment> acctachment;
}
