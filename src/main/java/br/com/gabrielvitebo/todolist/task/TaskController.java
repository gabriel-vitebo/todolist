package br.com.gabrielvitebo.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/task")
public class TaskController {
  
  @Autowired
  private ITaskRepository taskRepository;

  @PostMapping("/")
  public ResponseEntity create(@RequestBody TaskModel taskModel, HttpServletRequest request) {
    var user_id = request.getAttribute("user_id");
    taskModel.setUser_id((UUID) user_id);

    var currentDate = LocalDateTime.now();

    if(currentDate.isAfter(taskModel.getStart_at()) || currentDate.isAfter(taskModel.getEnd_at())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("A data é inválida");
    }

    if(taskModel.getStart_at().isAfter(taskModel.getEnd_at())) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("A data de inicio deve ser menor que a data de témino");
    }

    var task = this.taskRepository.save(taskModel);
    return ResponseEntity.status(HttpStatus.OK).body(task);
  }
}
