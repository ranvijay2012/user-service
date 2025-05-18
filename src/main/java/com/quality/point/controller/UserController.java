package com.quality.point.controller;

import com.quality.point.constant.SuccessDetails;
import com.quality.point.exception.UserServiceException;
import com.quality.point.service.UserService;
import com.quality.point.service.dto.ResponseDto;
import com.quality.point.service.dto.UserRequestDto;
import com.quality.point.service.dto.UserResponseDto;
import com.quality.point.utility.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/user-detail")
public class UserController {

    @Autowired
    private ResponseUtil responseUtil;

    @Autowired
    private UserService userService;

    @GetMapping()
    @Operation(summary = "Welcome method")
    public String getWelcome() {
        return "This is a welcome message for user service";
    }

    @GetMapping("users")
    @Operation(summary = "Get all users list from DB")
    public ResponseEntity<ResponseDto<?>> getUserList() {
        log.info("Get all user detail list from DB");
        List<UserResponseDto> userList = userService.getUserList();
        return responseUtil.getSuccessResponseWithDataDto(userList, SuccessDetails.ADDRESS_LIST_GET_SUCCESSFULLY);
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Get only users from DB with id")
    public ResponseEntity<ResponseDto<?>> getUser(@PathVariable("userId") Long userId) {
        log.info("Get only user dto from DB with id: {}", userId);
        UserResponseDto addressDto = userService.getUser(userId);
        return responseUtil.getSuccessResponseWithDataDto(addressDto, SuccessDetails.ADDRESS_GET_SUCCESSFULLY);
    }

    @PostMapping("/user")
    @Operation(summary = "Save address dto in DB")
    public ResponseEntity<ResponseDto<?>> saveAddress(@RequestBody UserRequestDto userRequestDto) throws UserServiceException {
        log.info("Save address dto in DB address dto is: {}", userRequestDto);
        UserResponseDto userResponseDto = userService.saveUser(userRequestDto);
        return responseUtil.getSuccessResponseWithDataDto(userResponseDto, SuccessDetails.ADDRESS_SAVED_SUCCESSFULLY);
    }

    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Delete address from DB filtered with userId")
    public ResponseEntity<ResponseDto<?>> deleteAddress(@PathVariable("userId") Long userId) {
        log.info("Delete address in DB id is: {}", userId);
        userService.deleteUser(userId);
        return responseUtil.getSuccessResponseDto(SuccessDetails.ADDRESS_DELETED_SUCCESSFULLY);
    }
}
