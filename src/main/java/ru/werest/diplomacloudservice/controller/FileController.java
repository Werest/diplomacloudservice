package ru.werest.diplomacloudservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.werest.diplomacloudservice.dto.ChangeFilenameRequest;
import ru.werest.diplomacloudservice.dto.FileListResponse;
import ru.werest.diplomacloudservice.services.file.FileService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService service;

    @PostMapping("/file")
    public ResponseEntity<Void> create(Principal user, @RequestParam("filename") String filename,
                                       MultipartFile file) throws IOException {

        service.saveFile(user, filename, file);
        log.info("Файл загружен!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/file")
    public ResponseEntity<Void> delete(Principal user, @RequestParam("filename") String filename) {
        service.deleteFile(user, filename);
        log.info("Файл удалён!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/file")
    public ResponseEntity<Resource> get(Principal user, @RequestParam("filename") String filename) {
        byte[] file = service.getFile(user, filename);
        return ResponseEntity.ok().body(new ByteArrayResource(file));
    }

    @PutMapping("/file")
    public ResponseEntity<Void> put(Principal user, @RequestParam("filename") String filename, @RequestBody ChangeFilenameRequest request) {
        service.putFile(user, filename, request);
        log.info("Имя файла было изменено!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<FileListResponse> getFiles(@RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return service.getFiles(limit);
    }

}
