package ru.werest.diplomacloudservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.werest.diplomacloudservice.entity.File;
import ru.werest.diplomacloudservice.repository.FileRepository;
import ru.werest.diplomacloudservice.request.ChangeFilenameRequest;
import ru.werest.diplomacloudservice.response.FileListResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileService {

    private final FileRepository repository;

    public FileService(FileRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public void saveFile(String authToken, String filename, MultipartFile multipartFile) throws IOException {
        File file = new File();
        file.setFilename(filename);
        file.setFile(multipartFile.getBytes());
        file.setSize(multipartFile.getSize());
        file.setCreateDate(LocalDateTime.now());

        repository.save(file);
    }

    @Transactional
    public void deleteFile(String filename) {
        File file = repository.findFileByFilename(filename);
        if (file == null) {
            log.error("Файла не существует!");
            throw new RuntimeException("Файла не существует!");
        }
        repository.delete(file);
    }

    public byte[] getFile(String filename) {
        File file = repository.findFileByFilename(filename);
        if (file == null) {
            log.error("Файла не существует!");
            throw new RuntimeException("Файла не существует!");
        }
        return file.getFile();
    }

    @Transactional
    public void putFile(String filename, ChangeFilenameRequest request) {
        File file = repository.findFileByFilename(filename);
        if (file == null) {
            log.error("Файла не существует!");
            throw new RuntimeException("Файла не существует!");
        }
        file.setFilename(request.getFilename());
        repository.save(file);
    }

    public List<FileListResponse> getFiles(Integer limit) {
        List<File> fileList = repository.findAll();
        return fileList.stream().map(f -> new FileListResponse(f.getFilename(), f.getSize()))
                .collect(Collectors.toList());
    }
}
