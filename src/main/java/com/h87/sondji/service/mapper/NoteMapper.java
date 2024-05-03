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
import org.mapstruct.*;

import java.util.Optional;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface NoteMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "title")
    @Mapping(target = "content")
    CreateNoteData fromCreateNoteDTO(CreateNoteDTO createNoteDTO);


    default NoteDTO toDTO(Note note, String extractCode) {
        if (ExtractCode.EXTRACT_CODE_1.equals(extractCode)) {
            return extractCode1(note);
        }
        throw new RuntimeException("Extraction of code ".concat(extractCode).concat(" is not supported for notes."));
    }

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "title", qualifiedByName = "fromNoteTitleToString")
    @Mapping(target = "content", qualifiedByName = "fromNoteContentToString")
    NoteDTO extractCode1(Note note);

    @Named("fromNoteTitleToString")
    default String fromNoteTitleToString(NoteTitle noteTitle) {
        return Optional.ofNullable(noteTitle)
                .map(NoteTitle::getValue)
                .orElse(null);
    }

    @Named("fromNoteContentToString")
    default String fromNoteContentToString(NoteContent noteContent) {
        return Optional.ofNullable(noteContent)
                .map(NoteContent::getValue)
                .orElse(null);
    }

    @BeanMapping(ignoreByDefault = true)
    NoteStatus fromNoteStatusDTO(NoteStatusDTO status);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "title")
    @Mapping(target = "content")
    UpdateNoteData fromUpdateNoteDTO(UpdateNoteDTO updateNoteDTO);
}
