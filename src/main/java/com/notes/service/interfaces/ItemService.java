package com.notes.service.interfaces;

import com.notes.dao.entites.Item;
import com.notes.dao.models.ItemModel;
import com.notes.dao.models.PageModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface ItemService {

    PageModel<Item> getAllItems(long folder_id, int page, int size);

    Item getItemById(Long id);
    Object getContent(Long id);

    ItemModel createItem(ItemModel itemModel, long folder_id, MultipartFile image);

    ItemModel updateItem(Long id, ItemModel itemModel, MultipartFile image);

    void deleteItem(Long id);

}