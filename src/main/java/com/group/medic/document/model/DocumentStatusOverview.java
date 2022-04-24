package com.group.medic.document.model;

import java.util.List;

public class DocumentStatusOverview {

    public DocumentStatusOverview(List<DocumentStatusPerUser> statusesPerUsers) {
        this.statusesPerUsers = statusesPerUsers;
    }

    private final List<DocumentStatusPerUser> statusesPerUsers;

    private String finalStatus;

    public List<DocumentStatusPerUser> getStatusesPerUsers() {
        return statusesPerUsers;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }
}
