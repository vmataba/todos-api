package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
public class Label extends BaseModel {

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

}
