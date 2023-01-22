package com.timur.AWS.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.timur.AWS.model.File;
import com.timur.AWS.model.Status;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FileDto {

    private Long id;
    private String name;
    private Status status;

    public static FileDto fromFile(File file) {
        FileDto dto = new FileDto();
        dto.setId(file.getId());
        dto.setName(file.getName());
        dto.setStatus(file.getStatus());
        return dto;
    }
}
