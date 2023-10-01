package ru.werest.diplomacloudservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileListResponse {
    private String filename;
    private long size;
}
