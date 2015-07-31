package ch.erni.jpa.tutorial.test;

import ch.erni.jpa.tutorial.dao.EmployeeDao;
import ch.erni.jpa.tutorial.enumType.PhoneType;
import ch.erni.jpa.tutorial.model.Address;
import ch.erni.jpa.tutorial.model.Department;
import ch.erni.jpa.tutorial.model.Device;
import ch.erni.jpa.tutorial.model.Employee;
import ch.erni.jpa.tutorial.util.ContextUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 * Created by veda on 7/27/2015.
 */

@RunWith(Parameterized.class)
public class AddElementsEmployeeTest {

    private String firstName;

    private String surname;

    private String dateOfBirth;

    private PhoneType type;

    private String phone;

    private String city;

    private String state;

    private EmployeeDao employeeDao;

    private UserTransaction userTransaction;

    @Parameterized.Parameters
    public static Collection<Object[]> parameters() {
        return Arrays.asList(new Object[][]{
                {"John", "Doe", "1970-01-01", PhoneType.Mobile, "0908478958", "Bratislava", "Slovakia"},
                {"Jack", "Doe", "1980-01-01", PhoneType.Home , "65847895", "London", "England"},
                {"Jeff", "Doe", "1990-01-01", PhoneType.Work, "789456789", "Lisboa", "Portugal"}
        });
    }

    public AddElementsEmployeeTest(String firstName, String surname, String dateOfBirth, PhoneType type, String phone, String city, String state) {
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.phone = phone;
        this.city = city;
        this.state = state;

    }

    @Before
    public void init() {
        try {
            Context context = EJBContainer.createEJBContainer().getContext();
            employeeDao = (EmployeeDao)  context.lookup("java:global/" + ContextUtil.getModuleName() + "/" + EmployeeDao.class.getSimpleName());
            userTransaction = (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testName() throws Exception {
        userTransaction.begin();
        Employee emp = new Employee(firstName, surname, dateOfBirth);
        Department dept = new Department("Development", 1 );
        emp.addDepartment(dept);
        emp.setAddress(new Address(this.state, this.city));
        emp.addDevice(new Device("PC", "2015-01-01"));

        emp.setPhoneNumbers(type, phone);
        employeeDao.save(emp);

        List<Employee> list = employeeDao.findByFirstName(firstName);
        System.out.println(emp);
        Assert.assertFalse(list.isEmpty());
        Assert.assertTrue(list.stream().anyMatch(e->e.getName().equals(firstName)));
        userTransaction.commit();

        List<Employee> empList = employeeDao.findAll();
        empList.stream().forEach(System.out :: println);
    }


}
