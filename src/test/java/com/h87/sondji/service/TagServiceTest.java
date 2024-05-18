package com.h87.sondji.service;

import com.h87.sondji.commons.ExtractCode;
import com.h87.sondji.domain.tag.Tag;
import com.h87.sondji.domain.tag.TagRepository;
import com.h87.sondji.service.mapper.TagMapper;
import com.h87.sondji.utils.CreateTagData;
import com.manageUser.model.CreateTagDTO;
import com.manageUser.model.TagDTO;
import org.assertj.core.api.Assertions;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class TagServiceTest {
    @InjectMocks
    private TagService objectUnderTest;
    @Mock
    private TagRepository tagRepository;
    @Mock
    private TagMapper tagMapper;

    @Test
    void createTagTest() {
        //Given
        CreateTagDTO createTagDTO = mock(CreateTagDTO.class);
        UUID createdTagId = UUID.randomUUID();
        CreateTagData createTagData = CreateTagData.builder().build();
        Mockito.mockStatic(Tag.class);

        when(tagMapper.fromCreateTagDTO(createTagDTO)).thenReturn(createTagData);
        Tag tag = mock(Tag.class);
        when(Tag.createTag(createTagData, tagRepository)).thenReturn(tag);
        when(tag.getId()).thenReturn(createdTagId);

        //When
        UUID resultUnderTest = objectUnderTest.createTag(createTagDTO);

        //Then
        assertThat(resultUnderTest).isEqualTo(createdTagId);
    }

    @Test
    void getTagByIdTest() {
        UUID tagId = UUID.randomUUID();
        String extractCode = ExtractCode.EXTRACT_CODE_1;
        TagDTO tagDTO = mock(TagDTO.class);
        Tag tag = mock(Tag.class);

        when(tagRepository.findById(tagId)).thenReturn(Optional.of(tag));
        when(tagMapper.toDTO(tag, extractCode)).thenReturn(tagDTO);

        TagDTO resultUnderTest = objectUnderTest.getTagById(tagId, extractCode);

        assertThat(resultUnderTest).isEqualTo(tagDTO);
    }

}