package com.h87.sondji.service;

import com.h87.sondji.domain.note.Note;
import com.h87.sondji.domain.note.NoteRepository;
import com.h87.sondji.repository.NoteSpringRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoteDefaultRepository implements NoteRepository {
    private final NoteSpringRepository noteSpringRepository;

    @Override
    public Note save(Note note) {
        return noteSpringRepository.save(note);
    }
}
