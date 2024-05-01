package com.h87.sondji.domain.note;

import java.util.List;

public interface NoteRepository {
    Note save(Note note);

    List<Note> findAllByStatus(NoteStatus noteStatus);
}
