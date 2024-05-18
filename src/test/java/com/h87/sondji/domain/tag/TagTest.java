package com.h87.sondji.domain.tag;

import com.h87.sondji.utils.CreateTagData;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class TagTest {

    @Test
    void createTagTest() {
        //Given
        String name = "any name";
        String description = "any description";
        Tag createdTag = mock(Tag.class);
        CreateTagData createTagData = CreateTagData.builder()
                .name(name)
                .description(description)
                .build();

        TagRepository tagRepository = mock(TagRepository.class);
        when(tagRepository.save(any())).thenReturn(createdTag);

        //When
        Tag resultUnderTest = Tag.createTag(createTagData, tagRepository);

        //Then
        verify(tagRepository).save(assertArg(tag -> {
            assertThat(tag)
                    .returns(name, tag1 -> tag1.getName().getValue())
                    .returns(description, tag1 -> tag1.getDescription().getValue());
        }));
        assertThat(resultUnderTest).isEqualTo(createdTag);
    }
}