package com.notes.controller.api;
import com.notes.dao.entites.Folder;
import com.notes.dao.models.FolderModel;
import com.notes.dao.models.PageModel;
import com.notes.service.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    @Autowired
    private FolderService folderService;

    // GET all folders
    @GetMapping
    public ResponseEntity<PageModel<Folder>> getAllFolders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageModel<Folder> folders = folderService.getAllFolders(page, size);
        return ResponseEntity.ok().body(folders);
    }

    // GET folder by ID
    @GetMapping("/{id}")
    public ResponseEntity<Folder> getFolderById(@PathVariable Long id) {
        Folder folder = folderService.getFolderById(id);
            return new ResponseEntity<>(folder, HttpStatus.OK);
    }

    // PUT create a new folder
    @PutMapping("/create")
    public ResponseEntity<Object> createFolder(@Valid @RequestBody FolderModel folderModel) {
        FolderModel createdFolder = folderService.createFolder(folderModel);
        return new ResponseEntity<>(createdFolder, HttpStatus.CREATED);
    }

    // POST update an existing folder
    @PostMapping("/{id}/edit")
    public ResponseEntity<Object> updateFolder(@PathVariable Long id, @RequestBody FolderModel folderModel) {
       FolderModel updatedFolder = folderService.updateFolder(id, folderModel);
        return new ResponseEntity<>(updatedFolder, HttpStatus.OK);
    }

    // DELETE delete a folder by ID
    @DeleteMapping("/{id}")
    public Object deleteFolder(@PathVariable Long id) {
        folderService.deleteFolder(id);
        return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
    }
}
