package com.h87.sondji.service.mapper;

import com.h87.sondji.commons.ExtractCode;
import com.h87.sondji.domain.note.Note;
import com.h87.sondji.domain.note.NoteContent;
import com.h87.sondji.domain.note.NoteStatus;
import com.h87.sondji.domain.note.NoteTitle;
import com.h87.sondji.utils.CreateNoteData;
import com.h87.sondji.utils.UpdateNoteData;
import com.manageUser.model.CreateNoteDTO;
import com.manageUser.model.NoteDTO;
import com.manageUser.model.NoteStatusDTO;
import com.manageUser.model.UpdateNoteDTO;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

class NoteMapperTest {
    private NoteMapper objectUnderTest;

    @BeforeEach
    void setup() {
        objectUnderTest = new NoteMapperImpl();
    }

    @Test
    void fromCreateNoteDTOTest() {
        //Given
        String title = "any title";
        String content = "any content";
        CreateNoteDTO createNoteDTO = new CreateNoteDTO()
                .title(title)
                .content(content);

        //When
        CreateNoteData resultUnderTest = objectUnderTest.fromCreateNoteDTO(createNoteDTO);

        //Then
        assertThat(resultUnderTest)
                .returns(title, CreateNoteData::title)
                .returns(content, CreateNoteData::content);
    }

    @Test
    void toDTO_1_test() {
        //Given
        objectUnderTest = spy(objectUnderTest);
        Note note = mock(Note.class);
        NoteDTO noteDTO = mock(NoteDTO.class);

        when(objectUnderTest.extractCode1(note)).thenReturn(noteDTO);

        //When
        NoteDTO resultUnderTest = objectUnderTest.toDTO(note, ExtractCode.EXTRACT_CODE_1);

        //Then
        assertThat(resultUnderTest).isEqualTo(noteDTO);
    }

    @Test
    void extractCode1_1_test() {
        //Given
        String titleValue = "any title";
        String contentValue = "any content";
        Note note = Note.builder()
                .title(new NoteTitle(titleValue))
                .content(new NoteContent(contentValue))
                .status(NoteStatus.DRAFT)
                .build();

        //When
        NoteDTO resultUnderTest = objectUnderTest.extractCode1(note);

        //Then
        assertThat(resultUnderTest)
                .returns(titleValue, NoteDTO::getTitle)
                .returns(contentValue, NoteDTO::getContent);
    }

    @Test
    void fromNoteStatusDTO_1_tes() {
        //When
        NoteStatus resultUnderTest = objectUnderTest.fromNoteStatusDTO(NoteStatusDTO.DELETED);

        //Then
        assertThat(resultUnderTest).isEqualTo(NoteStatus.DELETED);
    }

    @Test
    void fromUpdateNoteDTO_1_test() {
        //Given
        String title = "any tile";
        String content = "any content";
        UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO()
                .title(title)
                .content(content);

        //When
        UpdateNoteData resultUnderTest = objectUnderTest.fromUpdateNoteDTO(updateNoteDTO);

        //Then
        assertThat(resultUnderTest)
                .returns(title, UpdateNoteData::title)
                .returns(content, UpdateNoteData::content);
    }
}