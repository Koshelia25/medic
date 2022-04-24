package com.group.medic.document.repository;

import com.group.medic.document.model.Document;
import com.group.medic.document.model.DocumentStatus;
import com.group.medic.document.model.DocumentStatusPerUser;
import com.group.medic.document.model.UserDocument;

import java.util.List;

public interface DocumentRepository {

    List<Document> getAll();

    Document get(int id);

    void delete(int id);

    Document add(Document document);

    boolean update(Document document);

    List<Document> getAll(int userId);

    List<DocumentStatusPerUser> getAllStatuses(int id);

    boolean updateStatus(Integer id, Integer userId, DocumentStatus status);

    UserDocument addUserDocument(UserDocument userDocument);
}