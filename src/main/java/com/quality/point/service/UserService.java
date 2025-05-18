package com.quality.point.service;



import com.quality.point.exception.UserServiceException;
import com.quality.point.service.dto.UserRequestDto;
import com.quality.point.service.dto.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getUserList();

    UserResponseDto getUser(Long userId);

    UserResponseDto saveUser(UserRequestDto userRequestDto) throws UserServiceException;

    void deleteUser(Long userId);
}
