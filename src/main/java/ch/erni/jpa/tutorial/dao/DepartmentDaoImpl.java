package ch.erni.jpa.tutorial.dao;

import ch.erni.jpa.tutorial.model.Department;
import ch.erni.jpa.tutorial.model.Employee;
import org.springframework.stereotype.Repository;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by veda on 7/30/2015.
 */
@Repository
public class DepartmentDaoImpl {

    @PersistenceContext(name = "openjpa")
    private EntityManager entityManager;

    public void save(Department dept) {
        entityManager.persist(dept);
    }
}
