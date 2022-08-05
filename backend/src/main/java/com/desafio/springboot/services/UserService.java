package com.desafio.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.exceptions.ResourceNotFoundException;
import com.desafio.springboot.models.User;
import com.desafio.springboot.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User getUser(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + id + " não encontrado!"));
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public void updateUser(Long id, User user) {
    User currentUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + id + " não encontrado!"));

    currentUser.setName(user.getName());
    currentUser.setCpf(user.getCpf());
    currentUser.setEmail(user.getEmail());

    userRepository.save(currentUser);
  }

  public void deleteUser(Long id) {
    try {
      userRepository.deleteById(id);
    } catch (Exception e) {
      throw new ResourceNotFoundException("Usuário com id " + id + " não encontrado!");
    }

  }
}
