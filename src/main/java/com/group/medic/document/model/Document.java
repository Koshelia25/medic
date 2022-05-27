package com.group.medic.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Document {

    public Document() {
    }

    public Document(Integer id, String title, String link) {
        this.id = id;
        this.title = title;
        this.link = link;
    }

    private Integer id;

    private String title;

    private String link;

    private DocumentStatus documentStatus;

    private DocumentStatusOverview documentStatusOverview;

    private List<Integer> userIds;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDocumentStatusOverview(DocumentStatusOverview documentStatusOverview) {
        this.documentStatusOverview = documentStatusOverview;
    }

    public DocumentStatusOverview getDocumentStatusOverview() {
        return documentStatusOverview;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DocumentStatus getDocumentStatus() {
        return documentStatus;
    }

    public void setDocumentStatus(DocumentStatus documentStatus) {
        this.documentStatus = documentStatus;
    }
    @JsonIgnore
    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
