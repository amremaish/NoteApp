package com.notes.dao.repo;

import com.notes.dao.entites.Folder;
import com.notes.dao.entites.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepo extends JpaRepository<Folder, Long> {
    Folder findFirstByFolderNameAndUserId(String folderName, Long userId);
    Folder findFirstByFolderNameAndUserIdAndIdNot(String folderName, Long userId, Long id);
    Page<Folder> findByUserId(long userId, Pageable pageable);


}
