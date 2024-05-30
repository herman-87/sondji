package com.h87.sondji.domain.tag;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TagRepository {
    Tag save(Tag tag);

    Optional<Tag> findById(UUID tagId);

    List<Tag> findAll();
}
