package ru.werest.diplomacloudservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.werest.diplomacloudservice.entity.File;
import ru.werest.diplomacloudservice.entity.User;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    File findFileByFilenameAndUser(String filename, User user);

    Boolean existsAllByFilenameAndUser(String filename, User user);
}
