package com.example.fourthhomework.service;

import com.example.fourthhomework.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(Long id);
    UserDTO save(UserDTO userDTO);
    UserDTO update(UserDTO userDTO, Long id);
    void deleteById(Long id);
}
