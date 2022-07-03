package com.akhianand.springrolejwt.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "count_jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountJobsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "number")
    private long number;

    @Column(name = "date")
    private LocalDateTime date;
}
