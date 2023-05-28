package com.tabaapps.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Task {

    public static final int STATUS_PENDING = 0;

    public static final int STATUS_COMPLETED = 1;

    Task() {
        setCreatedAt();
        setStatus(STATUS_PENDING);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JsonIgnore
    private Listing listing;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY,namespace = "listing_id")
    private Long listingId;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public Listing getListing() {
        return listing;
    }

    public void setListing(Listing listing) {
        this.listing = listing;
    }

    public Long getListingId() {
        return listing.getId();
    }
}
