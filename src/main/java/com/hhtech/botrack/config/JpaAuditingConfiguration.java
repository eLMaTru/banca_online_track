package com.hhtech.botrack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * The type Jpa auditing configuration.
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    /**
     * Auditor provider auditor aware.
     *
     * @return the auditor aware
     */
    @Bean
    public AuditorAware<String> auditorProvider() {

        /*
          if you are using spring security, you can get the currently logged username with following code segment.
                 SecurityContextHolder.getContext().getAuthentication().getName();
         */
        return ()-> Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());

    }

}
