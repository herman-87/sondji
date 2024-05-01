package com.h87.sondji.domain.note;

import com.h87.sondji.utils.CreateNoteData;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class NoteTest {
    private Note objectUnderTest;

    @Test
    @DisplayName(
            """
                    Given
                    When I create a new note
                    Then I should see that note is saved"""
    )
    void createNoteTest() {
        //Given
        String title = "any title";
        String content = "any content";
        CreateNoteData createNoteData = CreateNoteData.builder()
                .title(title)
                .content(content)
                .build();
        NoteRepository noteRepository = mock(NoteRepository.class);
        Note note = mock(Note.class);
        when(noteRepository.save(any())).thenReturn(note);

        //When
        Note resultUnderTest = Note.createNote(createNoteData, noteRepository);

        //Then
        verify(noteRepository)
                .save(
                        assertArg(note1 -> assertThat(note1)
                                .returns(title, note2 -> note2.getTitle().getValue())
                                .returns(content, note3 -> note3.getContent().getValue()))
                );
        assertThat(resultUnderTest).isEqualTo(note);
    }
}