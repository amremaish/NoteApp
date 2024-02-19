package com.notes.service.impl;

import com.notes.dao.entites.Folder;
import com.notes.dao.entites.User;
import com.notes.dao.models.FolderModel;
import com.notes.dao.models.PageModel;
import com.notes.dao.repo.FolderRepo;
import com.notes.dao.repo.UserRepo;
import com.notes.error.CustomException;
import com.notes.service.interfaces.AuthService;
import com.notes.service.interfaces.FolderService;
import com.notes.util.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepo folderRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthService authService;

    @Override
    public PageModel<Folder> getAllFolders(int page, int size) {
        long current_user = authService.getCurrentUser().getId();
        return new PageModel<>(folderRepo.findByUserId(current_user, PageRequest.of(page, size)));
    }

    @Override
    public Folder getFolderById(Long id) {
        return folderRepo.findById(id).orElseThrow(() -> new CustomException("Folder is not found"));
    }

    @Override
    public FolderModel createFolder(FolderModel folderModel) {
        long current_user = authService.getCurrentUser().getId();
        User user = userRepo.findById(current_user).orElseThrow(() -> new CustomException("User is not found"));
        Folder folderExisted = folderRepo.findFirstByFolderNameAndUserId(folderModel.getFolderName(), user.getId());
        if (folderExisted != null) {
            throw new CustomException("Folder exists with the same name");
        }
        if (!Validator.isValidFolderName(folderModel.getFolderName())){
            throw new CustomException("Folder name is invalid.");
        }
        Folder folder = new Folder();
        folder.setFolderName(folderModel.getFolderName());
        folder.setPublic(folderModel.isPublic());
        folder.setUser(user);
        return new FolderModel(folderRepo.save(folder));
    }

    @Override
    public FolderModel updateFolder(Long id, FolderModel folderModel) {
        long current_user = authService.getCurrentUser().getId();
        Folder oldFolder = folderRepo.findById(id).orElseThrow(() -> new CustomException("Folder is not found"));
        if (oldFolder.getUser().getId() != current_user) {
            throw new CustomException("You don't have access to this folder");
        }
        User user = userRepo.findById(current_user).orElseThrow(() -> new CustomException("User is not found"));
        Folder folderExisted = folderRepo.findFirstByFolderNameAndUserIdAndIdNot(folderModel.getFolderName(), user.getId(), id);
        if (folderExisted != null) {
            throw new CustomException("Folder exists with the same name");
        }
        if (!Validator.isValidFolderName(folderModel.getFolderName())){
            throw new CustomException("Folder name is invalid.");
        }
        oldFolder.setFolderName(folderModel.getFolderName());
        oldFolder.setPublic(folderModel.isPublic());
        return new FolderModel(folderRepo.save(oldFolder));
    }

    @Override
    public void deleteFolder(Long id) {
        long current_user = authService.getCurrentUser().getId();
        Folder oldFolder = folderRepo.findById(id).orElseThrow(() -> new CustomException("Folder is not found"));
        if (oldFolder.getUser().getId() != current_user) {
            throw new CustomException("You don't have access to this folder");
        }
        folderRepo.deleteById(id);
    }
}
