package com.example.fourthhomework.controller;

import com.example.fourthhomework.dto.UserDTO;
import com.example.fourthhomework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> findAll() {
        var userDTOs = userService.findAll();
        return new ResponseEntity<>(userDTOs, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        var responseUserDTO = userService.save(userDTO);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        var updateUserDTO = userService.update(userDTO, id);
        return new ResponseEntity<>(updateUserDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam Long id) {
        userService.deleteById(id);
        return new ResponseEntity<>("The user was deleted successfully", HttpStatus.OK);
    }
}
