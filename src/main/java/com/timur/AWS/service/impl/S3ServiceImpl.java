package com.timur.AWS.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.timur.AWS.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public final class S3ServiceImpl implements S3Service {

    private final AmazonS3 s3client;
    private final String BUCKET_NAME = "timur.bucket";
    private final UserService userService;

    public void createBucket() {

        if (s3client.doesBucketExistV2(BUCKET_NAME)) {
            log.info("Bucket {} already exists, use a different name", BUCKET_NAME);
            return;
        }

        s3client.createBucket(BUCKET_NAME);
    }

    public void listBuckets(){
        List<Bucket> buckets = s3client.listBuckets();
        log.info("buckets: {}", buckets);
    }

    public Map<Long, String> viewAllUploadFiles() {
        ObjectListing objects = s3client.listObjects(BUCKET_NAME);
        Map<Long, String> objectSummaryMap = new HashMap<>();
        long i = 1;
        for(S3ObjectSummary objectSummary : objects.getObjectSummaries()) {
            objectSummaryMap.put(i++, objectSummary.getKey());
            log.info("File name: {}", objectSummary);
        }
        return objectSummaryMap;
    }

    @SneakyThrows
    public void downloadFile(String filename) {
        S3Object s3object = s3client.getObject(BUCKET_NAME, filename);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        File file = new File("download/" + filename);
        FileCopyUtils.copy(inputStream, new FileOutputStream(file));
        log.info("File: {} ", filename + " - is download");
    }

    public void deleteFile(String filename) {
        s3client.deleteObject(BUCKET_NAME,filename);
        
        log.info("File: {} ", filename + " - is deleted");
    }

    @SneakyThrows
    public String uploadFile(File file, String fileName) {
        s3client.putObject(
                new PutObjectRequest(BUCKET_NAME, fileName, file).withCannedAcl(CannedAccessControlList.PublicReadWrite)
        );
        log.info("File: {} ", fileName + " - is upload");

        return "Uploading Successfully -> ";
    }
}
