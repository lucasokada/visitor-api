package com.example.visitor_ms.ports.in.rest.company;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;
import com.example.visitor_ms.domain.exception.InvalidCompanyVisitorTypeException;
import com.example.visitor_ms.domain.exception.NotFoundException;
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

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    void associate_whenSuccess_expectNoContent() throws Exception {
        String cnpj = "37992724000179";
        Set<String> visitorsDocumentNumbers = Set.of("11309929939", "05788203821");

        doNothing().when(companyService).associate(anyString(), anySet());

        mockMvc.perform(post(BASE_PATH + "/documentNumber/" + cnpj + "/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(visitorsDocumentNumbers)))
                .andExpect(status().isNoContent());

        verify(companyService).associate(cnpj, visitorsDocumentNumbers);
    }

    @Test
    void associate_whenNotFoundException_expectNotFound() throws Exception {
        String cnpj = "37992724000179";
        Set<String> visitorsDocumentNumbers = Set.of("11309929939", "05788203821");

        doThrow(NotFoundException.class).when(companyService).associate(anyString(), anySet());

        mockMvc.perform(post(BASE_PATH + "/documentNumber/" + cnpj + "/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(visitorsDocumentNumbers)))
                .andExpect(status().isNotFound());

        verify(companyService).associate(cnpj, visitorsDocumentNumbers);
    }

    @Test
    void associate_whenInvalidVisitorTypeForCompanyException_expectUnprocessableEntity() throws Exception {
        String cnpj = "37992724000179";
        Set<String> visitorsDocumentNumbers = Set.of("11309929939", "05788203821");

        doThrow(InvalidCompanyVisitorTypeException.class).when(companyService).associate(anyString(), anySet());

        mockMvc.perform(post(BASE_PATH + "/documentNumber/" + cnpj + "/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(visitorsDocumentNumbers)))
                .andExpect(status().isUnprocessableEntity());

        verify(companyService).associate(cnpj, visitorsDocumentNumbers);
    }

    @Test
    void associate_whenInvalidCnpj_expectBadRequest() throws Exception {
        String cnpj = "379927240001";
        Set<String> visitorsDocumentNumbers = Set.of("11309929939", "05788203821");

        mockMvc.perform(post(BASE_PATH + "/documentNumber/" + cnpj + "/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(visitorsDocumentNumbers)))
                .andExpect(status().isBadRequest());

        verify(companyService, never()).associate(anyString(), anySet());
    }

    @Test
    void associate_whenInvalidCpfs_expectBadRequest() throws Exception {
        String cnpj = "37992724000179";
        Set<String> visitorsDocumentNumbers = Set.of("11309929939", "057882038");

        mockMvc.perform(post(BASE_PATH + "/documentNumber/" + cnpj + "/visitors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(visitorsDocumentNumbers)))
                .andExpect(status().isBadRequest());

        verify(companyService, never()).associate(anyString(), anySet());
    }

    @Test
    void getByDocumentNumber_whenSuccess_expectOk() throws Exception {
        String cnpj = "37992724000179";
        Company expectedResponse = validCompany();

        when(companyService.getByDocumentNumber(anyString())).thenReturn(Optional.of(expectedResponse));

        mockMvc.perform(get(BASE_PATH + "/documentNumber/" + cnpj)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }

    @Test
    void getByDocumentNumber_whenOptionalEmpty_expectNotFound() throws Exception {
        String cnpj = "37992724000179";

        when(companyService.getByDocumentNumber(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_PATH + "/documentNumber/" + cnpj)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
