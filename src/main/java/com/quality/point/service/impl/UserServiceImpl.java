package com.quality.point.service.impl;

import com.quality.point.constant.ErrorDetails;
import com.quality.point.exception.UserServiceException;
import com.quality.point.repository.UserRepository;
import com.quality.point.repository.entity.User;
import com.quality.point.service.UserService;
import com.quality.point.service.adaptor.UserAdaptor;
import com.quality.point.service.dto.UserRequestDto;
import com.quality.point.service.dto.UserResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserAdaptor userAdaptor;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserResponseDto> getUserList() {
        log.info("Getting address list from DB ");
        List<User> users = userRepository.findAll();
        log.info("Now converting address entity into dto");
        List<UserResponseDto> employeeDtos = userAdaptor.mapOneTypeListToAnotherType(users, UserResponseDto.class);
        log.info("After converting entity to dto then return a dto list with size: {}", employeeDtos.size());
        return employeeDtos;
    }

    @Override
    public UserResponseDto getUser(Long userId) {
        log.info("Getting address detail from DB with ID: {}", userId);
        Optional<User> userOptional = userRepository.findById(userId);
        log.info("Fetched address detail successfully from DB");
        User user = userOptional.orElseGet(User::new);
        log.info("Map address entity to dto and return data with ID: {}", userId);
        return (UserResponseDto) userAdaptor.mapOneObjectToAnother(user, UserResponseDto.class);
    }

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) throws UserServiceException {
        log.info("Map user dto into entity data is: {}", userRequestDto);
        User user = (User) userAdaptor.mapOneObjectToAnother(userRequestDto, User.class);
        try {
            log.info("user data saving in DB");
            user = userRepository.save(user);
            log.info("user data saved successfully in DB with id: {}", user.getId());
        } catch (Exception ex) {
            log.error("user data could not be save due to {}", ex.getMessage());
            throw new UserServiceException(ErrorDetails.EMPLOYEE_DATA_UNABLE_TO_SAVE, ex);
        }
        log.info("Now converting entity to dto and return a dto");
        return (UserResponseDto) userAdaptor.mapOneObjectToAnother(user, UserResponseDto.class);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deleting address detail from DB with ID: {}", id);
        userRepository.deleteById(id);
        log.info("Deleted address detail from DB successfully");
    }
}
