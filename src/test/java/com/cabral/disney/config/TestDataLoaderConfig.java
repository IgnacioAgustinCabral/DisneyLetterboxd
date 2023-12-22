package com.cabral.disney.config;

import com.cabral.disney.models.Movie;
import com.cabral.disney.models.Role;
import com.cabral.disney.models.User;
import com.cabral.disney.repository.MovieRepository;
import com.cabral.disney.repository.RoleRepository;
import com.cabral.disney.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class TestDataLoaderConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    private final MovieRepository movieRepository;

    @Autowired
    public TestDataLoaderConfig(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, MovieRepository movieRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.movieRepository = movieRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        createRoles();

        createUsers();

        createMovie();
    }

    private void createRoles() {
        // Check if roles already exist
        if (roleRepository.count() == 0) {
            Role roleUser = Role.builder().name("ROLE_USER").build();
            Role roleAdmin = Role.builder().name("ROLE_ADMIN").build();

            roleRepository.saveAll(Arrays.asList(roleUser, roleAdmin));
        }
    }

    private void createUsers() {
        // Check if users already exist
        if (userRepository.count() == 0) {
            Role roleUser = roleRepository.findByName("ROLE_USER").get();
            Role roleAdmin = roleRepository.findByName("ROLE_ADMIN").get();

            User user1 = User.builder()
                    .username("user1")
                    .password(passwordEncoder.encode("password"))
                    .email("email1@gmail.com")
                    .roles(Arrays.asList(roleUser))
                    .build();

            User user2 = User.builder()
                    .username("admin1")
                    .password(passwordEncoder.encode("adminpass"))
                    .email("email2@gmail.com")
                    .roles(Arrays.asList(roleAdmin))
                    .build();

            userRepository.saveAll(Arrays.asList(user1, user2));
        }
    }

    private void createMovie() {
        Movie movie = Movie.builder().title("Aladin").synopsis("synopsis").creationDate(LocalDate.of(1994, 2, 2)).build();
        movieRepository.save(movie);
    }
}
