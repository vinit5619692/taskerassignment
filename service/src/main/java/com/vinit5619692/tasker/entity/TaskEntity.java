package com.vinit5619692.tasker.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vinit5619692.tasker.helper.JsonDateSerializer;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity class for tasks Table
 */
@Entity
@Table(name = "tasks")
@NamedQueries({
        @NamedQuery(name="TaskEntity.getAll",
                query="SELECT t FROM TaskEntity t "),
})
public class TaskEntity {

    @Id
    private String id;

    @Column(name = "taskCode")
    private String taskCode;

    @Column(name = "taskDate")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using= JsonDateSerializer.class)
    private Date taskDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }
}
