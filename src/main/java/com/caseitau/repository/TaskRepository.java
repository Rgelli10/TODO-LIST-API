package com.caseitau.repository;

import com.caseitau.entity.StatusTask;
import com.caseitau.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository <Task, Long> {
    @Query(value = "SELECT t FROM Task t WHERE t.userId = ?1 AND t.status = ?2")
    List<Task> findByUserIdAndStatus(Long userId, StatusTask statusTask);
}
