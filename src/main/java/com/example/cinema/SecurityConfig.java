package com.example.cinema;

import com.example.cinema.User.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            com.example.cinema.User.User user = userRepository.getUserByName(username);
            if(user==null){
                throw new UsernameNotFoundException("User not found.");
            }
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), authorities);
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**","/resources/**",
                        "/cinema/home", "/ticket/home", "/seance/home",
                        "/registration").permitAll() // Dostęp do strony głównej i rejestracji dla wszystkich
                //.anyRequest().hasRole("ADMIN")
                //.antMatchers("/ticket/home","/seance/home","/cinema/home").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/ticket/*","/seance/*","/cinema/*").hasRole("ADMIN")
                .and()
                .formLogin().permitAll()
                .defaultSuccessUrl("/cinema/home")
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/cinema/home")
                .and()
                .csrf().disable(); // Wyłączamy zabezpieczenie CSRF w celu prostszego testowania
    }
}