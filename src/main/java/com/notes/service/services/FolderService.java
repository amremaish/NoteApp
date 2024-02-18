package com.notes.service.spec;

import com.notes.dao.entites.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FolderService {
    Page<Folder> getAllFolders(Pageable pageable);
    Folder getFolderById(Long id);
    Folder createFolder(Folder folder);
    Folder updateFolder(Long id, Folder folder);
    void deleteFolder(Long id);
}