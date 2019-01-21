package com.sethabrams.travelapp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @Column(name = "User_Email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    @NotEmpty
    private UserProfile userProfile;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
