package com.vinit5619692.tasker.dao;


import com.vinit5619692.tasker.bussinessValidations.TaskBusinessPolicy;
import com.vinit5619692.tasker.customExceptions.BusinessException;
import com.vinit5619692.tasker.entity.TaskEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

/**
 * DAO class to access date from database
 */
public class TaskerEntityDAO extends AbstractDAO<TaskEntity> {

    private static final String DELETE_TASK_QUERY = "DELETE FROM TaskEntity t WHERE t.id = :taskId";


    private SessionFactory sessionFactory;
    public TaskerEntityDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    /**
     * Return task based on id provided
     * @param id
     * @return TaskEntity
     */
    public TaskEntity findById(String id){
        return  get(id);
    }

    /**
     * Create or update task
     * @param taskEntity
     * @return TaskEntity
     */
    public TaskEntity merge(TaskEntity taskEntity){

        try {
            TaskBusinessPolicy.checkBusinessLogic(taskEntity);
            if(StringUtils.isEmpty(taskEntity.getId()))
                taskEntity.setId(UUID.randomUUID().toString());
            TaskEntity taskEntity1 = (TaskEntity) sessionFactory.getCurrentSession().merge(taskEntity);
            sessionFactory.getCurrentSession().flush();
             return taskEntity1;
        }catch (BusinessException b){
            throw b;
        }

    }

    /**
     * Return tasks based on filter parameter on taskCode column
     * @return
     */
    public List<TaskEntity> getAll(){
          return list(namedTypedQuery("TaskEntity.getAll"));
    }

    /**
     * Delete task based on id provided
     * @param id
     * @return
     */
    public String deleteTask(String id){

        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_TASK_QUERY);
        query.setParameter("taskId", id);
        int result = query.executeUpdate();
        sessionFactory.getCurrentSession().flush();
        return result + " records deleted";
    }
}
