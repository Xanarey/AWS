package com.timur.AWS.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ReadFile {

    private static final String K_1 = "C:/Users/Пользователь/Desktop/aws1.txt";
    private static final String K_2 = "C:/Users/Пользователь/Desktop/aws2.txt";

    public static String getK_1() {
        return getString(K_1);
    }

    public static String getK_2() {
        return getString(K_2);
    }

    private static String getString(String k) {
        String value;
        try (Stream<String> stream = Files.lines(Paths.get(k))) {
            Optional<String> optional = stream.findFirst();
            value = optional.orElse(null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
