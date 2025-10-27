package com.rapheal.student_management_system.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDTO {
@NotBlank(message = "First Name is required")
    private String firstName;

@NotBlank(message = "Last Name is required")
    private String lastName;

@Email(message = "Please provide a valid email address")
    private String email;

@Min(value = 10)
@Max(value = 70)
    private int age;
}
