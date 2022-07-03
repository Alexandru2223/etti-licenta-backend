package com.akhianand.springrolejwt.model.entity;

import com.akhianand.springrolejwt.model.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idreviews")
    private long id;

    @Column(name = "message")
    private String message;

    @Column(name = "rating")
    private int rating;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name="id_user", nullable=false)
    private UserEntity userEntity;
}
