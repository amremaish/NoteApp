package com.notes.dao.models;

import com.notes.dao.entites.Item;
import com.notes.dao.entites.ItemType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class ItemModel {

    private Long id;

    private long folderId;

    private long userId;
    @NotNull(message = "Item type must be one of: IMAGE, NOTE")
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    private String content;


    public ItemModel() {

    }

    public ItemModel(Item item) {
        this.id = item.getId();
        this.itemType = item.getItemType();
        this.content = item.getContent();
        this.folderId = item.getFolder().getId();
        this.userId = item.getUser().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getFolderId() {
        return folderId;
    }

    public void setFolderId(long folderId) {
        this.folderId = folderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
