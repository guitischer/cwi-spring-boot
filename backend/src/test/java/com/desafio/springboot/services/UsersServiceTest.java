package com.desafio.springboot.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;

import com.desafio.springboot.models.User;

import com.desafio.springboot.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UsersServiceTest {

  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Test
  void getUsersSuccess() {
    User user1 = User.builder().name("Dummy 1").cpf("00000000001").email("dummy1@dummy1.com").build();

    User user2 = User.builder().name("Dummy 2").cpf("00000000002").email("dummy2@dummy2.com").build();

    List<User> usersList = new ArrayList<>(Arrays.asList(user1, user2));
    Mockito.when(userRepository.findAll()).thenReturn(usersList);

    List<User> users = userService.getUsers();
    Mockito.verify(userRepository).findAll();

    assert (users == usersList);
  }

  @Test
  void getUserAllArgsSuccess() {
    Long id = new Random().nextLong();

    User userMock = User.builder().id(id).name("Dummy 1").cpf("00000000001").email("dummy1@dummy1.com").build();
    Mockito.when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(userMock));

    User user = userService.getUser(id);
    Mockito.verify(userRepository).findById(id);

    assert (user.getId() == id);
  }

  // @Test
  // void saveUser() {

  // }

  // @Test
  // void updateUser() {

  // }

  // @Test
  // void deleteUser() {

  // }

}
