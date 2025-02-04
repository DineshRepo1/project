package com.shopsmart.connect.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "of")
public class InlineAttachment extends Attachment{
    private String cId;
}
