package com.shopsmart.connect.beans;

import lombok.Data;

@Data
public class Attachment {
    private String filename;
    private byte[] content;
    private String mediaType;
    private AttachmentType attachmentType;
}
