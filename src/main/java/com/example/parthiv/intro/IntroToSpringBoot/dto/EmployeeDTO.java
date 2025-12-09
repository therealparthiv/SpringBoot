package com.example.parthiv.intro.IntroToSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EmployeeDTO {

    private Long id;
    @NotBlank(message = "Required field in Employee cannot be empty: name")
    @Size(min = 3, max=10, message = "Name between 3 and 10 characters")
    private String name;
    @Email
    @NotBlank
    private String email;
    @NotNull
    @Min(value=18, message = "Age of Employee cannot be less than 18")
    @Max(value=80, message = "Age of Employee cannot be more than 80")
    private Integer age;

    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role: USER/ADMIN")
    @NotBlank
    private String role; //ADMIN, USER

    @NotNull
    @Positive
    @Digits(integer = 6, fraction = 2)
    private Integer salary;

    @PastOrPresent
    private LocalDate dateOfJoining;

//    @AssertTrue(message = "Employee should be active")
    @JsonProperty("isActive")
    private Boolean isActive;
}
