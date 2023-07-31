package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public abstract class BaseModel {

    BaseModel(){
        this.setCreatedAt(LocalDateTime.now());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("created_by")
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JsonProperty("updated_by")
    @JoinColumn(name = "updated_by")
    private User updatedBy;
}
