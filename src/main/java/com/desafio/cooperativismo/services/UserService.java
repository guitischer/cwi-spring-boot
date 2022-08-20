package com.desafio.cooperativismo.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cooperativismo.dtos.UserDTO;
import com.desafio.cooperativismo.enums.ErrorMessageEnum;
import com.desafio.cooperativismo.exceptions.InvalidParameterException;
import com.desafio.cooperativismo.exceptions.MissingParameterException;
import com.desafio.cooperativismo.exceptions.ResourceNotFoundException;
import com.desafio.cooperativismo.models.User;
import com.desafio.cooperativismo.repositories.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public User getUser(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageEnum.USER_NOT_FOUND));
  }

  public void saveUser(UserDTO userDTO) {
    var user = new User();
    BeanUtils.copyProperties(userDTO, user);

    requiredFieldsValidation(user);
    checkIfThereIsUserWithSameCPF(user.getCpf());

    userRepository.save(user);
  }

  public void updateUser(Long id, UserDTO userDTO) {
    User currentUser = userRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageEnum.USER_NOT_FOUND));
    BeanUtils.copyProperties(userDTO, currentUser);
    requiredFieldsValidation(currentUser);
    userRepository.save(currentUser);
  }

  public void deleteUser(Long id) {
    try {
      userRepository.deleteById(id);
    } catch (Exception e) {
      throw new IllegalArgumentException(ErrorMessageEnum.USER_NOT_FOUND.getMessage());
    }
  }

  private void checkIfThereIsUserWithSameCPF(String cpf) {
    List<User> users = userRepository.findByCpf(cpf);

    if (users.size() > 0) {
      throw new InvalidParameterException(ErrorMessageEnum.CPF_IN_USE.getMessage());
    }
  }

  private void requiredFieldsValidation(User user) {
    if (user.getName() == null || user.getName().equals("")) {
      throw new MissingParameterException(ErrorMessageEnum.REQUIRED_NAME_FIELD);
    }

    if (user.getCpf() == null || user.getCpf().equals("")) {
      throw new MissingParameterException(ErrorMessageEnum.REQUIRED_CPF_FIELD);
    }
  }
}
