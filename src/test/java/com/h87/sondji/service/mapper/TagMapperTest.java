package com.h87.sondji.service.mapper;

import com.h87.sondji.utils.CreateTagData;
import com.manageUser.model.CreateTagDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Assertions.assertThat(resultUnderTest)
                .returns(name, CreateTagData::name)
                .returns(description, CreateTagData::description);
    }
}