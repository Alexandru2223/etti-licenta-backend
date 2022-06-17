package com.akhianand.springrolejwt.controller;

import com.akhianand.springrolejwt.mapper.ReviewEntityToReviewDtoMapper;
import com.akhianand.springrolejwt.model.UserEntity;
import com.akhianand.springrolejwt.model.dto.JobsDTO;
import com.akhianand.springrolejwt.model.dto.ReviewsDTO;
import com.akhianand.springrolejwt.model.entity.ReviewsEntity;
import com.akhianand.springrolejwt.repository.ReviewsRepository;
import com.akhianand.springrolejwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class ReviewsController {

    private final ReviewsRepository repository;

    private final UserRepository userRepository;

    private final ReviewEntityToReviewDtoMapper reviewEntityToReviewDtoMapper;

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/reviews"})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewsEntity> getAllReviews() {
        return repository.findAll();
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping({"/reviews/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody ReviewsEntity reviewsEntity, @PathVariable String user) {
        UserEntity userEntity = userRepository.findByUsername(user);
        reviewsEntity.setUserEntity(userEntity);
        repository.save(reviewsEntity);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/reviews/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewsDTO> getReviewsByAuthor(@PathVariable String user) {
        return repository.getReviewsEntitiesByAuthor(user).stream().map(reviewEntityToReviewDtoMapper::convert).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/reviews/user/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public List<ReviewsDTO> getReviewsForUser(@PathVariable String user) {
        return repository.getReviewsForUser(user).stream().map(reviewEntityToReviewDtoMapper::convert).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/reviews/count/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public long getCountReviewsByAuthor(@PathVariable String user) {
        return repository.getReviewsEntitiesByAuthor(user).stream().count();
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/reviews/user/count/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public long getCountReviewsForUser(@PathVariable String user) {
        return repository.getReviewsForUser(user).stream().count();
    }
}
