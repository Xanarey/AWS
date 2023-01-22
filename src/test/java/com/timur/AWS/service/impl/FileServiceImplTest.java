package com.timur.AWS.service.impl;

import com.timur.AWS.model.File;
import com.timur.AWS.repository.FileRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileServiceImplTest {

    @Mock
    private FileRepository fileRepository;
    private final FileServiceImpl fileService;

    public FileServiceImplTest() {
        MockitoAnnotations.openMocks(this);
        this.fileService = new FileServiceImpl(fileRepository);
    }

    @Test
    void readAllFiles() {
        List<File> fileList = new ArrayList<>();
        File one = new File();
        one.setName("one.txt");
        File two = new File();
        two.setName("two.txt");
        fileList.add(one);
        fileList.add(two);

        Mockito.when(fileRepository.findAll()).thenReturn(fileList);

        List<File> fileListService = fileService.readAllFiles();

        assertNotNull(fileListService);
        assertEquals(2, fileListService.size());
        assertEquals("two.txt", fileListService.get(1).getName());
    }

    @Test
    void save() {
        File one = new File();
        one.setName("one.txt");

        Mockito.when(fileRepository.save(one)).thenReturn(one);

        fileService.save(one);

        assertNotNull(one);
        assertEquals("one.txt", one.getName());
    }

    @Test
    void delete() {
        Mockito.doNothing().when(fileRepository).delete(new File());
        fileService.delete(new File());
        Mockito.verify(fileRepository).delete(new File());
    }

    @Test
    void update() {
        File one = new File();
        one.setName("one.txt");

        Mockito.when(fileRepository.save(one)).thenReturn(one);

        one.setName("two.txt");

        fileService.update(one);

        assertNotNull(one);
        assertEquals("two.txt", one.getName());
    }
}