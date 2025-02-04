package com.shopsmart.connect.preparater;

import java.util.Map;

public interface MessagePreparater {
    String prepare(String templateName, Map<String, Object> tokens);
}
