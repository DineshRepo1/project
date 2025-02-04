package com.shopsmart.connect.service;

import com.shopsmart.connect.beans.message.Message;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@AllArgsConstructor
@Slf4j
public class TextMessageServiceImple implements MessageService{

    @Override
    public void dispatch(Message message) {
        log.info("sending an text message to : {} with body: {}", message.getTo(), message.getText());
    }
}
