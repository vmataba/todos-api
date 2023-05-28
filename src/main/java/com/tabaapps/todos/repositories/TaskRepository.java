package com.tabaapps.todos.repositories;

import com.tabaapps.todos.models.Label;
import com.tabaapps.todos.models.Listing;
import com.tabaapps.todos.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByListing(Listing listing);

    List<Task> findByListingLabel(Label label);
}
