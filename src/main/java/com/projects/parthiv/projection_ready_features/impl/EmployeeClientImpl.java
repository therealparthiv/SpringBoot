package com.projects.parthiv.projection_ready_features.impl;

import com.projects.parthiv.projection_ready_features.advice.ApiResponse;
import com.projects.parthiv.projection_ready_features.clients.EmployeeClient;
import com.projects.parthiv.projection_ready_features.dto.EmployeeDTO;
import com.projects.parthiv.projection_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        log.trace("Trying to retrieve all employees in getAllEmployees");
        try {
            log.info("Attempting to call the restClient method in getAllEmployees");

            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        log.error("Client error while fetching employees: {}",
                                new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not retrieve employees");
                    })
                    .body(new ParameterizedTypeReference<>() {});

            log.debug("Successfully retrieved employees in getAllEmployees");
            log.trace("Retrieved Employee List in getAllEmployees: {}",
                    employeeDTOList.getData());

            return employeeDTOList.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getAllEmployees", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        log.trace("Trying to retrieve employee by id {}", employeeId);
        try {
            log.info("Attempting to call the restClient method in getEmployeeById for id {}",
                    employeeId);

            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});

            log.debug("Successfully retrieved employee with id {}", employeeId);
            log.trace("Retrieved Employee Data in getEmployeeById: {}",
                    employeeResponse.getData());

            return employeeResponse.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getEmployeeById for id {}", employeeId, e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        log.trace("Trying to create new employee with data {}", employeeDTO);
        try {
            log.info("Attempting to call the restClient method in createNewEmployee");

            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse =
                    restClient.post()
                            .uri("employees")
                            .body(employeeDTO)
                            .retrieve()
                            .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                                log.error("Client error while creating employee: {}",
                                        new String(response.getBody().readAllBytes()));
                                throw new ResourceNotFoundException("Could not create the Employee");
                            })
                            .toEntity(new ParameterizedTypeReference<>() {});

            log.debug("Successfully created employee in createNewEmployee");
            log.trace("Created Employee Data in createNewEmployee: {}",
                    employeeDTOApiResponse.getBody().getData());

            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            log.error("Exception occurred in createNewEmployee", e);
            throw new RuntimeException(e);
        }
    }
}
