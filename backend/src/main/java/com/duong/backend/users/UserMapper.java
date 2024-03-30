package com.duong.backend.users;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDto mapToUserDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public User mapToUser(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
