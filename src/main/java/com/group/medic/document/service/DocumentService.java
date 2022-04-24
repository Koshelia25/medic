package com.group.medic.document.service;

import com.group.medic.document.model.*;
import com.group.medic.document.repository.DocumentRepository;
import com.group.medic.user.model.User;
import com.group.medic.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository repository;


    @Autowired
    private UserService userService;

    public List<Document> getAll() {
        //TODO change for security
        int userId = 1;
        List<Document> documents = repository.getAll();
        documents.forEach(d -> {
            DocumentStatusOverview overview = getAllStatuses(d.getId());
            DocumentStatusPerUser statusForLoggedInUser = null;
            for(DocumentStatusPerUser status : overview.getStatusesPerUsers()) {
                if(userId == status.getUserId()) {
                    statusForLoggedInUser = status;
                    overview.getStatusesPerUsers().remove(status);
                    break;
                }
            }
            if(statusForLoggedInUser != null) {
                d.setDocumentStatus(statusForLoggedInUser.getDocumentStatus());
            }
            d.setDocumentStatusOverview(overview);

        });
        return documents;
    }

    public Document get(int id) {
        return repository.get(id);
    }

    public void delete(int id) {
        repository.delete(id);
    }


    public Document add(Document document) {
        Document savedDocument = repository.add(document);
        for (Integer userId : savedDocument.getUserIds()) {
            UserDocument userDocument = new UserDocument();
            userDocument.setDocumentId(document.getId());
            userDocument.setUserId(userId);
            userDocument.setDocumentStatus(DocumentStatus.NEW);
            repository.addUserDocument(userDocument);
        }
        return savedDocument;
    }

    public List<Document> getAll(int userId) {
        return repository.getAll(userId);
    }

    private DocumentStatusOverview getAllStatuses(int id) {
        List<DocumentStatusPerUser> statusesPerUsers = repository.getAllStatuses(id);
        statusesPerUsers.forEach(s -> {
            User user = userService.get(s.getUserId());
            s.setUserName(user.getName());
        });
        DocumentStatusOverview documentStatus = new DocumentStatusOverview(statusesPerUsers);
        return documentStatus;
    }

    public void updateStatus(int id, Integer userId, DocumentStatus status) {
        repository.updateStatus(id, userId, status);
    }

    public boolean update(Document document) {
        return repository.update(document);
    }

}
