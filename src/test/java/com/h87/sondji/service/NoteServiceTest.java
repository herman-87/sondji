package com.h87.sondji.service;

import com.h87.sondji.domain.note.NoteRepository;
import com.manageUser.model.CreateNoteDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.UUID;

class NoteServiceTest {
    @InjectMocks
    private NoteService objectUnderTest;
    @Mock
    private NoteRepository noteRepository;

    @Test
    @DisplayName(
            """
                    Given
                    When I create a new Note with CreateNoteDTO
                    Then I should see that CreateNoteDTO is mapped to Note, saved and the generated ID was returned
                    """
    )
    void createNoteTest() {
        CreateNoteDTO createNoteDTO = mock(CreateNoteDTO.class);
        UUID createdNoteId = UUID.randomUUID();

        when()

        UUID resultUnderTest = objectUnderTest.createNote(createNoteDTO);
        Assertions.assertThat(resultUnderTest).isEqualTo(createdNoteId);
    }
}