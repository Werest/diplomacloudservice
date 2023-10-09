package ru.werest.diplomacloudservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Table(name = "TFILE", schema = "NETOLOGY")
@Data
@DynamicUpdate
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "size")
    private Long size;

    @Column(name = "file", nullable = false)
    private byte[] file;

    @ManyToOne
    private User user;
}
