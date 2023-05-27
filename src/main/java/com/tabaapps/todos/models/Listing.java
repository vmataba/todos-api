package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Listing{

    public static final int STATUS_ARCHIVED = 0;

    public static final int STATUS_ACTIVE = 1;

    Listing(){
        setCreatedAt();
        this.setStatus(STATUS_ACTIVE);
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JsonIgnore
    private Label label;

    @OneToMany(mappedBy = "listing")
    private List<Task> tasks;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }


    @Column(nullable = false)
    @JsonProperty("created_at")
    protected LocalDateTime createdAt;


    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public Label getLabel() {
        return label;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
