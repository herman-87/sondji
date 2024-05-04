package com.h87.sondji.service;

import com.h87.sondji.domain.note.Note;
import com.h87.sondji.domain.note.NoteRepository;
import com.h87.sondji.domain.note.NoteStatus;
import com.h87.sondji.repository.NoteSpringRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class NoteDefaultRepository implements NoteRepository {
    private final NoteSpringRepository noteSpringRepository;

    @Override
    public Note save(Note note) {
        return noteSpringRepository.save(note);
    }

    @Override
    public List<Note> findAllByStatus(NoteStatus status) {
        return noteSpringRepository.findAllByStatus(status);
    }

    @Override
    public Optional<Note> findById(UUID noteId) {
        return noteSpringRepository.findById(noteId);
    }

    @Override
    public void delete(Note note) {
        noteSpringRepository.delete(note);
    }
}
