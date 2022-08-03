package com.desafio.springboot.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.springboot.exceptions.ResourceNotFoundException;
import com.desafio.springboot.models.User;
import com.desafio.springboot.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;
  
  @GetMapping
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/{id}")
  public User getUser(@PathVariable Long id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com id " +id+" não encontrado!"));
  }

  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public User storeUser(@RequestBody User user){
    return userRepository.save(user);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
    User currentUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuário com id " +id+" não encontrado!"));

    currentUser.setName(user.getName());
    currentUser.setCpf(user.getCpf());
    currentUser.setEmail(user.getEmail());

    userRepository.save(currentUser);
    
    return ResponseEntity.ok(currentUser);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
    try {
      userRepository.deleteById(id);
      return ResponseEntity.ok(id);
    } catch (Exception e) {
      throw new ResourceNotFoundException("Usuário com id " +id+" não encontrado!");
    }
    
  }
}
