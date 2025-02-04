package com.shopsmart.connect.beans.message;

import lombok.Data;

@Data
public class Message {
    private String from ;
    private String[] to;
    private String text;
}
