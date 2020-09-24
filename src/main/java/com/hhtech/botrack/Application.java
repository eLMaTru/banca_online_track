package com.hhtech.botrack;

import javax.annotation.PostConstruct;

import com.hhtech.botrack.model.Role;
import com.hhtech.botrack.model.Status;
import com.hhtech.botrack.model.User;
import com.hhtech.botrack.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@Data
@EnableJpaAuditing(auditorAwareRef = "customAuditorAware")
@Log4j2
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
			user.setPassword("ownerpass");
			user.setPasswordConfirm("owner");
			Role role = Role.Type.SUPER_USER.toRole();
			role.setDescription("System Owner");
			user.setRole(role);
			Status status = Status.Type.ENABLED.toStatus();
			status.setName("ENABLED");
			status.setDescription("Item is enabled");
			user.setStatus(status);

			try {
				userService.save(user);
			} catch (Exception e) {
				log.warn("*** No se pudo gualdar el Usuario inicial \"{}\" porque el data.sql aun no se ha ejecutado. "
						+ "Este usuario se creara la proxima vez que corra la App ***", user.getUsername());
			}

		}
	}
}
