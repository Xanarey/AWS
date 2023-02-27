package com.timur.AWS.service.impl;

import java.io.File;

public interface S3Service {

    void createBucket();

    void listBuckets();

    void downloadFile(String filename);

    void deleteFile(String filename);

    String uploadFile(File file, String fileName);
}
