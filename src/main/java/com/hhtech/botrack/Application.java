package com.hhtech.botrack;

import javax.annotation.PostConstruct;

import com.hhtech.botrack.model.Role;
import com.hhtech.botrack.model.User;
import com.hhtech.botrack.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Data;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@Data // Lombok: adds getters and setters
@EnableJpaAuditing(auditorAwareRef="customAuditorAware")
public class Application {

	@Autowired
	UserService userService;

	@PostConstruct
	public void initApplication() {
		createFirstUser();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}

	private void createFirstUser() {

		if (!userService.userNameExist("owner")) {
			User user = new User();
			user.setUsername("owner");
			user.setEmail("owner@test.com");
			user.setPassword("owner");
			user.setPasswordConfirm("owner");
			user.setRole(Role.Type.OWNER.toRole());
		

			userService.save(user);
		}
	}

}
