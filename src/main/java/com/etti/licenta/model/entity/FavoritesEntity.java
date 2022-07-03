package com.akhianand.springrolejwt.model.entity;

import com.akhianand.springrolejwt.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "favourites")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavoritesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfavourites")
    private long id;

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name="id_job", nullable=false)
    private JobsEntity jobsEntity;
}
