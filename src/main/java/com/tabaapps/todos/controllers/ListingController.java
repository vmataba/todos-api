package com.tabaapps.todos.controllers;

import com.tabaapps.todos.models.Label;
import com.tabaapps.todos.models.Listing;
import com.tabaapps.todos.repositories.LabelRepository;
import com.tabaapps.todos.repositories.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/listings")
public class ListingController {
    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ListingRepository listingRepository;

    @GetMapping(path = "/{labelId}")
    public List<Listing> viewUserListings(@PathVariable Long labelId) throws Exception {
        Optional<Label> optionalLabel = labelRepository.findById(labelId);
        if (optionalLabel.isEmpty()) {
            throw new Exception("Label is not found");
        }
        Label label = optionalLabel.get();
        return listingRepository.findByLabel(label);
    }

    @PostMapping(path = "/{labelId}")
    public Listing addNewListing(@PathVariable Long labelId, @RequestBody Listing listing) throws Exception {
        Optional<Label> optionalLabel = labelRepository.findById(labelId);
        if (optionalLabel.isEmpty()) {
            throw new Exception("Label is not found");
        }
        listing.setLabel(optionalLabel.get());
        return listingRepository.save(listing);
    }

    @PutMapping(path = "/{id}")
    public Listing updateListing(@PathVariable Long id, @RequestBody Listing listing) throws Exception {
        Optional<Listing> optionalListing = listingRepository.findById(id);
        if (optionalListing.isEmpty()) {
            throw new Exception("Listing is not found");
        }
        Listing savedListing = optionalListing.get();
        savedListing.setTitle(listing.getTitle());
        savedListing.setStatus(listing.getStatus());
        return savedListing;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteListing(@PathVariable Long id) throws Exception {
        Optional<Listing> optionalListing = listingRepository.findById(id);
        if (optionalListing.isEmpty()) {
            throw new Exception("Listing is not found");
        }
        listingRepository.delete(optionalListing.get());
        return ResponseEntity.noContent().build();
    }

}

