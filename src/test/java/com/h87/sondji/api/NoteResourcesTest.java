package com.h87.sondji.api;

import com.manageUser.model.CreateNoteDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
@AutoConfigureWebTestClient
class NoteResourcesTest {
    @Autowired
    private WebTestClient webTestClient;


    @Test
    @DisplayName(
            """
                    Given"""
    )
    void createNote() {
        CreateNoteDTO createNoteDTO = new CreateNoteDTO()
                .title("any")
                .content("content");

        UUID resultUnderTest = webTestClient
                .post()
                .uri("/note")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(createNoteDTO)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(UUID.class)
                .returnResult()
                .getResponseBody();

    }
}