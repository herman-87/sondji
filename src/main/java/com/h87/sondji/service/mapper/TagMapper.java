package com.h87.sondji.service.mapper;

import com.h87.sondji.commons.ExtractCode;
import com.h87.sondji.domain.tag.Tag;
import com.h87.sondji.domain.tag.TagDescription;
import com.h87.sondji.domain.tag.TagName;
import com.h87.sondji.utils.CreateTagData;
import com.manageUser.model.CreateTagDTO;
import com.manageUser.model.TagDTO;
import org.mapstruct.BeanMapping;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface TagMapper {
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name")
    @Mapping(target = "description")
    CreateTagData fromCreateTagDTO(CreateTagDTO createTagDTO);

    default TagDTO toDTO(Tag tag, String extractCode) {
        if (ExtractCode.EXTRACT_CODE_1.equals(extractCode)) {
            return extractCode1(tag);
        }
        throw new RuntimeException("Extraction of code ".concat(extractCode).concat(" is not supported for notes."));
    }

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "name", qualifiedByName = "fromTagNameToString")
    @Mapping(target = "description", qualifiedByName = "fromTagDescriptionToString")
    TagDTO extractCode1(Tag tag);

    @Named("fromTagNameToString")
    default String fromTagNameToString(TagName tagName) {
        return Optional.ofNullable(tagName)
                .map(TagName::getValue)
                .orElse(null);
    }

    @Named("fromTagDescriptionToString")
    default String fromTagDescriptionToString(TagDescription tagDescription) {
        return Optional.ofNullable(tagDescription)
                .map(TagDescription::getValue)
                .orElse(null);
    }
}
