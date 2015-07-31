package ch.erni.jpa.tutorial.model;

import ch.erni.jpa.tutorial.enumType.PhoneType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.*;

/**
 * Created by veda on 7/27/2015.
 */

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EMP_ID")
    private long id;

    @Column(name = "EMP_NAME")
    private String name;

    @Column(name = "EMP_SURNAME")
    private String surName;

    @Column(name = "EMP_DOB")
    private String dateOfBirth;

    @OneToMany(mappedBy = "works", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Department> dept =  new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.ORDINAL)
    @MapKeyColumn(name = "PHONE_TYPE")
    @Column(name="PHONE_NUM")
    private Map<PhoneType, String> phoneNumbers = new TreeMap<>();

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private Collection<TaskAssigment> assignTasks =  new ArrayList<>();


    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "EMP_DEVICE",
        joinColumns ={ @JoinColumn(name="EMP_ID", referencedColumnName="EMP_ID") },
        inverseJoinColumns={ @JoinColumn(name="DEVICE_ID", referencedColumnName="devId", unique=true) }
    )
    private List<Device> devices = new ArrayList<>();

    @Version
    private Integer version;



    public Employee() {
    }

    public Employee(String name, String surName, String dateOfBirth) {
        this.name = name;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void addDepartment(Department dept) {
        this.dept.add(dept);
    }

    public Map<PhoneType, String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(PhoneType type, String number) {
        phoneNumbers.put(type, number);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void addDevice(Device device){
        this.devices.add(device);
    }

    public void addAssigmentTask(TaskAssigment taskAssigment){
        assignTasks.add(taskAssigment);
    }

    public Collection<TaskAssigment> getAssignTasks() {
        return assignTasks;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public List<Department> getDept() {
        return dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", dept=" + dept +
                ", task=" + assignTasks +
                ", phoneNumbers=" + phoneNumbers +
                ", version=" + version +
                '}';
    }



}
