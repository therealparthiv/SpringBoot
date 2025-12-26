package com.projects.parthiv.projection_ready_features;

import com.projects.parthiv.projection_ready_features.clients.EmployeeClient;
import com.projects.parthiv.projection_ready_features.dto.EmployeeDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProjectionReadyFeaturesApplicationTests {

    @Autowired
    private EmployeeClient employeeClient;

	@Test
	void contextLoads() {
	}

    @Test
    @Order(3)
    void getAllEmployees(){
        List<EmployeeDTO> employeeDTOList= employeeClient.getAllEmployees();
        System.out.println(employeeDTOList);
    }

    @Test
    @Order(2)
    void getEmployeeByIdTest(){
        EmployeeDTO employeeDTO= employeeClient.getEmployeeById(9L);
        System.out.println(employeeDTO);;
    }

    @Test
    @Order(1)
    void createNewEmployeeTest(){
        EmployeeDTO employeeDTO= new EmployeeDTO(null, "Parthiv", "parthiv@gmail.com",
                21, "USER", 5000, LocalDate.of(2020, 12, 1), true);
        EmployeeDTO savedEmployeeDTO= employeeClient.createNewEmployee(employeeDTO);
        System.out.println(savedEmployeeDTO);
    }

}
