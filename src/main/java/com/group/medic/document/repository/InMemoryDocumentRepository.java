package com.group.medic.document.repository;

import com.group.medic.document.model.Document;
import com.group.medic.document.model.DocumentStatus;
import com.group.medic.document.model.DocumentStatusPerUser;
import com.group.medic.document.model.UserDocument;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class InMemoryDocumentRepository implements DocumentRepository {

    private final Document DOCUMENT_1 = new Document(1, "Document 1", "");

    private final Document DOCUMENT_2 = new Document(2, "Document 2", "");

    private final Map<Integer, Document> DOCUMENT_MAP = new HashMap<>();

    {
//        DOCUMENT_1.setStatusForCurrentUser("test");
//        DOCUMENT_2.setStatusForCurrentUser("test");

        DOCUMENT_MAP.put(1, DOCUMENT_1);
        DOCUMENT_MAP.put(2, DOCUMENT_2);
    }

    @Override
    public List<Document> getAll() {
        return new ArrayList<>(DOCUMENT_MAP.values());
    }

    @Override
    public Document get(int id) {
        return DOCUMENT_MAP.get(id);
    }

    @Override
    public void delete(int id) {
        DOCUMENT_MAP.remove(id);
    }

    @Override
    public Document add(Document document) {
        int id = DOCUMENT_MAP.size();
        document.setId(++id);
        return DOCUMENT_MAP.put(document.getId(), document);
    }

    @Override
    public boolean update(Document document) {
        return false;
    }

    @Override
    public List<Document> getAll(int userId) {
        return getAll();
    }

    @Override
    public List<DocumentStatusPerUser> getAllStatuses(int id) {
//        DocumentStatusPerUser documentStatusPerUser = new DocumentStatusPerUser(1, "signed");
//        return Collections.singletonList(documentStatusPerUser);
        return null;
    }

    @Override
    public boolean updateStatus(Integer id, Integer userId, DocumentStatus status) {
        Document document = DOCUMENT_MAP.get(id);
//        document.setStatusForCurrentUser(status);
        return true;
    }

    @Override
    public UserDocument addUserDocument(UserDocument userDocument) {
        return null;
    }
}
