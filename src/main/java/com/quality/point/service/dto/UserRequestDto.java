package com.quality.point.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String add1;
    private String add2;
    private String city;
    private String state;
    private Integer pin;

}
