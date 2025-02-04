package com.shopsmart.connect.beans.notification;

import lombok.Data;

import java.util.Map;
@Data
public class Notification {
    private String from;
    private String[] to;
    private String templateName;
    private Map<String, Object> tokens;
}
