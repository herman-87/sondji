package com.h87.sondji.service.mapper;

import com.h87.sondji.utils.CreateNoteData;
import com.manageUser.model.CreateNoteDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface NoteMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "title")
    @Mapping(target = "content")
    CreateNoteData fromCreateNoteDTO(CreateNoteDTO createNoteDTO);
}
