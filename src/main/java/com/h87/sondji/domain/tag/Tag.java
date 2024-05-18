package com.h87.sondji.domain.tag;


import com.h87.sondji.commons.EntityBase;
import com.h87.sondji.utils.CreateTagData;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_tag")
public class Tag extends EntityBase {
    @Embedded
    private TagName name;
    @Embedded
    private TagDescription description;

    public static Tag createTag(CreateTagData createTagData, TagRepository tagRepository) {
        Tag tagToCreate = Tag.builder()
                .name(new TagName(createTagData.name()))
                .description(new TagDescription(createTagData.description()))
                .build();
        return tagRepository.save(tagToCreate);
    }
}
