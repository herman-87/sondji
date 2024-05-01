package com.h87.sondji.api;

import com.h87.sondji.service.NoteService;
import com.manageUser.model.CreateNoteDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.UUID;

class NoteResourcesTest extends ResourceTest {
    @MockBean
    private NoteService noteService;

    @Test
    @DisplayName(
            """
                    Given CreateNoteDTO is respected
                    When i create a new Note by passing the CreateNoteDTO
                    Then i should see that Service layer is calling to create a new Note with the CreateNoteDTO"""
    )
    void createNote() {
        CreateNoteDTO createNoteDTO = new CreateNoteDTO()
                .title("any title")
                .content("any content");
        UUID noteId = UUID.randomUUID();

        Mockito.when(noteService.createNote(createNoteDTO)).thenReturn(noteId);

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

        Assertions.assertThat(resultUnderTest).isEqualTo(noteId);
    }
}