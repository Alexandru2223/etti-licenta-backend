package com.akhianand.springrolejwt;

import com.akhianand.springrolejwt.model.entity.CountJobsEntity;
import com.akhianand.springrolejwt.service.CountJobsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication

public class SpringRoleJwtApplication {
	private static CountJobsService countJobsService;


	public SpringRoleJwtApplication(CountJobsService countJobsService) {

		SpringRoleJwtApplication.countJobsService = countJobsService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringRoleJwtApplication.class, args);
		SpringRoleJwtApplication springRoleJwtApplication = new SpringRoleJwtApplication(countJobsService);

		Runnable helloRunnable = new Runnable() {
			public void run() {
				countJobsService.save(LocalDateTime.now());
			}
		};

		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(helloRunnable, 0, 24, TimeUnit.HOURS);
	}
}
