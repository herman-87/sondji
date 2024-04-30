package com.h87.sondji.domain.note;

import com.h87.sondji.domain.commons.EntityBase;
import com.h87.sondji.domain.tag.Tag;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
    private List<Tag> tagList = new LinkedList<>();
}
