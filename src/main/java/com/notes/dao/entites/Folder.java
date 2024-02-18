package com.notes.dao.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.notes.dao.models.FolderModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "folders")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @NotNull(message = "user_id must not be empty")
    @JoinColumn(name = "user_id")
    private User user;
    @NotNull(message = "Folder name must not be empty")
    private String folderName;

    private boolean isPublic = false;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
