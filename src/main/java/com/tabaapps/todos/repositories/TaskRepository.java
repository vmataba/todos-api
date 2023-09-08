package com.tabaapps.todos.repositories;

import com.tabaapps.todos.models.Label;
import com.tabaapps.todos.models.Listing;
import com.tabaapps.todos.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByListing(Listing listing);

    @Query("select t from Task t join Listing l on t.listing = l where l.label = :label")
    List<Task> findByLabel(Label label);
}
