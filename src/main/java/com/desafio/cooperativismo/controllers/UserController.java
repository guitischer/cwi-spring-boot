package com.desafio.cooperativismo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.cooperativismo.dtos.UserDTO;
import com.desafio.cooperativismo.models.User;
import com.desafio.cooperativismo.services.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
@Api(value = "Endpoints de Usuário")
public class UserController {

  @Autowired
  private UserService userService;

  @ApiOperation(value = "Listagem dos usuários")
  @GetMapping
  public ResponseEntity<List<User>> list() {
    List<User> users = userService.getUsers();
    return ResponseEntity.ok(users);
  }

  @ApiOperation(value = "Listar um usuário")
  @GetMapping("/{id}")
  public ResponseEntity<User> get(@PathVariable Long id) {
    User user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @ApiOperation(value = "Criação de usuário")
  @PostMapping
  public ResponseEntity<User> save(@RequestBody @Valid UserDTO userDTO) {
    userService.saveUser(userDTO);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @ApiOperation(value = "Alteração de usuário")
  @PutMapping("/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
    userService.updateUser(id, userDTO);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @ApiOperation(value = "Remoção de usuário")
  @DeleteMapping("/{id}")
  public ResponseEntity<Long> delete(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(id);
  }
}
