package com.h87.sondji.domain.note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoteRepository {
    Note save(Note note);

    List<Note> findAllByStatus(NoteStatus noteStatus);

    Optional<Note> findById(UUID noteId);
}
