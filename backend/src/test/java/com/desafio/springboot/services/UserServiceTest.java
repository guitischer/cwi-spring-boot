package com.desafio.springboot.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafio.springboot.dtos.UserDTO;
import com.desafio.springboot.exceptions.InvalidParameterException;
import com.desafio.springboot.exceptions.MissingParameterException;
import com.desafio.springboot.models.User;

import com.desafio.springboot.repositories.UserRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Test
  void getUsers_Success() {
    User user1 = User.builder().name("Dummy 1").cpf("00000000001").email("dummy1@dummy1.com").build();

    User user2 = User.builder().name("Dummy 2").cpf("00000000002").email("dummy2@dummy2.com").build();

    List<User> usersList = new ArrayList<>(Arrays.asList(user1, user2));
    Mockito.when(userRepository.findAll()).thenReturn(usersList);

    List<User> users = userService.getUsers();
    Mockito.verify(userRepository).findAll();

    assert (users == usersList);
  }

  @Test
  void getUser_Success() {
    Long id = new Random().nextLong();

    User userMock = User.builder().id(id).name("Dummy 1").cpf("00000000001").email("dummy1@dummy1.com").build();
    Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userMock));

    User user = userService.getUser(id);
    Mockito.verify(userRepository).findById(id);

    assert (user.getId() == id);
  }

  @Test
  void saveUserAllArgs_Success() {
    UserDTO userDTO = new UserDTO();
    BeanUtils.copyProperties(getUserAllArgs(), userDTO);

    userService.saveUser(userDTO);

    Mockito.verify(userRepository).save(any(User.class));
  }

  @Test
  void saveUserWithoutName_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(getUserAllArgs(), userDTO);
      userDTO.setName(null);

      userService.saveUser(userDTO);

      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  @Test
  void saveUserWithoutEmail_Fail() {
    Assertions.assertThrows(InvalidParameterException.class, () -> {
      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(getUserAllArgs(), userDTO);
      userDTO.setEmail(null);

      userService.saveUser(userDTO);

      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  @Test
  void saveUserWithInvalidEmail_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(getUserAllArgs(), userDTO);
      userDTO.setEmail("dummy.dummy.com");

      userService.saveUser(userDTO);

      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  @Test
  void saveUserWithoutCpf_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(getUserAllArgs(), userDTO);
      userDTO.setCpf(null);

      userService.saveUser(userDTO);

      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  // @Test
  // void updateUser() {

  // }

  // @Test
  // void deleteUser() {

  // }

  private static User getUserAllArgs() {
    return User
        .builder()
        .id(new Random().nextLong())
        .name("Dummy")
        .email("dummy@dummy.com")
        .cpf("00000000001")
        .build();
  }

}
