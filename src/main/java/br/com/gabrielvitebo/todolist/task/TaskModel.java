package br.com.gabrielvitebo.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;
  private UUID user_id;
  private String description;

  @Column(length = 50)
  private String title;
  private LocalDateTime start_at;
  private LocalDateTime end_at;
  private String priority;

  @CreationTimestamp
  private LocalDateTime created_at;


}
