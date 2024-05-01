package com.h87.sondji.service.mapper;

import com.h87.sondji.utils.CreateNoteData;
import com.manageUser.model.CreateNoteDTO;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}