package com.group.medic.document.web;

import com.group.medic.document.model.Document;
import com.group.medic.document.model.DocumentStatus;
import com.group.medic.document.service.DocumentService;
import com.group.medic.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "documents", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin
public class DocumentController {

    @Autowired
    private DocumentService service;

    @GetMapping
    public List<Document> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Document get(@PathVariable int id) {
        return service.get(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @PostMapping
    public Document add(@RequestBody Document document) {
        return service.add(document);
    }

    @GetMapping("/user")
    public List<Document> getAllForLoggedInUser() {
        int userId = SecurityUtil.authUserId();
        return service.getAll(userId);
    }

    @PutMapping("/{id}/status/{status}")
    public void updateStatus(@PathVariable int id, @PathVariable DocumentStatus status) {
        int userId = SecurityUtil.authUserId();
        service.updateStatus(id, userId, status);
    }

    //TODO
    @PutMapping
    public boolean update(Document document) {
        return false;
    }
}

