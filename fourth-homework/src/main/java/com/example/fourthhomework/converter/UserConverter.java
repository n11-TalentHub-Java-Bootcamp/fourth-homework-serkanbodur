package com.example.fourthhomework.converter;

import com.example.fourthhomework.dto.UserDTO;
import com.example.fourthhomework.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    List<UserDTO> convertAllUsersToUserDTOs(List<User> user);

    UserDTO convertUserToUserDTO(User user);

    User convertUserDTOToUser(UserDTO userDTO);
}
