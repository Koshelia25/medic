package com.group.medic.document.model;

public class DocumentStatusPerUser {

    public DocumentStatusPerUser() {
    }

    public DocumentStatusPerUser(Integer userId, DocumentStatus documentStatus) {
        this.userId = userId;
        this.documentStatus = documentStatus;
    }

    private Integer userId;
    private DocumentStatus documentStatus;
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
