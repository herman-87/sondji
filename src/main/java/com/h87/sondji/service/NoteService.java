package com.h87.sondji.service;

import com.h87.sondji.domain.note.NoteRepository;
import com.manageUser.model.CreateNoteDTO;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public UUID createNote(CreateNoteDTO createNoteDTO) {
        return null;
    }
}
