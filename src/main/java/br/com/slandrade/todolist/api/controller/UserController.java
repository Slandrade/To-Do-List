package br.com.slandrade.todolist.api.controller;

import br.com.slandrade.todolist.domain.exception.UserAlreadyExistException;
import br.com.slandrade.todolist.domain.model.UserModel;
import br.com.slandrade.todolist.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/")
  public ResponseEntity<Object> createUser(@RequestBody final UserModel userModel)
      throws UserAlreadyExistException {
    var userCreated = userService.createUser(userModel);

    return ResponseEntity.ok(userCreated);
  }
}
