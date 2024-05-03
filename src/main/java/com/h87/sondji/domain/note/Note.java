package com.h87.sondji.domain.note;

import com.h87.sondji.commons.EntityBase;
import com.h87.sondji.domain.tag.Tag;
import com.h87.sondji.utils.CreateNoteData;
import com.h87.sondji.utils.UpdateNoteData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_note")
public class Note extends EntityBase {
    @Embedded
    private NoteTitle title;

    @Embedded
    private NoteContent content;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "c_status")
    private NoteStatus status = NoteStatus.DRAFT;

    @Builder.Default
    @ManyToMany
    @JoinTable(
            name = "t_note_tag",
            joinColumns = @JoinColumn(name = "c_note"),
            inverseJoinColumns = @JoinColumn(name = "c_tag")
    )
    private List<Tag> tagList = new LinkedList<>();

    public static Note createNote(CreateNoteData data, NoteRepository noteRepository) {
        Note note = Note.builder()
                .title(new NoteTitle(data.title()))
                .content(new NoteContent(data.content()))
                .build();
        return noteRepository.save(note);
    }

    public void update(UpdateNoteData updateNoteData, NoteRepository noteRepository) {

    }
}
