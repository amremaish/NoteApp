package com.notes.service.impl;
import com.notes.dao.entites.Folder;
import com.notes.dao.repo.FolderRepo;
import com.notes.service.spec.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepo folderRepository; // Assuming you have a FolderRepository

    @Override
    public Page<Folder> getAllFolders(Pageable pageable) {
        return folderRepository.findAll(pageable);
    }

    @Override
    public Folder getFolderById(Long id) {
        return folderRepository.findById(id).orElse(null);
    }

    @Override
    public Folder createFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public Folder updateFolder(Long id, Folder folder) {
        if (folderRepository.existsById(id)) {
            folder.setId(id);
            return folderRepository.save(folder);
        } else {
            return null;
        }
    }

    @Override
    public void deleteFolder(Long id) {
        folderRepository.deleteById(id);
    }
}
