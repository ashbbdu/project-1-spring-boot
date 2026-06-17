package com.project1.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UpdateEmployeeDto {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private String role;
    private LocalDateTime dateOfJoining;
    private Boolean isActive;
}
