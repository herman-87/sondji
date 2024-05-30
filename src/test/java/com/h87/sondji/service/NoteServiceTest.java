package com.h87.sondji.service;

import com.h87.sondji.commons.ExtractCode;
import com.h87.sondji.domain.note.Note;
import com.h87.sondji.domain.note.NoteRepository;
import com.h87.sondji.domain.note.NoteStatus;
import com.h87.sondji.domain.tag.Tag;
import com.h87.sondji.domain.tag.TagRepository;
import com.h87.sondji.exceptions.ResourcesNotFoundException;
import com.h87.sondji.service.mapper.NoteMapper;
import com.h87.sondji.utils.CreateNoteData;
import com.h87.sondji.utils.SondjiErrorCode;
import com.h87.sondji.utils.UpdateNoteData;
import com.manageUser.model.CreateNoteDTO;
import com.manageUser.model.NoteDTO;
import com.manageUser.model.NoteStatusDTO;
import com.manageUser.model.UpdateNoteDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {
    @InjectMocks
    private NoteService objectUnderTest;
    @Mock
    private NoteRepository noteRepository;
    @Mock
    private NoteMapper noteMapper;
    @Mock
    private TagRepository tagRepository;

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
        String extractCode = ExtractCode.EXTRACT_CODE_1;
        NoteDTO noteDTO1 = mock(NoteDTO.class);
        NoteDTO noteDTO2 = mock(NoteDTO.class);
        Note note1 = mock(Note.class);
        Note note2 = mock(Note.class);

        when(noteMapper.fromNoteStatusDTO(NoteStatusDTO.PUBLISHED)).thenReturn(NoteStatus.PUBLISHED);
        when(noteRepository.findAllByStatus(NoteStatus.PUBLISHED)).thenReturn(List.of(note1, note2));
        when(noteMapper.toDTO(note1, extractCode)).thenReturn(noteDTO1);
        when(noteMapper.toDTO(note2, extractCode)).thenReturn(noteDTO2);

        //When
        List<NoteDTO> resultUnderTest = objectUnderTest.getAllPublishedNotes(extractCode, NoteStatusDTO.PUBLISHED);

        //Then
        assertThat(resultUnderTest).containsExactly(noteDTO1, noteDTO2);
    }

    @Test
    @DisplayName(
            """
                    Given Note to update exist by id
                    When i update the Note with id
                    Then i should see that the Note is updated with the passing data
                    """
    )
    void updateNoteById_1_test1() {
        //Given
        UUID noteId = UUID.randomUUID();
        UpdateNoteDTO updateNoteDTO = mock(UpdateNoteDTO.class);
        UpdateNoteData updateNoteData = UpdateNoteData.builder().build();
        var note = mock(Note.class);

        when(noteMapper.fromUpdateNoteDTO(updateNoteDTO)).thenReturn(updateNoteData);
        when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));

        //When
        objectUnderTest.updateNoteById(noteId, updateNoteDTO);

        //Then
        verify(note).update(updateNoteData, noteRepository);
    }

    @Test
    @DisplayName(
            """
                    Given Note to update does not exist
                    When i try to update the Note by id
                    Then a ResourcesNotFoundException was throw with error code 'Note Not Found'
                    """
    )
    void updateNoteByIdTes() {
        //Given
        UUID noteId = UUID.randomUUID();
        UpdateNoteDTO updateNoteDTO = mock(UpdateNoteDTO.class);
        UpdateNoteData updateNoteData = UpdateNoteData.builder().build();

        when(noteMapper.fromUpdateNoteDTO(updateNoteDTO)).thenReturn(updateNoteData);
        when(noteRepository.findById(noteId)).thenReturn(Optional.empty());

        //When
        assertThatThrownBy(() -> objectUnderTest.updateNoteById(noteId, updateNoteDTO))
                .isInstanceOf(ResourcesNotFoundException.class)
                .hasMessage(SondjiErrorCode.NOTE_NOT_FOUND.getValue());
    }

    @Test
    void getNoteById_1_test() {
        //Given
        UUID noteId = UUID.randomUUID();
        NoteDTO noteDTO = mock(NoteDTO.class);
        Note note = mock(Note.class);
        String extractCode = ExtractCode.EXTRACT_CODE_1;

        when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));
        when(noteMapper.toDTO(note, extractCode)).thenReturn(noteDTO);

        //When
        NoteDTO resultUnderTest = objectUnderTest.getNoteById(noteId, extractCode);

        //Then
        assertThat(resultUnderTest).isEqualTo(noteDTO);
    }

    @Test
    void deleteNoteByIdTest() throws ResourcesNotFoundException {
        UUID noteId = UUID.randomUUID();

        Note note = mock(Note.class);
        when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));

        objectUnderTest.deleteNoteById(noteId);

        verify(note).delete(noteRepository);
    }

    @Test
    void addTagTest1() {
        //Given
        UUID noteId = UUID.randomUUID();
        UUID tagId = UUID.randomUUID();
        Note note = mock(Note.class);
        Tag tag = mock(Tag.class);

        when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));
        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));

        //When
        objectUnderTest.addTag(noteId, tagId);

        //Then
        verify(note).addTag(tag, noteRepository);
    }

    @Test
    void addTagTest2() {
        //Given
        UUID noteId = UUID.randomUUID();
        UUID tagId = UUID.randomUUID();
        Note note = mock(Note.class);

        when(noteRepository.findById(noteId)).thenReturn(Optional.of(note));
        when(tagRepository.findById(tagId)).thenReturn(Optional.empty());

        //When
        assertThatThrownBy(() -> objectUnderTest.addTag(noteId, tagId))
                .isInstanceOf(ResourcesNotFoundException.class)
                .hasMessage(SondjiErrorCode.TAG_NOT_FOUND.getValue());
    }

    @Test
    void addTagTest3() {
        //Given
        UUID noteId = UUID.randomUUID();
        UUID tagId = UUID.randomUUID();

        when(noteRepository.findById(noteId)).thenReturn(Optional.empty());

        //When
        assertThatThrownBy(() -> objectUnderTest.addTag(noteId, tagId))
                .isInstanceOf(ResourcesNotFoundException.class)
                .hasMessage(SondjiErrorCode.NOTE_NOT_FOUND.getValue());
    }
}
