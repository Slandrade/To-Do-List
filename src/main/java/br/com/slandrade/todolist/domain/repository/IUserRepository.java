package br.com.slandrade.todolist.domain.repository;

import java.util.Optional;
import java.util.UUID;

import br.com.slandrade.todolist.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID>{

  Optional<UserModel> findByUsername(String username);
}
