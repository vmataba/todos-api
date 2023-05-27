package com.tabaapps.todos.repositories;

import com.tabaapps.todos.models.Label;
import com.tabaapps.todos.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Long> {

    List<Label> findByUser(User user);
}
