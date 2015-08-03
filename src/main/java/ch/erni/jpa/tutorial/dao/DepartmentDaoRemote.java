package ch.erni.jpa.tutorial.dao;

import ch.erni.jpa.tutorial.model.Department;

import javax.ejb.Remote;

/**
 * Created by veda on 8/3/2015.
 */
@Remote
public interface DepartmentDaoRemote {

    public void save(Department dept);
}
