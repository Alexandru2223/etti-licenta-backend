package com.akhianand.springrolejwt.controller;

import com.akhianand.springrolejwt.mapper.FavoritesEntityToFavoritesDTOMapper;
import com.akhianand.springrolejwt.mapper.JobsEntityToJobsDTO;
import com.akhianand.springrolejwt.model.UserEntity;
import com.akhianand.springrolejwt.model.dto.FavoritesDTO;
import com.akhianand.springrolejwt.model.dto.JobsDTO;
import com.akhianand.springrolejwt.model.entity.FavoritesEntity;
import com.akhianand.springrolejwt.model.entity.JobsEntity;
import com.akhianand.springrolejwt.repository.FavoritesRepository;
import com.akhianand.springrolejwt.repository.JobsRepository;
import com.akhianand.springrolejwt.repository.UserRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class FavoritesController {

    private final FavoritesRepository repository;

    private final UserRepository userRepository;

    private final JobsRepository jobsRepository;

    private final FavoritesEntityToFavoritesDTOMapper favoritesEntityToFavoritesDTOMapper;

    private final JobsEntityToJobsDTO jobsEntityToJobsDTO;

    @PreAuthorize("hasRole('USER')")
    @PostMapping({"/favourites/add"})
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestParam Long idJob, @RequestParam String user) {
        FavoritesEntity favoritesEntity = new FavoritesEntity();
        UserEntity userEntity = userRepository.findByUsername(user);
        favoritesEntity.setUserEntity(userEntity);
        JobsEntity jobsEntity = jobsRepository.findById(idJob).get();
        favoritesEntity.setJobsEntity(jobsEntity);
        repository.save(favoritesEntity);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/favorites/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public List<FavoritesDTO> getAllFavoritesForUser(@PathVariable String user){
        return repository.getAllByUser(user).stream().map(favoritesEntityToFavoritesDTOMapper::convert).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/favorites/saved/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> getAllJobsSavedForUser(@PathVariable String user){
        List<JobsEntity> collect = repository.getAllByUser(user).stream().map(favoritesEntity -> favoritesEntity.getJobsEntity()).collect(Collectors.toList());
        List<JobsDTO> collect1 = collect.stream().map(jobsEntityToJobsDTO::convert).collect(Collectors.toList());
        return collect1;
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping({"/favorites/saved/count/{user}"})
    @ResponseStatus(HttpStatus.OK)
    public long getCountFavorites(@PathVariable String user){
        List<JobsEntity> collect = repository.getAllByUser(user).stream().map(favoritesEntity -> favoritesEntity.getJobsEntity()).collect(Collectors.toList());
        return collect.stream().map(jobsEntityToJobsDTO::convert).collect(Collectors.toList()).stream().count();
    }


    @PreAuthorize("hasRole('USER')")
    @DeleteMapping({"/favorites/delete"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteFromFavorites(@RequestParam Long idJob, @RequestParam String user) {
        FavoritesEntity favoritesEntity = repository.getByJobId(idJob);
        if (favoritesEntity.getUserEntity().getUsername().equalsIgnoreCase(user)){
            repository.delete(favoritesEntity);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping({"/favorites/delete/saved"})
    @ResponseStatus(HttpStatus.OK)
    public List<JobsDTO> deleteFromFavoritesAndReturn(@RequestParam Long idJob, @RequestParam String user) {
        FavoritesEntity favoritesEntity = repository.getByJobId(idJob);
        if (favoritesEntity.getUserEntity().getUsername().equalsIgnoreCase(user)){
            repository.delete(favoritesEntity);
        }
        return getAllJobsSavedForUser(user);
    }

}
