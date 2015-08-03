package ch.erni.jpa.tutorial.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by veda on 7/27/2015.
 */

@Entity
public class Department implements Serializable{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long deptId;

    @Column(name = "DEPT_NAME")
    private String deptName;

    @Column
    int stage;

    @ManyToOne
    @JoinColumn(name="WORKS_ID")
    private Employee works;

    public Department(){
    }

    public Department(String deptName, int stage) {
        this.deptName = deptName;
        this.stage = stage;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public void assignWork(Employee emp){
        works = emp;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", stage=" + stage +
                '}';
    }
}
