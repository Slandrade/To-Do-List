package br.com.slandrade.todolist.domain.exception.custom;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomErrorResponse {
  private LocalDateTime timestamp;
  private int           status;
  private String        error;
  private String        message;
  private String        path;
}
