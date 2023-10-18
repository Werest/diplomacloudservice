package ru.werest.diplomacloudservice.services.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.werest.diplomacloudservice.entity.File;
import ru.werest.diplomacloudservice.entity.User;
import ru.werest.diplomacloudservice.exception.FileException;
import ru.werest.diplomacloudservice.exception.FileNotExistException;
import ru.werest.diplomacloudservice.exception.MissingValueException;
import ru.werest.diplomacloudservice.repository.FileRepository;
import ru.werest.diplomacloudservice.repository.UserRepository;
import ru.werest.diplomacloudservice.dto.ChangeFilenameRequest;
import ru.werest.diplomacloudservice.dto.FileListResponse;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileRepository repository;

    private final UserRepository userRepository;

    public User findUserByName(Principal user) {
        User userEntity = userRepository.findUserByUsername(user.getName());
        if (userEntity == null) {
            throw new RuntimeException("Неверно передан пользователь!");
        }
        return userEntity;
    }

    @Transactional
    public void saveFile(Principal user, String filename, MultipartFile multipartFile) throws IOException {
        validFileName(filename);
        User userEntity = findUserByName(user);

        if (repository.existsAllByFilenameAndUser(filename, userEntity)) {
            log.error("Файла существует! " + filename);
            throw new FileException("Файла существует! " + filename + " нужно переименовать!");
        }

        File file = new File();
        file.setFilename(filename);
        file.setFile(multipartFile.getBytes());
        file.setSize(multipartFile.getSize());
        file.setCreateDate(LocalDateTime.now());
        file.setUser(userEntity);

        repository.save(file);
    }

    @Transactional
    public void deleteFile(Principal user, String filename) {
        validFileName(filename);
        User userEntity = findUserByName(user);

        File file = repository.findFileByFilenameAndUser(filename, userEntity);
        if (file == null) {
            log.error("Файла не существует!");
            throw new RuntimeException("Файла не существует!");
        }
        repository.delete(file);
    }

    public byte[] getFile(Principal user, String filename) {
        User userEntity = findUserByName(user);

        File file = repository.findFileByFilenameAndUser(filename, userEntity);
        if (file == null) {
            log.error("Файла не существует!");
            throw new RuntimeException("Файла не существует!");
        }
        return file.getFile();
    }

    @Transactional
    public void putFile(Principal user, String filename, ChangeFilenameRequest request) {
        validFileName(request.getFilename());
        User userEntity = findUserByName(user);

        File file = repository.findFileByFilenameAndUser(filename, userEntity);
        if (file == null) {
            log.error("Файла не существует!");
            throw new FileNotExistException("Файла не существует!");
        }
        file.setFilename(request.getFilename());
        repository.save(file);
    }

    public List<FileListResponse> getFiles(Integer limit) {
        List<File> fileList = repository.findAll();
        return fileList.stream().map(f -> new FileListResponse(f.getFilename(), f.getSize()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    public void validFileName(String filename) {
        if (filename == null || filename.isEmpty()) {
            log.error("Не указано имя файла! Укажите имя файла!");
            throw new MissingValueException("Не указано имя файла! Укажите имя файла!");
        }
    }
}
