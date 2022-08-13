package com.desafio.springboot.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.dtos.UserDTO;
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

  public void saveUser(UserDTO userDTO) {
    var user = new User();
    BeanUtils.copyProperties(userDTO, user);
    userRepository.save(user);
  }

  public void updateUser(Long id, UserDTO userDTO) {
    User currentUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + id + " não encontrado!"));
    BeanUtils.copyProperties(userDTO, currentUser);
    userRepository.save(currentUser);
  }

  public void deleteUser(Long id) {
    try {
      userRepository.deleteById(id);
    } catch (Exception e) {
      throw new ResourceNotFoundException("Usuário com id " + id + " não encontrado!");
    }
  }

  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }
}
