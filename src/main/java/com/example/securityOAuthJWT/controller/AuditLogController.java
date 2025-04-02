package com.example.securityOAuthJWT.controller;

import com.example.securityOAuthJWT.model.AuditLog;
import com.example.securityOAuthJWT.service.AuditLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
public class AuditLogController {

    @Autowired
    private AuditLogService auditLogService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<AuditLog> auditLog() {
        return auditLogService.getAllAuditLogs();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/note/{noteId}")
    public List<AuditLog> auditLog(@PathVariable Long noteId) {
        return auditLogService.getAuditLogsForNoteId(noteId);
    }
}
