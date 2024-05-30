package com.h87.sondji.service;

import com.h87.sondji.api.NoteResources;
import com.h87.sondji.commons.EntityBase;
import com.h87.sondji.domain.note.Note;
import com.h87.sondji.domain.note.NoteRepository;
import com.h87.sondji.domain.note.NoteStatus;
import com.h87.sondji.domain.tag.Tag;
import com.h87.sondji.domain.tag.TagRepository;
import com.h87.sondji.exceptions.ResourcesNotFoundException;
import com.h87.sondji.service.mapper.NoteMapper;
import com.h87.sondji.utils.SondjiErrorCode;
import com.h87.sondji.utils.UpdateNoteData;
import com.manageUser.model.CreateNoteDTO;
import com.manageUser.model.NoteDTO;
import com.manageUser.model.NoteStatusDTO;
import com.manageUser.model.UpdateNoteDTO;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
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
    private final TagRepository tagRepository;
    private final NoteResources noteResources;

    @Transactional
    public UUID createNote(CreateNoteDTO createNoteDTO) {
        return Optional.of(createNoteDTO)
                .map(noteMapper::fromCreateNoteDTO)
                .map(createNoteData -> Note.createNote(createNoteData, noteRepository))
                .map(EntityBase::getId)
                .orElseThrow(() -> new RuntimeException("Error when trying to create a Note"));
    }

    @Transactional
    public List<NoteDTO> getAllPublishedNotes(String extractCode1, NoteStatusDTO status) {
        NoteStatus noteStatus = noteMapper.fromNoteStatusDTO(status);
        return noteRepository.findAllByStatus(noteStatus)
                .stream()
                .map(note -> noteMapper.toDTO(note, extractCode1))
                .toList();
    }

    @SneakyThrows
    @Transactional
    public void updateNoteById(UUID noteId, UpdateNoteDTO updateNoteDTO) {
        UpdateNoteData updateNoteData = noteMapper.fromUpdateNoteDTO(updateNoteDTO);
        Note note = getNote(noteId);
        note.update(updateNoteData, noteRepository);
    }

    private Note getNote(UUID noteId) throws ResourcesNotFoundException {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourcesNotFoundException(SondjiErrorCode.NOTE_NOT_FOUND));
    }

    @SneakyThrows
    @Transactional
    public NoteDTO getNoteById(UUID noteId, String extractCode) {
        Note note = getNote(noteId);
        return noteMapper.toDTO(note, extractCode);
    }


    @SneakyThrows
    @Transactional
    public void deleteNoteById(UUID noteId) {
        Note note = getNote(noteId);
        note.delete(noteRepository);
    }

    @Transactional
    @SneakyThrows
    public void addTag(UUID noteId, UUID tagId) {
        Note note = getNoteById(noteId);
        Tag tag = getTagById(tagId);
        note.addTag(tag, noteRepository);
    }

    private Tag getTagById(UUID tagId) throws ResourcesNotFoundException {
        return tagRepository.findById(tagId)
                .orElseThrow(() -> new ResourcesNotFoundException(SondjiErrorCode.TAG_NOT_FOUND));
    }

    private Note getNoteById(UUID noteId) throws ResourcesNotFoundException {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourcesNotFoundException(SondjiErrorCode.NOTE_NOT_FOUND));
    }
}
