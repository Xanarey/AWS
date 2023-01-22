package com.timur.AWS.service;

import com.timur.AWS.model.File;

import java.util.List;

public interface FileService {

    List<File> readAllFiles();

    void save(File file);

    void delete(File file);

    void update(File file);

}