package com.notes.service.impl;

import com.notes.dao.entites.Folder;
import com.notes.dao.entites.Item;
import com.notes.dao.entites.ItemType;
import com.notes.dao.entites.User;
import com.notes.dao.models.ItemModel;
import com.notes.dao.models.PageModel;
import com.notes.dao.repo.ItemRepo;
import com.notes.error.CustomException;
import com.notes.service.services.AuthService;
import com.notes.service.services.FolderService;
import com.notes.service.services.ItemService;
import com.notes.util.ImageUploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private FolderService folderService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ItemRepo itemRepo;


    @Override
    public PageModel<Item> getAllItems( long folder_id, int page, int size) {
        long current_user = authService.getCurrentUser().getId();
        return new PageModel<>(itemRepo.findByUserIdAndFolderId(current_user, folder_id, PageRequest.of(page, size)));
    }

    @Override
    public Item getItemById(Long id) {
        return null;
    }

    @Override
    public ItemModel createItem(ItemModel itemModel, long folder_id, MultipartFile image) {
        long userId = authService.getCurrentUser().getId();
        Folder folder = folderService.getFolderById(folder_id);
        itemModel.setUserId(userId);
        if (!folder.isPublic() && userId != folder.getUser().getId()) {
            throw new CustomException("You don't have permission to do this action");
        }
        if (itemModel.getItemType() == ItemType.IMAGE) {
            itemModel = SaveImageItem(itemModel, folder, image);
        } else {
            itemModel = SaveNoteItem(itemModel, folder);
        }
        return itemModel;
    }

    private ItemModel SaveNoteItem(ItemModel itemModel, Folder folder) {
        if (itemModel.getContent() == null){
            throw new CustomException("content is missing for note.");
        }
        User curUser = authService.getCurrentUser();
        Item item = new Item();
        item.setItemType(itemModel.getItemType());
        item.setContent(itemModel.getContent());
        item.setFolder(folder);
        item.setUser(curUser);
        return new ItemModel(itemRepo.save(item));
    }

    private ItemModel SaveImageItem(ItemModel itemModel, Folder folder, MultipartFile image) {
        User curUser = authService.getCurrentUser();
        String fileName = ImageUploader.uploadImage(image, itemModel.getUserId(), folder.getFolderName());
        Item itemDb = itemRepo.findFirstByContentAndUserIdAndFolderId(fileName, curUser.getId(), folder.getId());
        if (itemDb != null) {
            throw new CustomException("Image already exists in the " + folder.getFolderName() + " folder.");
        }
        Item item = new Item();
        if (itemModel.getId() != null) {
            item.setId(itemModel.getId());
        }
        item.setItemType(itemModel.getItemType());
        item.setContent(fileName);
        item.setFolder(folder);
        item.setUser(curUser);
        return new ItemModel(itemRepo.save(item));
    }


    @Override
    public ItemModel updateItem(Long id, ItemModel itemModel, MultipartFile image) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new CustomException("item can't be find"));
        Folder folder = folderService.getFolderById(itemModel.getFolderId());
        User curUser = authService.getCurrentUser();
        if (curUser.getId() != item.getUser().getId() && curUser.getId() != folder.getUser().getId()) {
            throw new CustomException("You don't have permission to do this action");
        }
        // update item model
        itemModel.setId(id);
        itemModel.setUserId(item.getUser().getId());

        if (itemModel.getItemType() == ItemType.IMAGE) {
            ImageUploader.removeImage(curUser.getId(), folder.getFolderName(), item.getContent());
            itemModel = SaveImageItem(itemModel, folder, image);
        } else {
            itemModel = SaveNoteItem(itemModel, folder);
        }
        return itemModel;
    }

    @Override
    public void deleteItem(Long id) {
        Item item = itemRepo.findById(id).orElseThrow(() -> new CustomException("item can't be find"));
        Folder folder = item.getFolder();
        User curUser = authService.getCurrentUser();
        if (curUser.getId() != item.getUser().getId() && curUser.getId() != folder.getUser().getId()) {
            throw new CustomException("You don't have permission to do this action");
        }
        itemRepo.delete(item);

    }
}