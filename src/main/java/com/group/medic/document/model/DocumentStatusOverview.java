package com.group.medic.document.model;

import java.util.List;

public class DocumentStatusOverview {

    public DocumentStatusOverview(List<DocumentStatusPerUser> statusesPerUsers) {
        this.statusesPerUsers = statusesPerUsers;
    }

    private final List<DocumentStatusPerUser> statusesPerUsers;

    public List<DocumentStatusPerUser> getStatusesPerUsers() {
        return statusesPerUsers;
    }
}
