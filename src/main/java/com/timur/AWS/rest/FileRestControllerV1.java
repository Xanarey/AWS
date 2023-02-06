package com.timur.AWS.rest;

import com.timur.AWS.dto.FileDto;
import com.timur.AWS.model.File;
import com.timur.AWS.service.FileService;
import com.timur.AWS.service.S3Service;
import com.timur.AWS.service.UserService;
import com.timur.AWS.utils.FileHelper;
import com.timur.AWS.utils.ConvertMultipart;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/files/")
public class FileRestControllerV1 {

    private final S3Service service;
    private final UserService userService;
    private final FileService fileService;

    private static String time = String.valueOf(LocalDateTime.now());

    @Autowired
    public FileRestControllerV1(S3Service service, UserService userService, FileService fileService) {
        this.service = service;
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('files:readAll')")
    public ResponseEntity<List<FileDto>> getAllFiles() {
        List<File> files = fileService.readAllFiles();

        if (files.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        List<FileDto> fileDtos = new ArrayList<>();

        for (File f: files)
            fileDtos.add(FileDto.fromFile(f));

        return new ResponseEntity<>(fileDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('files:download')")
    public ResponseEntity<String> downloadFile(@PathVariable @Validated Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<File> files = fileService.readAllFiles();

        if (files.isEmpty())
            new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String name = "";

        for (File f: files)
            if (f.getId() == id)
                name = f.getName();

        service.downloadFile(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('files:delete')")
    public ResponseEntity<String> deleteFile(@PathVariable @Validated Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<File> files = fileService.readAllFiles();

        if (files == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        if (id > files.get(files.size() - 1).getId())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        String name = "";

        for (File f: files)
            if (Objects.equals(f.getId(), id)) {
                name = f.getName();
                fileService.delete(f);
                service.deleteFile(name);
            }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('files:upload')")
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") MultipartFile multipartFile) {
        try {
            java.io.File file = ConvertMultipart.convertMultiPartToFile(multipartFile);
            String fileName = multipartFile.getOriginalFilename();
            service.uploadFile(file, fileName);
            FileHelper.updateFileInfoInDB(fileName, userService);
        } catch (IOException e) {
            System.out.printf("UploadController().uploadFile().Exception : " + e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>("timur.bucket" + "/" + multipartFile.getOriginalFilename(), HttpStatus.OK);
    }
}
