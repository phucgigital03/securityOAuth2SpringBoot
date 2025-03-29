package com.example.securityOAuthJWT.service;

import com.example.securityOAuthJWT.model.Note;
import jakarta.transaction.Transactional;

import java.util.List;

public interface NoteService {
    Note createNoteForUser(String username, String content);

    Note updateNoteForUser(Long noteId, String content, String username);

    void deleteNoteForUser(Long noteId, String username);

    @Transactional
    List<Note> getNotesForUser(String username);
}
