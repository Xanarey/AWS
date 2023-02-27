package com.timur.AWS.repository;

import com.timur.AWS.model.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    File findByName(String name);
}
