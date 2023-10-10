package br.com.slandrade.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private IUserRepository userRepository;

  @PostMapping("/")
  public ResponseEntity<Object> createUser(@RequestBody UserModel userModel) {
    var user = userRepository.findByUsername(userModel.getUsername());

    if(user != null) {
      return ResponseEntity.badRequest().body("Usuário já existe!");
    }

    var userCreated = userRepository.save(userModel);
    return ResponseEntity.ok(userCreated);
  }
}
