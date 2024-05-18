package com.h87.sondji.service;

import com.h87.sondji.domain.tag.Tag;
import com.h87.sondji.domain.tag.TagRepository;
import com.h87.sondji.repository.TagSpringRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class TagDefaultRepository implements TagRepository {
    private final TagSpringRepository tagSpringRepository;

    @Override
    public Tag save(Tag tag) {
        return tagSpringRepository.save(tag);
    }

    @Override
    public Optional<Tag> findById(UUID tagId) {
        return tagSpringRepository.findById(tagId);
    }
}
