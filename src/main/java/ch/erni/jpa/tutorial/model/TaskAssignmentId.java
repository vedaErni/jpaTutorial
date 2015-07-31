package ch.erni.jpa.tutorial.model;

import java.io.Serializable;

/**
 * Created by veda on 7/28/2015.
 */
public class TaskAssignmentId implements Serializable{
    private long employee;
    private long task;


    public TaskAssignmentId() {
    }

    public TaskAssignmentId(long employee, long task) {
        this.employee = employee;
        this.task = task;
    }

    public long getEmployee() {
        return employee;
    }

    public long getTask() {
        return task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskAssignmentId that = (TaskAssignmentId) o;

        if (employee != that.employee) return false;
        if (task != that.task) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (employee ^ (employee >>> 32));
        result = 31 * result + (int) (task ^ (task >>> 32));
        return result;
    }
}
