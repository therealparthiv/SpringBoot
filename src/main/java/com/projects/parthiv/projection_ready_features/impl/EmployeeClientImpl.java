package com.projects.parthiv.projection_ready_features.impl;

import com.projects.parthiv.projection_ready_features.advice.ApiResponse;
import com.projects.parthiv.projection_ready_features.clients.EmployeeClient;
import com.projects.parthiv.projection_ready_features.dto.EmployeeDTO;
import com.projects.parthiv.projection_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        try{
            ApiResponse<List<EmployeeDTO>> employeeDTOList= restClient
                    .get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });

            return employeeDTOList.getData();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try{
            ApiResponse<EmployeeDTO> employeeResponse= restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                        });

            return employeeResponse.getData();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try{
            ResponseEntity<ApiResponse<EmployeeDTO>> employeeDTOApiResponse= restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, ((request, response) -> {
                        System.out.println(new String(response.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("Could not create the Employee");
                    }))
                    .toEntity(new ParameterizedTypeReference<>(){
                    });

            return employeeDTOApiResponse.getBody().getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
