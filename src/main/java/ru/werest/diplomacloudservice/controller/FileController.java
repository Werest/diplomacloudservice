package ru.werest.diplomacloudservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.werest.diplomacloudservice.request.ChangeFilenameRequest;
import ru.werest.diplomacloudservice.response.FileListResponse;
import ru.werest.diplomacloudservice.services.FileService;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static ru.werest.diplomacloudservice.global.Constants.FILE;

@RequestMapping("/")
@Slf4j
@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService service;

    @PostMapping(FILE)
    public void create(Principal user, @RequestParam("filename") String filename,
                       MultipartFile file) throws IOException {

        service.saveFile(user, filename, file);
        log.info("Файл сохранен!");
    }

    @DeleteMapping(FILE)
    public void delete(Principal user, @RequestParam("filename") String filename) {
        service.deleteFile(user, filename);
        log.info("Файл удалён!");
    }

    @GetMapping(FILE)
    public ResponseEntity<Resource> get(Principal user, @RequestParam("filename") String filename) {
        byte[] file = service.getFile(user, filename);
        return ResponseEntity.ok().body(new ByteArrayResource(file));
    }

    @PutMapping(FILE)
    public void put(Principal user, @RequestParam("filename") String filename, @RequestBody ChangeFilenameRequest request) {
        service.putFile(user, filename, request);
        log.info("Имя файла было измено!");
    }

    @GetMapping("/list")
    public List<FileListResponse> getFiles(@RequestParam(name = "limit", defaultValue = "10") Integer limit) {
        return service.getFiles(limit);
    }

}
