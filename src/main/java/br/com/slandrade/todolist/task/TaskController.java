package br.com.slandrade.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.slandrade.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/tasks")
public class TaskController {
  
  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity createdTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    var userId = request.getAttribute("userId");
    taskModel.setUserId((UUID) userId);

    var currentDate = LocalDateTime.now();
    var startDate = taskModel.getStartAt();
    var endDate = taskModel.getEndAt();

    if(currentDate.isAfter(startDate) || currentDate.isAfter(endDate)) {
      return ResponseEntity.badRequest()
        .body("A data de início / término não pode ser anterior a data atual");
    }

    if(startDate.isAfter(endDate)) {
      return ResponseEntity.badRequest()
        .body("A data de início não pode ser posterior a data de término");
    }

    var task = taskRepository.save(taskModel);
    return ResponseEntity.ok().body(task);
  }

  @GetMapping("/")
  public List<TaskModel> list(HttpServletRequest request) {
    var userId = request.getAttribute("userId");
    return taskRepository.findByUserId((UUID) userId);
  }

  @PutMapping("/{id}")
  public ResponseEntity updateTask(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id) {

    var task = taskRepository.findById(id).orElse(null);

    if(task == null) {
      return ResponseEntity.badRequest()
        .body("Tarefa não encontrada");
    }

    var userId = request.getAttribute("userId");

    if(!task.getUserId().equals(userId)) {
      return ResponseEntity.badRequest()
        .body("Você não tem permissão para alterar essa tarefa");
    }

    Utils.copyNonNullProperties(taskModel, task);
    
    return ResponseEntity.ok().body(taskRepository.save(task));
  }
}