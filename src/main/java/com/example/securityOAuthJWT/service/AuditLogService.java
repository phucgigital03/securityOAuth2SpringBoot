package com.example.securityOAuthJWT.service;

import com.example.securityOAuthJWT.model.AuditLog;
import com.example.securityOAuthJWT.model.Note;

import java.util.List;

public interface AuditLogService {
     void logNoteCreation(String username, Note note);
 
     void logNoteUpdate(String username, Note note);
 
     void logNoteDeletion(String username, Long noteId);

    List<AuditLog> getAllAuditLogs();

    List<AuditLog> getAuditLogsForNoteId(Long noteId);
}