package br.com.slandrade.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tb_tasks")
public class TaskModel {
  
  @Id
  @GeneratedValue(generator = "UUID")
  private UUID id;

  @Column(length = 50)
  private String title;
  private String description;
  private String priority;
  private LocalDateTime startAt;
  private LocalDateTime endAt;
  private UUID userId;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public void setTitle(String title) throws Exception {
    if(title.length() > 50) {
      throw new Exception("O título tem um limite de 50 caracteres");
    }
    this.title = title;
  }
}
