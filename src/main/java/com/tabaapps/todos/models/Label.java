package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Label {

    public Label(){
        setCreatedAt();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    @JsonProperty("created_at")
    protected LocalDateTime createdAt;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "label")
    private List<Listing> listings;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public User getUser() {
        return user;
    }


    public List<Listing> getListings() {
        return listings;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
