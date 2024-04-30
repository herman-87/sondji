package com.h87.sondji.api;

import com.h87.sondji.service.NoteService;
import com.manageUser.api.NoteApi;
import com.manageUser.model.CreateNoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NoteResources implements NoteApi {
    private final NoteService noteService;

    @Override
    public ResponseEntity<UUID> createNote(CreateNoteDTO createNoteDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(noteService.createNote(createNoteDTO));
    }
}
