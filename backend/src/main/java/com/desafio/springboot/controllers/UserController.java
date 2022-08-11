package com.desafio.springboot.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
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

import com.desafio.springboot.dtos.UserDTO;
import com.desafio.springboot.models.User;
import com.desafio.springboot.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping
  public ResponseEntity<List<User>> list() {
    List<User> users = userService.getUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> get(@PathVariable Long id) {
    User user = userService.getUser(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping
  public ResponseEntity<User> save(@RequestBody @Valid UserDTO userDTO) {
    var user = new User();
    BeanUtils.copyProperties(userDTO, user);
    userService.saveUser(user);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
    Optional<User> userOptional = userService.findById(id);

    if (!userOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    var user = new User();
    BeanUtils.copyProperties(userDTO, user);
    user.setId(id);
    userService.updateUser(id, user);
    return ResponseEntity.status(HttpStatus.ACCEPTED).build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> delete(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.ok(id);
  }
}
