package com.sethabrams.travelapp.domain;

import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    @JoinColumn(name = "user")
    @MapsId
    private @NonNull User user;

    @Column(name = "User Rating")
    private Integer userRating;

    public UserProfile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }
}
