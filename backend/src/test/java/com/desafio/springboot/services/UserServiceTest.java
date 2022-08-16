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
    User user1 = getUserAllArgs();
    User user2 = getUserAllArgs();

    List<User> usersList = new ArrayList<>(Arrays.asList(user1, user2));
    Mockito.when(userRepository.findAll()).thenReturn(usersList);

    List<User> users = userService.getUsers();
    Mockito.verify(userRepository).findAll();

    assert (users == usersList);
  }

  @Test
  void getUser_Success() {
    User userMock = getUserAllArgs();
    Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userMock));

    User user = userService.getUser(userMock.getId());
    Mockito.verify(userRepository).findById(userMock.getId());

    assert (user.getId() == userMock.getId());
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
    Assertions.assertThrows(MissingParameterException.class, () -> {
      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(getUserAllArgs(), userDTO);
      userDTO.setEmail(null);

      userService.saveUser(userDTO);
      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  @Test
  void saveUserWithInvalidEmail_Fail() {
    Assertions.assertThrows(InvalidParameterException.class, () -> {
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

  @Test
  void updateUserAllArgs_Success() {
    User userMock = getUserAllArgs();
    Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userMock));

    User user = userService.getUser(userMock.getId());
    Mockito.verify(userRepository).findById(userMock.getId());

    UserDTO userDTO = new UserDTO();
    BeanUtils.copyProperties(user, userDTO);
    userDTO.setName("Dummy 2");
    userDTO.setCpf("00000000002");
    userDTO.setEmail("dummy2@dummy2.com");

    userService.updateUser(user.getId(), userDTO);
    Mockito.verify(userRepository).save(any(User.class));
  }

  @Test
  void updateUserWithNullName_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      User userMock = getUserAllArgs();
      Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userMock));

      User user = userService.getUser(userMock.getId());
      Mockito.verify(userRepository).findById(userMock.getId());

      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(user, userDTO);
      userDTO.setName(null);
      userDTO.setCpf("00000000002");
      userDTO.setEmail("dummy2@dummy2.com");

      userService.updateUser(user.getId(), userDTO);
      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  @Test
  void updateUserWithNullCpf_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      User userMock = getUserAllArgs();
      Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userMock));

      User user = userService.getUser(userMock.getId());
      Mockito.verify(userRepository).findById(userMock.getId());

      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(user, userDTO);
      userDTO.setName("Dummy 2");
      userDTO.setCpf(null);
      userDTO.setEmail("dummy2@dummy2.com");

      userService.updateUser(user.getId(), userDTO);
      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  @Test
  void updateUserWithNullEmail_Fail() {
    Assertions.assertThrows(MissingParameterException.class, () -> {
      User userMock = getUserAllArgs();
      Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userMock));

      User user = userService.getUser(userMock.getId());
      Mockito.verify(userRepository).findById(userMock.getId());

      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(user, userDTO);
      userDTO.setName("Dummy 2");
      userDTO.setCpf("00000000002");
      userDTO.setEmail(null);

      userService.updateUser(user.getId(), userDTO);
      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  @Test
  void updateUserWithInvalidEmail_Fail() {
    Assertions.assertThrows(InvalidParameterException.class, () -> {
      User userMock = getUserAllArgs();
      Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userMock));

      User user = userService.getUser(userMock.getId());
      Mockito.verify(userRepository).findById(userMock.getId());

      UserDTO userDTO = new UserDTO();
      BeanUtils.copyProperties(user, userDTO);
      userDTO.setName("Dummy 2");
      userDTO.setCpf("00000000002");
      userDTO.setEmail("dummy2.dummy2.com");

      userService.updateUser(user.getId(), userDTO);
      Mockito.verify(userRepository, Mockito.never()).save(any(User.class));
    });
  }

  @Test
  void deleteUser_Success() {
    User user = getUserAllArgs();

    userService.deleteUser(user.getId());
    Mockito.verify(userRepository).deleteById(any(Long.class));
  }

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
