package com.notes.service.interfaces;

import com.notes.dao.entites.Folder;
import com.notes.dao.models.FolderModel;
import com.notes.dao.models.PageModel;

public interface FolderService {
    PageModel<Folder> getAllFolders(int page, int size);
    Folder getFolderById(Long id);
    FolderModel createFolder(FolderModel folderModel);
    FolderModel updateFolder(Long id, FolderModel folderModel);
    void deleteFolder(Long id);
}