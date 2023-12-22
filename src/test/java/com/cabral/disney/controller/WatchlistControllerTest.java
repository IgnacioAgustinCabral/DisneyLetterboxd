package com.cabral.disney.controller;

import com.cabral.disney.models.User;
import com.cabral.disney.repository.UserRepository;
import com.cabral.disney.service.impl.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class WatchlistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setup() {
        // Create test users if they don't exist
        user = getUser("user1");
    }

    private User getUser(String username) {
        return userRepository.findByUsername(username).get();
    }


    @Test
    public void userWithROLE_USERAddsMovieToWatchlistEndpointShouldReturn200_OK() throws Exception {
        String token = jwtService.getToken(user);

        ResultActions result = mockMvc.perform(post("/watchlist/{movieId}/add-to-watchlist", 1L)
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());
    }
}

