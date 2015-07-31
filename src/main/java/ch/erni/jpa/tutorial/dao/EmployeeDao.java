package ch.erni.jpa.tutorial.dao;

import ch.erni.jpa.tutorial.model.Employee;
import ch.erni.jpa.tutorial.model.Task;
import ch.erni.jpa.tutorial.model.TaskAssigment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by veda on 7/27/2015.
 */

@Stateless
public class EmployeeDao {

    @PersistenceContext(name = "openjpa")
    private EntityManager entityManager;

    public void save(Employee empl) {
        entityManager.persist(empl);
    }

    public List<Employee> findAll() {
        return entityManager.createQuery("select e from Employee e", Employee.class)
                .getResultList();
    }

    public List<Employee> findByFirstName(String firstName) {
        return entityManager.createQuery("select e from Employee e where e.name = :firstName", Employee.class)
                .setParameter("firstName", firstName)
                .getResultList();
    }

    public void saveAssignTasks(Employee empl){
        entityManager.persist(empl);
    }

}
