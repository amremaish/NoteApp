package com.notes.service.services;

import com.notes.dao.entites.Item;
import com.notes.dao.models.ItemModel;
import com.notes.dao.models.PageModel;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {

    PageModel<Item> getAllItems(long folder_id, int page, int size);

    Item getItemById(Long id);

    ItemModel createItem(ItemModel itemModel, long folder_id, MultipartFile image);

    ItemModel updateItem(Long id, ItemModel itemModel, MultipartFile image);

    void deleteItem(Long id);

}