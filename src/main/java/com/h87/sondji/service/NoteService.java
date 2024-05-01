package com.h87.sondji.service;

import com.h87.sondji.commons.EntityBase;
import com.h87.sondji.domain.note.Note;
import com.h87.sondji.domain.note.NoteRepository;
import com.h87.sondji.domain.note.NoteStatus;
import com.h87.sondji.service.mapper.NoteMapper;
import com.manageUser.model.CreateNoteDTO;
import com.manageUser.model.NoteDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;

    @Transactional
    public UUID createNote(CreateNoteDTO createNoteDTO) {
        return Optional.of(createNoteDTO)
                .map(noteMapper::fromCreateNoteDTO)
                .map(createNoteData -> Note.createNote(createNoteData, noteRepository))
                .map(EntityBase::getId)
                .orElseThrow(() -> new RuntimeException("Error when trying to create a Note"));
    }

    public List<NoteDTO> getAllPublishedNotes(String extractCode1) {
        return noteRepository.findAllByStatus(NoteStatus.PUBLISHED)
                .stream()
                .map(note -> noteMapper.toDTO(note, extractCode1))
                .toList();
    }
}
