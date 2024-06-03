package com.discussion.handler;

import com.discussion.utils.JacksonUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

    public ObjectNode build(Object data) {
       ObjectNode response =  JacksonUtils.objectNode();
       response.putPOJO("data",data);
       return response;
    }

}
