package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Setter
@Getter
public class Label extends BaseModel {

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "label")
    private List<Listing> listings;

}
