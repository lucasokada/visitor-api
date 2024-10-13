package com.example.visitor_ms.ports.in.rest.company;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;
import com.example.visitor_ms.domain.exception.InvalidCompanyException;
import com.example.visitor_ms.domain.service.CompanyService;
import com.example.visitor_ms.port.in.rest.company.CompanyController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CompanyService companyService;

    @Autowired
    private ObjectMapper mapper;

    private final String BASE_PATH = "/v1/company";

    private CreateCompanyCommand validRequest() {
        return new CreateCompanyCommand("Condomínio Swift", "37992724000179");
    }

    private Company validCompany() {
        return new Company("Condomínio Swift", "37992724000179");
    }

    @Test
    void create_whenValidRequest_expectCreated() throws Exception {
        CreateCompanyCommand request = validRequest();
        Company expectedResponse = validCompany();

        when(companyService.create(any(CreateCompanyCommand.class))).thenReturn(expectedResponse);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));

        verify(companyService).create(request);
    }

    @Test
    void create_whenInvalidCompanyException_expectBadRequest() throws Exception {
        CreateCompanyCommand request = validRequest();

        when(companyService.create(any(CreateCompanyCommand.class))).thenThrow(InvalidCompanyException.class);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(companyService).create(request);
    }
}
