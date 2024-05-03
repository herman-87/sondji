package com.h87.sondji.api;

import static com.h87.sondji.commons.ExtractCode.EXTRACT_CODE;
import static com.h87.sondji.commons.ExtractCode.EXTRACT_CODE_1;

import com.h87.sondji.commons.ExtractCode;
import com.h87.sondji.service.NoteService;
import com.manageUser.model.CreateNoteDTO;
import com.manageUser.model.NoteDTO;
import static com.manageUser.model.NoteStatusDTO.PUBLISHED;
import com.manageUser.model.UpdateNoteDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;
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
        //Given
        CreateNoteDTO createNoteDTO = new CreateNoteDTO()
                .title("any title")
                .content("any content");
        UUID noteId = UUID.randomUUID();

        Mockito.when(noteService.createNote(createNoteDTO)).thenReturn(noteId);

        //When
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

        //Then
        assertThat(resultUnderTest).isEqualTo(noteId);
    }

    @Test
    @DisplayName(
            """
                    Given
                    When I get all published note
                    Then all published notes will then be returned.
                    """
    )
    void getAllPublishedNotes_1_test() {
        //Given
        NoteDTO noteDTO1 = mock(NoteDTO.class);
        NoteDTO noteDTO2 = mock(NoteDTO.class);
        Mockito.when(noteService.getAllPublishedNotes(EXTRACT_CODE_1, PUBLISHED)).thenReturn(List.of(noteDTO1, noteDTO2));

        //When
        List<NoteDTO> resultUnderTest = webTestClient
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/note")
                                .queryParam(EXTRACT_CODE, EXTRACT_CODE_1)
                                .queryParam("noteStatus", PUBLISHED)
                                .build()
                )
                .exchange()
                .expectBodyList(NoteDTO.class)
                .returnResult()
                .getResponseBody();

        //Then
        assertThat(resultUnderTest).containsExactly(noteDTO1, noteDTO2);
    }

    @Test
    void updateNoteByIdTest() {
        UUID noteId = UUID.randomUUID();
        UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO()
                .title("new title")
                .content("new content");

        //When
        webTestClient
                .put()
                .uri(
                        uriBuilder -> uriBuilder.path("/note/{noteId}")
                                .build(Map.of("noteId", noteId))
                )
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateNoteDTO)
                .exchange()
                .expectStatus()
                .isNoContent();

        //Then
        verify(noteService).updateNoteById(noteId, updateNoteDTO);
    }

    @Test
    void getNoteById_1_test() {
        //Given
        UUID noteId = UUID.randomUUID();
        NoteDTO noteDTO = mock(NoteDTO.class);

        when(noteService.getNoteById(noteId, EXTRACT_CODE_1)).thenReturn(noteDTO);

        //When
        NoteDTO resultUnderTest = webTestClient
                .get()
                .uri(
                        uriBuilder -> uriBuilder
                                .path("/note/{noteId}")
                                .queryParam(EXTRACT_CODE, EXTRACT_CODE_1)
                                .build(Map.of("noteId", noteId))
                )
                .exchange()
                .expectStatus().isOk()
                .expectBody(NoteDTO.class)
                .returnResult()
                .getResponseBody();

        //Then
        assertThat(resultUnderTest).isEqualTo(noteDTO);
    }
}