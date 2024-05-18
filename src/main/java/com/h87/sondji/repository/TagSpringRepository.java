package com.h87.sondji.repository;

import com.h87.sondji.domain.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TagSpringRepository extends JpaRepository<Tag, UUID> {
}
