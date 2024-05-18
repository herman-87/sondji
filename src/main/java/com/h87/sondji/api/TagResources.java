package com.h87.sondji.api;

import com.h87.sondji.service.TagService;
import com.manageUser.api.TagApi;
import com.manageUser.model.CreateTagDTO;
import com.manageUser.model.TagDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TagResources implements TagApi {
    private final TagService tagService;

    @Override
    public ResponseEntity<UUID> createTag(CreateTagDTO createTagDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tagService.createTag(createTagDTO));
    }

    @Override
    public ResponseEntity<TagDTO> getTagById(UUID tagId, String extractCode) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tagService.getTagById(tagId, extractCode));
    }
}
