package com.caseitau.dto;

import com.caseitau.entity.StatusTask;
import com.caseitau.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class TaskDto {

    private Long id;

    private User userId;

    @Column(nullable = false)
    private String resumeDescription;

    @Column(nullable = false)
    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @UpdateTimestamp
    private LocalDateTime updatedAt = LocalDateTime.now();

    private String status = String.valueOf(StatusTask.PENDING);
}
