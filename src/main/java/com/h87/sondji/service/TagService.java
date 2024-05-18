package com.h87.sondji.service;

import com.h87.sondji.domain.tag.Tag;
import com.h87.sondji.domain.tag.TagRepository;
import com.h87.sondji.service.exceptions.ResourcesNotFoundException;
import com.h87.sondji.service.mapper.TagMapper;
import com.h87.sondji.utils.SondjiErrorCode;
import com.manageUser.model.CreateTagDTO;
import com.manageUser.model.TagDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Transactional
    public UUID createTag(CreateTagDTO createTagDTO) {
        return Optional.ofNullable(createTagDTO)
                .map(tagMapper::fromCreateTagDTO)
                .map(createTagData -> Tag.createTag(createTagData, tagRepository))
                .map(Tag::getId)
                .orElseThrow(() -> new RuntimeException(SondjiErrorCode.SOMETHING_WRONG.getValue()));
    }

    @SneakyThrows
    @Transactional
    public TagDTO getTagById(UUID tagId, String extractCode) {
        return Optional.ofNullable(tagId)
                .flatMap(tagRepository::findById)
                .map(tag1 -> tagMapper.toDTO(tag1, extractCode))
                .orElseThrow(() -> new ResourcesNotFoundException(SondjiErrorCode.NOTE_NOT_FOUND));
    }
}
