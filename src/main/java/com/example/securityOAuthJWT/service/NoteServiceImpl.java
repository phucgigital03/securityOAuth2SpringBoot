package com.example.securityOAuthJWT.service;

import com.example.securityOAuthJWT.model.Note;
import com.example.securityOAuthJWT.repositories.NoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;
    private final AuditLogService auditLogService;

    public NoteServiceImpl(NoteRepository noteRepository, AuditLogService auditLogService) {
        this.noteRepository = noteRepository;
        this.auditLogService = auditLogService;
    }

    @Override
    public Note createNoteForUser(String username, String content) {
        Note note = new Note();
        note.setContent(content);
        note.setOwnerUsername(username);
        Note savedNote = noteRepository.save(note);
        auditLogService.logNoteCreation(username, savedNote);
        return savedNote;
    }

    @Override
    public Note updateNoteForUser(Long noteId, String content, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(()
                -> new RuntimeException("Note not found"));
        note.setContent(content);
        Note updatedNote = noteRepository.save(note);
        auditLogService.logNoteUpdate(username, updatedNote);
        return updatedNote;
    }

    @Override
    public void deleteNoteForUser(Long noteId, String username) {
        Note note = noteRepository.findById(noteId).orElseThrow(
                () -> new RuntimeException("Note not found")
        );
        auditLogService.logNoteDeletion(username, noteId);
        noteRepository.delete(note);
    }

    @Override
    @Transactional
    public List<Note> getNotesForUser(String username) {
        List<Note> personalNotes = noteRepository
                .findByOwnerUsername(username);
        return personalNotes;
    }
}
