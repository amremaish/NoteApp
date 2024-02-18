package com.notes.controller.api;
import com.notes.dao.entites.Folder;
import com.notes.service.spec.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/folders")
public class FolderController {

    @Autowired
    private FolderService folderService;

    // GET all folders
    @GetMapping
    public ResponseEntity<List<Folder>> getAllFolders(Pageable pageable) {
        Page<Folder> folders = folderService.getAllFolders(pageable);
        List<Folder> folderList = folders.getContent();
        return ResponseEntity.ok().body(folderList);
    }

    // GET folder by ID
    @GetMapping("/{id}")
    public ResponseEntity<Folder> getFolderById(@PathVariable Long id) {
        Folder folder = folderService.getFolderById(id);
        if (folder != null) {
            return new ResponseEntity<>(folder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST create a new folder
    @PostMapping
    public ResponseEntity<Folder> createFolder(@RequestBody Folder folder) {
        Folder createdFolder = folderService.createFolder(folder);
        return new ResponseEntity<>(createdFolder, HttpStatus.CREATED);
    }

    // PUT update an existing folder
    @PutMapping("/{id}")
    public ResponseEntity<Folder> updateFolder(@PathVariable Long id, @RequestBody Folder folder) {
        Folder updatedFolder = folderService.updateFolder(id, folder);
        if (updatedFolder != null) {
            return new ResponseEntity<>(updatedFolder, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // DELETE delete a folder by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long id) {
        folderService.deleteFolder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
