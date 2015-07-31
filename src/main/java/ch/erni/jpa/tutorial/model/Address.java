package ch.erni.jpa.tutorial.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by veda on 7/28/2015.
 */

@Embeddable
public class Address {

    @Column
    private String city;

    @Column
    private String state;

    public Address() {
    }

    public Address(String state, String city) {
        this.state = state;
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
