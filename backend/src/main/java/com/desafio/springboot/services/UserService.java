package com.desafio.springboot.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.springboot.dtos.UserDTO;
import com.desafio.springboot.exceptions.InvalidParameterException;
import com.desafio.springboot.exceptions.MissingParameterException;
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
    requiredFieldsValidation(user);
    userRepository.save(user);
  }

  public void updateUser(Long id, UserDTO userDTO) {
    User currentUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuário com id " + id + " não encontrado!"));
    BeanUtils.copyProperties(userDTO, currentUser);
    requiredFieldsValidation(currentUser);
    userRepository.save(currentUser);
  }

  public void deleteUser(Long id) {
    try {
      userRepository.deleteById(id);
    } catch (Exception e) {
      throw new ResourceNotFoundException("Usuário com id " + id + " não encontrado!");
    }
  }

  private void requiredFieldsValidation(User user) {
    if (user.getName() == null || user.getName().equals("")) {
      throw new MissingParameterException("Campo 'Nome' é obrigatório!");
    }

    if (user.getCpf() == null || user.getCpf().equals("")) {
      throw new MissingParameterException("Campo 'CPF' é obrigatório!");
    }

    if (user.getEmail() == null || user.getEmail().equals("")) {
      throw new MissingParameterException("Campo 'E-mail' é obrigatório!");
    }

    if (!user.getEmail().contains("@")) {
      throw new InvalidParameterException("E-mail inválido!");
    }

  }
}
