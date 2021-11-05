package com.revature.models;

import javax.persistence.*;

//Entity is used to denote the CLass as an entity that HIbernate will manage
// and by extension will have a Databae Representation(i.e.a Table)

//Table is used to provide additional information aboe the Table itself.
@Entity
@Table(name = "departments")
public class Department {

    @Id // Denotes this field as the primary Key
    @Column(name="id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="department_name", nullable = false)
    private String name;

    public Department() {
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
