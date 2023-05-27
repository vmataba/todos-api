package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.tabaapps.todos.security.Security;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class User {

    User() {
        this.setCreatedAt();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @JsonProperty("first_name")
    @Column(nullable = false)
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @JsonProperty("created_at")
    protected LocalDateTime createdAt;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Label> labels;


    public Long getId() {
        return id;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = Security.hashPassword(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<Label> getLabels() {
        return labels;
    }

}
