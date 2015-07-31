package ch.erni.jpa.tutorial.model;

import javax.persistence.*;

/**
 * Created by veda on 7/31/2015.
 */
@Entity
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;
}
