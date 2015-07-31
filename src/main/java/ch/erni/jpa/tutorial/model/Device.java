package ch.erni.jpa.tutorial.model;

import javax.persistence.*;

/**
 * Created by veda on 7/28/2015.
 */

@Entity
public class Device {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long devId;

    @Column
    private String name;

    @Column
    private String creation_date;

    public Device() {
    }

    public Device(String name, String creation_date) {
        this.name = name;
        this.creation_date = creation_date;
    }

    @Override
    public String toString() {
        return "Device{" +
                "dev_id=" + devId +
                ", name='" + name + '\'' +
                ", creation_date='" + creation_date + '\'' +
                '}';
    }
}
