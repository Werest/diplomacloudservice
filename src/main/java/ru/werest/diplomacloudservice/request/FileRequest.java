package ru.werest.diplomacloudservice.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileRequest {
    private String hash;
    private MultipartFile file;
}
