package ch.erni.jpa.tutorial.model;

import javax.persistence.*;
import java.util.Date;


/**
 * Created by veda on 7/28/2015.
 */

@Entity
@Table(name="EMP_TASK")
@IdClass(TaskAssignmentId.class)
public class TaskAssigment {

    @Id
    @ManyToOne
    @JoinColumn(name="EMP_ID", referencedColumnName = "EMP_ID", insertable = false, updatable = false)
    private Employee employee;

    @Id
    @ManyToOne
    @JoinColumn(name="TASK_ID", referencedColumnName = "TASK_ID", insertable = false, updatable = false)
    private Task task;

    @Temporal(TemporalType.DATE)
    @Column(name="START_DATE")
    private Date startDate;

    public TaskAssigment(Employee employee, Task task, Date startDate) {
        this.employee = employee;
        this.task = task;
        this.startDate = startDate;
    }

    public TaskAssigment() {

    }

    @Override
    public String toString() {
        return "TaskAssigment{" +
                "startDate=" + startDate +
                '}';
    }
}
