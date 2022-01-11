package com.example.fourthhomework.service.impl;

import com.example.fourthhomework.converter.UserConverter;
import com.example.fourthhomework.dto.UserDTO;
import com.example.fourthhomework.entity.User;
import com.example.fourthhomework.exception.UserIsNotExistException;
import com.example.fourthhomework.repository.UserRepository;
import com.example.fourthhomework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserDTO> findAll() {
        var users = userRepository.findAll();
        var userDTOs = UserConverter.INSTANCE.convertAllUsersToUserDTOs(users);
        return userDTOs;
    }

    @Override
    public UserDTO findById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!(optionalUser.isPresent())) {
            throw new UserIsNotExistException(("The user with " + id + " id number is not found!"));
        }
        return UserConverter.INSTANCE.convertUserToUserDTO(optionalUser.get());
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        var user = UserConverter.INSTANCE.convertUserDTOToUser(userDTO);
        userRepository.save(user);
        return UserConverter.INSTANCE.convertUserToUserDTO(user);
    }

    @Override
    public UserDTO update(UserDTO userDTO, Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (Objects.isNull(user)) {
            throw new UserIsNotExistException(("The user with " + userDTO.getId() + " id number is not found!"));
        }
        user.setId(id);
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setUsername(userDTO.getUsername());
        user = userRepository.save(user);
        return UserConverter.INSTANCE.convertUserToUserDTO(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id).orElseThrow(() -> new UserIsNotExistException("The user with " + id + " id number is not found"));
        userRepository.deleteById(id);
    }
}
