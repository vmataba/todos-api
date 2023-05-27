package com.tabaapps.todos.repositories;

import com.tabaapps.todos.models.Label;
import com.tabaapps.todos.models.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {

    List<Listing> findByLabel(Label label);
}
