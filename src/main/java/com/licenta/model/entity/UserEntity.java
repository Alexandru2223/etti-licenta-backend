package com.licenta.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idusers")
	private long id;
	@Column
	private String username;
	@Column
	@JsonIgnore
	private String password;

	@OneToMany(mappedBy="userEntity")
	private List<JobsEntity> jobsEntities;

}
