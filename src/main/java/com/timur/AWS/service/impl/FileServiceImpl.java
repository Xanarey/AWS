package com.timur.AWS.service.impl;

import com.timur.AWS.model.File;
import com.timur.AWS.repository.FileRepository;
import com.timur.AWS.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public List<File> readAllFiles() {
        log.info("IN FileServiceImpl readAllFiles");
        return fileRepository.findAll();
    }

    @Override
    public void save(File file) {
        log.info("IN FileServiceImpl upload {}", file);
        fileRepository.save(file);
    }

    @Override
    public void delete(File file) {
        log.info("IN FileServiceImpl deleteFileByName {}", file.getName());
        fileRepository.delete(file);
    }

    @Override
    public void update(File file) {
        log.info("IN FileServiceImpl update {}", file);
        fileRepository.save(file);
    }
}
