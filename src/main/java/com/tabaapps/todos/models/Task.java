package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Task extends BaseModel{

    public static final int STATUS_PENDING = 0;

    public static final int STATUS_COMPLETED = 1;

    Task() {
        super();
        setStatus(STATUS_PENDING);
    }

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JsonIgnore
    private Listing listing;

    public Long getListingId(){
        return this.listing.getId();
    }

}
