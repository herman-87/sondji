package com.h87.sondji.api;

import com.h87.sondji.service.NoteService;
import com.manageUser.api.NoteApi;
import com.manageUser.model.CreateNoteDTO;
import com.manageUser.model.NoteDTO;
import com.manageUser.model.NoteStatusDTO;
import com.manageUser.model.UpdateNoteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NoteResources implements NoteApi {
    private final NoteService noteService;

    @Override
    public ResponseEntity<UUID> createNote(CreateNoteDTO createNoteDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(noteService.createNote(createNoteDTO));
    }

    @Override
    public ResponseEntity<List<NoteDTO>> getAllNotesByStatus(String extractCode, NoteStatusDTO status) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(noteService.getAllPublishedNotes(extractCode, status));
    }

    @Override
    public ResponseEntity<Void> updateNoteById(UUID noteId, UpdateNoteDTO updateNoteDTO) {
        noteService.updateNoteById(noteId, updateNoteDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
