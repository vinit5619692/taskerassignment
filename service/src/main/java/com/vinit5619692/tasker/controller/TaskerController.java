package com.vinit5619692.tasker.controller;


import com.codahale.metrics.annotation.Timed;
import com.vinit5619692.tasker.bussinessValidations.TaskBusinessPolicy;
import com.vinit5619692.tasker.customExceptions.BusinessException;
import com.vinit5619692.tasker.dao.TaskerEntityDAO;
import com.vinit5619692.tasker.entity.TaskEntity;
import com.vinit5619692.tasker.helper.ResponseBuilder;
import com.wordnik.swagger.annotations.Api;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Controller/Resource class to all endpoints related to task
 */
@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "task", description = "Task Resource for performing CRUD operations on Task Table")
    public class TaskerController {

    private static final Logger logger = LoggerFactory.getLogger(TaskerController.class);
    private final TaskerEntityDAO taskerEntityDAO;
    private static final String STATUS_FAILED="Failed";
    private static final String STATUS_SUCCESS="Success";;

    public TaskerController(TaskerEntityDAO taskerEntityDAO) {
        this.taskerEntityDAO = taskerEntityDAO;
    }

    /**
     * Get call to get tasks from id
     * @param id
     * @return Response
     */
    @GET
    @Timed
    @UnitOfWork
    @Path("{id}")
    public Response getTaskById(
            @PathParam("id") String id
    ) {
        logger.info("Inside TaskerController.getTask method");
        return Response
                .ok(ResponseBuilder.getFinalResponse(taskerEntityDAO.findById(id),STATUS_SUCCESS))
                .build();
    }

    /**
     * Get call to get list of tasks from task description with like operator
     * @return Response
     */
    @GET
    @UnitOfWork
    public Response getTasksList() {
        logger.info("Inside TaskerController.getTasksList method");
        return Response
                .ok(ResponseBuilder.getFinalResponse(taskerEntityDAO.getAll(),STATUS_SUCCESS))
                .build();
    }

    /**
     * Post call to create or update task detail
     * @param taskEntity
     * @return Response
     */
    @GET
    @UnitOfWork
    @Path("/post")
    public Response mergeTask(@NotNull @Valid @QueryParam("body") String body) {
        logger.info("Inside TaskerController.mergeTask method");
        try{
            TaskEntity taskEntity = new TaskEntity();
            taskEntity.setTaskCode(body.split("~-~")[0]);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            LocalDate taskDate = LocalDate.parse(body.split("~-~")[1], formatter);

            taskEntity.setTaskDate(java.sql.Date.valueOf(taskDate));

            if(!"undefined".equals(body.split("~-~")[2]))
                taskEntity.setId(body.split("~-~")[2]);

            return Response
                    .ok(ResponseBuilder.getFinalResponse(taskerEntityDAO.merge(taskEntity),STATUS_SUCCESS))
                    .build();
        }catch (BusinessException b){
            return Response
                    .serverError()
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ResponseBuilder.formatCustomErrorMessage(b.getMessage(),STATUS_FAILED))
                    .build();
        }
    }


    /**
     * Delete call to delete a task
     * @param id
     * @return Response
     */
    @DELETE
    @Timed
    @UnitOfWork
    @Path("{id}")
    public Response deleteTask(
            @PathParam("id") String id
    ) {
        logger.info("Inside TaskerController.deleteTask method");
        return Response
                .ok(ResponseBuilder.formatCustomErrorMessage(taskerEntityDAO.deleteTask(id),STATUS_SUCCESS))
                .build();
    }

}
