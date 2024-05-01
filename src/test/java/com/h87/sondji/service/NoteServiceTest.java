package com.h87.sondji.service;

import com.h87.sondji.domain.note.Note;
import com.h87.sondji.domain.note.NoteRepository;
import com.h87.sondji.service.mapper.NoteMapper;
import com.h87.sondji.utils.CreateNoteData;
import com.manageUser.model.CreateNoteDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    @InjectMocks
    private NoteService objectUnderTest;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteMapper noteMapper;

    @Test
    @DisplayName(
            """
                    Given
                    When I create a new Note with CreateNoteDTO
                    Then I should see that CreateNoteDTO is mapped to Note, saved and the generated ID was returned
                    """
    )
    void createNoteTest() {
        //Given
        CreateNoteDTO createNoteDTO = mock(CreateNoteDTO.class);
        UUID createdNoteId = UUID.randomUUID();
        var createNoteData = CreateNoteData.builder().build();
        var note = mock(Note.class);
        mockStatic(Note.class);

        when(noteMapper.fromCreateNoteDTO(createNoteDTO)).thenReturn(createNoteData);
        when(Note.createNote(createNoteData, noteRepository)).thenReturn(note);
        when(note.getId()).thenReturn(createdNoteId);

        //When
        UUID resultUnderTest = objectUnderTest.createNote(createNoteDTO);

        //Then
        Assertions.assertThat(resultUnderTest).isEqualTo(createdNoteId);
    }
}