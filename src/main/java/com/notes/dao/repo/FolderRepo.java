package com.notes.dao.repo;

import com.notes.dao.entites.Folder;
import com.notes.dao.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FolderRepo extends JpaRepository<Folder, Long> {

}
