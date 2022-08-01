package com.vinit5619692.tasker.bussinessValidations;

import com.vinit5619692.tasker.customExceptions.BusinessException;
import com.vinit5619692.tasker.entity.TaskEntity;
import com.vinit5619692.tasker.helper.JsonDateSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskBusinessPolicy {

    private static final Logger logger = LoggerFactory.getLogger(TaskBusinessPolicy.class);

    /**
     * Implement business logic here
     * @param taskEntity
     */
    public static void checkBusinessLogic(TaskEntity taskEntity)  {
        logger.info("Inside TaskBusinessPolicy.checkBusinessLogic method");

        if(taskEntity == null)
            throw new BusinessException("Data not received");
        if(taskEntity.getTaskCode() == null)
            throw new BusinessException("Task description is mandatory");
        if(taskEntity.getTaskDate() == null)
            throw new BusinessException("Task date is mandatory");
        if(taskEntity.getTaskCode().length() > 50)
            throw new BusinessException("Task description length can not exceed 50");
        if(checkSpecialChar(taskEntity.getTaskCode()))
            throw new BusinessException("No Special character allowed");
        if(taskEntity.getTaskDate().before(new Date()) )
            throw new BusinessException("Past Dates are not allowed");
    }

    /**
     * Check for any special character in string
     * @param taskCode
     * @return Boolean
     */
    private static Boolean checkSpecialChar(String taskCode){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(taskCode);
        return matcher.find();
    }
}
