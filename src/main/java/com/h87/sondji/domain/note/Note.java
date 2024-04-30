package com.h87.sondji.domain.note;

import com.h87.sondji.domain.commons.EntityBase;
import com.h87.sondji.domain.tag.Tag;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

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
    @ManyToMany
    @JoinTable(
            name = "t_note_tag",
            joinColumns = @JoinColumn(name = "c_note"),
            inverseJoinColumns = @JoinColumn(name = "c_tag")
    )
    private List<Tag> tagList = new LinkedList<>();
}
