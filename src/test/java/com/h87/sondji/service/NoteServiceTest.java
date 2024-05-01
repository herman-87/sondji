package com.h87.sondji.service;

import com.h87.sondji.commons.ExtractCode;
import com.h87.sondji.domain.note.Note;
import com.h87.sondji.domain.note.NoteRepository;
import com.h87.sondji.domain.note.NoteStatus;
import com.h87.sondji.service.mapper.NoteMapper;
import com.h87.sondji.utils.CreateNoteData;
import com.manageUser.model.CreateNoteDTO;
import com.manageUser.model.NoteDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
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
        assertThat(resultUnderTest).isEqualTo(createdNoteId);
    }

    @Test
    @DisplayName(
            """
                    Given
                    When i get all published notes
                    Then i should see that all published notes was fetched
                    """
    )
    void getAllPublishedNotes_1_test() {
        //Given
        String extractCode = ExtractCode.extractCode1;
        NoteDTO noteDTO1 = mock(NoteDTO.class);
        NoteDTO noteDTO2 = mock(NoteDTO.class);
        Note note1 = mock(Note.class);
        Note note2 = mock(Note.class);

        when(noteRepository.findAllByStatus(NoteStatus.PUBLISHED)).thenReturn(List.of(note1, note2));
        when(noteMapper.toDTO(note1, extractCode)).thenReturn(noteDTO1);
        when(noteMapper.toDTO(note2, extractCode)).thenReturn(noteDTO2);

        //When
        List<NoteDTO> resultUnderTest = objectUnderTest.getAllPublishedNotes(extractCode);

        //Then
        assertThat(resultUnderTest).containsExactly(noteDTO1, noteDTO2);
    }
}