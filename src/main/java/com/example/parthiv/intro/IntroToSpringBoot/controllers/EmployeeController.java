package com.example.parthiv.intro.IntroToSpringBoot.controllers;

import com.example.parthiv.intro.IntroToSpringBoot.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path= "/employees")
public class EmployeeController {

//    @GetMapping(path= "/getSecretMessage")
//    public String getMySuperSecretMessage(){
//        return "Secret Message: sdirubfgiuerbf";
//    }

    @GetMapping(path= "/{employeeId}")
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId){
        return new EmployeeDTO(employeeId, "Parthiv", "parthiv@gmail.com", 21, LocalDate.of(2024,01,02), true);
    }

    @GetMapping
    public String getAllEmployees(@RequestParam(required = false) Integer age,
                                  @RequestParam(required = false) String sortBy){
        return "Hi, age "+ age+ " "+ sortBy;
    }

}
