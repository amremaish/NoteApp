package com.notes.dao.models;


import com.notes.dao.entites.Folder;

import javax.validation.constraints.NotNull;

public class FolderModel {

    private long id;
    private long userId;
    @NotNull(message = "Folder name must not be empty")
    private String folderName;

    private boolean isPublic = false;

    public FolderModel(Folder folder) {
        this.id = folder.getId();
        this.userId = folder.getUser().getId();
        this.folderName = folder.getFolderName();
        this.isPublic = folder.isPublic();
    }
    public FolderModel() {

    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
