package ru.werest.diplomacloudservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileListResponse {
    private String filename;
    private long size;
}
