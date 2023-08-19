package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name_of_role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName_of_role() {
        return name_of_role;
    }

    public void setName_of_role(String name_of_role) {
        this.name_of_role = name_of_role;
    }
}
