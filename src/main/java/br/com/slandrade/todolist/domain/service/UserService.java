package br.com.slandrade.todolist.domain.service;

import br.com.slandrade.todolist.domain.exception.UserAlreadyExistException;
import br.com.slandrade.todolist.domain.model.UserModel;

import java.util.Optional;

public interface UserService {
  Optional<UserModel> getUserByUserName(String username) throws UserAlreadyExistException;
  UserModel createUser(UserModel userModel) throws UserAlreadyExistException;
}
