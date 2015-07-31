package ch.erni.jpa.tutorial.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by veda on 7/28/2015.
 */
@Entity
public class Task {

    @Id
    @Column(name = "TASK_ID")
    @GeneratedValue
    private long id;

    @Column
    private String name;

/*    @OneToOne(mappedBy = "EMP_ID")
    private Employee employee;*/

    @OneToMany(mappedBy="employee")
    private List<TaskAssigment> assignments = new ArrayList<>();

    public Task() {
    }

    public long getId() {
        return id;
    }

    public Task(String name) {
        this.name = name;
    }

    public void addAssigment(TaskAssigment taskAssigment) {
        assignments.add(taskAssigment);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", assignments=" + assignments +
                '}';
    }
}
