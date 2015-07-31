package ch.erni.jpa.tutorial.test;

import ch.erni.jpa.tutorial.dao.EmployeeDao;
import ch.erni.jpa.tutorial.model.Department;
import ch.erni.jpa.tutorial.model.Employee;
import ch.erni.jpa.tutorial.model.Task;
import ch.erni.jpa.tutorial.model.TaskAssigment;
import ch.erni.jpa.tutorial.util.ContextUtil;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Before;
import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;
import java.util.Date;
import java.util.Optional;

/**
 * Created by veda on 7/28/2015.
 */
public class AssignTaskTest {

    private EmployeeDao employeeDao;

    private UserTransaction userTransaction;

    @Before
    public void init(){
        try {
            Context context = EJBContainer.createEJBContainer().getContext();
            employeeDao = (EmployeeDao)  context.lookup("java:global/" + ContextUtil.getModuleName() + "/" + EmployeeDao.class.getSimpleName());
            userTransaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAssignTaskToEmpolyee() throws Exception{
        userTransaction.begin();
        Task task = new Task("Create project");
        Employee employee = new Employee("Jozo", "Fero", "1987-02-01");
        TaskAssigment taskAssigment = new TaskAssigment(employee, task, new Date());
        Department dept = new Department("First floor", 2);
        dept.assignWork(employee);
        task.addAssigment(taskAssigment);
        employee.addAssigmentTask(taskAssigment);
        employee.addDepartment(dept);

        employeeDao.saveAssignTasks(employee);

        userTransaction.commit();

        Optional<Employee> emp = employeeDao.findByFirstName("Jozo").stream().findFirst();
        emp.get().getDept().stream().forEach(System.out :: println);
        emp.get().getAssignTasks().stream().forEach(System.out :: println);

        employeeDao.findAll().stream().forEach(System.out :: println);

    }

}
