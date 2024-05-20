package com.h87.sondji.service.mapper;

import com.h87.sondji.commons.ExtractCode;
import com.h87.sondji.domain.tag.Tag;
import com.h87.sondji.domain.tag.TagDescription;
import com.h87.sondji.domain.tag.TagName;
import com.h87.sondji.utils.CreateTagData;
import com.manageUser.model.CreateTagDTO;
import com.manageUser.model.TagDTO;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TagMapperTest {
    private TagMapper objectUnderTest;

    @BeforeEach
    void setup() {
        objectUnderTest = new TagMapperImpl();
    }

    @Test
    void fromCreateTagDTO() {
        //Given
        String name = "any name";
        String description = "any description";
        CreateTagDTO createTagDTO = new CreateTagDTO()
                .name(name)
                .description(description);

        //When
        CreateTagData resultUnderTest = objectUnderTest.fromCreateTagDTO(createTagDTO);

        //Then
        assertThat(resultUnderTest)
                .returns(name, CreateTagData::name)
                .returns(description, CreateTagData::description);
    }

    @Test
    void toDTOTest() {
        Tag tag = mock(Tag.class);
        TagDTO tagDTO = mock(TagDTO.class);
        objectUnderTest = spy(objectUnderTest);

        when(objectUnderTest.extractCode1(tag)).thenReturn(tagDTO);

        TagDTO resultUnderTest = objectUnderTest.toDTO(tag, ExtractCode.EXTRACT_CODE_1);

        assertThat(resultUnderTest).isEqualTo(tagDTO);
    }

    @Test
    void extractCode1Test() {
        String name = "any tag name";
        String description = "any tag description";
        Tag tag = Tag.builder()
                .name(new TagName(name))
                .description(new TagDescription(description))
                .build();

        TagDTO resultUnderTest = objectUnderTest.extractCode1(tag);

        assertThat(resultUnderTest)
                .returns(name, TagDTO::getName)
                .returns(description, TagDTO::getDescription);
    }
}