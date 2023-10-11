package br.com.slandrade.todolist.domain.service.impl;

import br.com.slandrade.todolist.domain.exception.UserAlreadyExistException;
import br.com.slandrade.todolist.domain.model.UserModel;
import br.com.slandrade.todolist.domain.repository.IUserRepository;
import br.com.slandrade.todolist.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final String USER_ALREADY_EXISTS = "User already exists.";

  private final IUserRepository repository;

  @Override
  public Optional<UserModel> getUserByUserName(final String username) {
    return repository.findByUsername(username);
  }

  @Override
  public UserModel createUser(final UserModel user) throws UserAlreadyExistException {

    if(getUserByUserName(user.getUsername()).isPresent()) {
      throw new UserAlreadyExistException(USER_ALREADY_EXISTS);
    }
    return repository.save(user);
  }

}
