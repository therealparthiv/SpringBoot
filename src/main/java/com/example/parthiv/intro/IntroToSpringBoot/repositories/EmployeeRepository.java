package com.example.parthiv.intro.IntroToSpringBoot.repositories;

import com.example.parthiv.intro.IntroToSpringBoot.entitites.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
