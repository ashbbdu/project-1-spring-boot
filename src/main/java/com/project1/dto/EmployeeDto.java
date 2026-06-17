package com.project1.dto;

import com.project1.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
public class EmployeeDto {
    private Long id;

    @NotNull(message = "First name is required")
    @NotEmpty(message = "First name can not be empty")
    @Size(min = 4 , max = 10 , message = "First name should be between 4 and 10 characters")
    private String name;

    @Email(message = "Email should be a valid email")
    private String email;

    @Max(value = 60 , message = "Age of employee should not be greater than 60")
    @Min(value = 18 , message = "Age of employee should not be lesser than 18")
    private Integer age;

//    @Pattern(regexp = "^(ADMIN|USER)$" , message = "User role must be ADMIN or USER")
    @EmployeeRoleValidation
    private String role;


    @PastOrPresent(message = "Joining Date can not be in future")
    private LocalDateTime dateOfJoining;

    @AssertTrue
    private Boolean isActive;
}
