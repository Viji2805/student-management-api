package com.workshop.studentmanagement1;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StudentDTO {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Min(18)
    @Max(60)
    private Integer age;
    @NotBlank
    private String course;
}
