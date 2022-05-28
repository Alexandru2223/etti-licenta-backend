package com.licenta.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class UserDTO {

	private long id;

	private String username;

	private String password;

	private List<JobsDTO> jobsDTOS;
}
