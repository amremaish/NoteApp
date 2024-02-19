package com.notes.controller.api;

import com.notes.dao.entites.Item;
import com.notes.dao.models.ItemModel;
import com.notes.dao.models.PageModel;
import com.notes.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    // GET all folders
    @GetMapping("/{folder_id}")
    public ResponseEntity<PageModel<Item>> getAllItems(
            @PathVariable long folder_id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageModel<Item> items = itemService.getAllItems(folder_id, page, size);
        return ResponseEntity.ok().body(items);
    }

    // GET folder by ID
    @GetMapping("/byId/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @GetMapping("/{item_id}/content")
    public Object getContent(@PathVariable long item_id) {
        return itemService.getContent(item_id);
    }

    // PUT create a new folder
    @PutMapping("/{folder_id}/create")
    public ResponseEntity<ItemModel> createItem(
            @PathVariable long folder_id,
            @ModelAttribute @Valid ItemModel itemModel,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
        ItemModel createdItem = itemService.createItem(itemModel, folder_id, image);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    // POST update an existing folder
    @PostMapping("/{item_id}/update")
    public ResponseEntity<ItemModel> updateItem(
            @PathVariable long item_id,
            @ModelAttribute @Valid ItemModel itemModel,
            @RequestParam(value = "image", required = false) MultipartFile image
    ) {
       ItemModel updatedItem = itemService.updateItem(item_id, itemModel, image);
       return new ResponseEntity<>(updatedItem, HttpStatus.OK);
    }

    // DELETE delete a folder by ID
    @DeleteMapping("/{id}")
    public Object deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
}
