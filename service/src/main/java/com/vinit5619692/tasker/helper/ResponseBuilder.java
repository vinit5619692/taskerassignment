package com.vinit5619692.tasker.helper;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {

    /**
     * Put Data into "body" key and add Status to the response
     * @param body
     * @param status
     * @return
     */
    public static Map<String,Object> getFinalResponse(Object body,String status){
        Map<String,Object> response = new HashMap<>();
        response.put("body",body);
        response.put("status",status);
        return  response;
    }

    /**
     * Add status and format the response
     * @param message
     * @param status
     * @return
     */
    public static Map<String,Object> formatCustomErrorMessage(String message,String status){
        Map<String,Object> response = new HashMap<>();
        response.put("message",message);
        response.put("status",status);
        return  response;
    }
}
