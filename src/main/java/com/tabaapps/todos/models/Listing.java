package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Listing extends BaseModel{

    public static final int STATUS_ARCHIVED = 0;

    public static final int STATUS_ACTIVE = 1;

    Listing(){
        super();
        this.setStatus(STATUS_ACTIVE);
    }

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JsonIgnore
    private Label label;

    @OneToMany(mappedBy = "listing")
    private List<Task> tasks;

}
