package com.notes.dao.repo;

import com.notes.dao.entites.Folder;
import com.notes.dao.entites.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    Item findFirstByContentAndUserIdAndFolderId(String content, Long userId, Long folderId);

    Page<Item> findByUserIdAndFolderId(long userId, long folder_id, Pageable pageable);
}
