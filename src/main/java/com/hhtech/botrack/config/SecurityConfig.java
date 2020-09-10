package com.hhtech.botrack.config;

import com.hhtech.botrack.security.UserAuthenticationFailureHandler;
import com.hhtech.botrack.security.UserAuthenticationSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/app/**/*.{js,html}")
                .antMatchers("/bower_components/**").antMatchers("/i18n/**").antMatchers("/content/**")
                .antMatchers("/swagger-ui/index.html").antMatchers("/test/**");
        web.ignoring().antMatchers("/resources/**", "/i18n/**", "/static/**", "/bancaonlinetrack/**", "/assets/**",
                "/css/**", "/js/**", "/images/**");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/login").permitAll().antMatchers("/owner/**")
                .hasAuthority(AuthoritiesConstants.OWNER).antMatchers("/admin/**")
                .hasAnyAuthority(AuthoritiesConstants.ADMIN, AuthoritiesConstants.OWNER).antMatchers("/supervisor/**")
                .hasAnyAuthority(AuthoritiesConstants.SUPERVISOR, AuthoritiesConstants.ADMIN,
                        AuthoritiesConstants.OWNER)
                .antMatchers("/user/**")
                .hasAnyAuthority(AuthoritiesConstants.USER, AuthoritiesConstants.SUPERVISOR, AuthoritiesConstants.ADMIN,
                        AuthoritiesConstants.OWNER)
                .antMatchers("/api/authenticate").permitAll().antMatchers("/api/account/reset_password/init")
                .permitAll().antMatchers("/api/account/reset_password/finish").permitAll().antMatchers("/api/**")
                .permitAll()// .authenticated()
                .antMatchers("/management/**").hasAuthority(AuthoritiesConstants.ADMIN).antMatchers("/user/**")
                .hasAuthority(AuthoritiesConstants.ADMIN).and().formLogin().loginPage("/login")
                .loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/home").failureUrl("/signIn/?error")
                .failureHandler(userAuthenticationFailureHandler()).successHandler(userAuthenticationSuccessHandler())
                .and().logout().logoutUrl("/logout/").logoutSuccessUrl("/home").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true).and().sessionManagement().sessionFixation().migrateSession()
                .invalidSessionUrl("/home");
        super.configure(http);

    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Bean
    public UserAuthenticationSuccessHandler userAuthenticationSuccessHandler() {
        return new UserAuthenticationSuccessHandler();
    }

    @Bean
    public UserAuthenticationFailureHandler userAuthenticationFailureHandler() {
        return new UserAuthenticationFailureHandler();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
