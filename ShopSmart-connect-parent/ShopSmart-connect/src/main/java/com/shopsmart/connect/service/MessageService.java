package com.shopsmart.connect.service;

import com.shopsmart.connect.beans.message.Message;

public interface MessageService <T extends Message>{
    void dispatch(final T message);
}
