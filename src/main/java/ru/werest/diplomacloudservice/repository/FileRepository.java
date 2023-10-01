package ru.werest.diplomacloudservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.werest.diplomacloudservice.entity.File;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File findFileByFilename(String filename);
}
