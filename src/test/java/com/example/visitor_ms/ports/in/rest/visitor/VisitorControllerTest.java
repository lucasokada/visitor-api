package com.example.visitor_ms.ports.in.rest.visitor;

import com.example.visitor_ms.domain.Access;
import com.example.visitor_ms.domain.Address;
import com.example.visitor_ms.domain.BrazilState;
import com.example.visitor_ms.domain.Contact;
import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.VisitorType;
import com.example.visitor_ms.domain.command.CreateAccessCommand;
import com.example.visitor_ms.domain.command.CreateAddressCommand;
import com.example.visitor_ms.domain.command.CreateContactCommand;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;
import com.example.visitor_ms.domain.exception.InvalidAccessException;
import com.example.visitor_ms.domain.exception.InvalidAddressException;
import com.example.visitor_ms.domain.exception.InvalidContactException;
import com.example.visitor_ms.domain.exception.InvalidPersonException;
import com.example.visitor_ms.domain.exception.NotFoundException;
import com.example.visitor_ms.domain.service.VisitorService;
import com.example.visitor_ms.port.in.rest.visitor.VisitorController;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VisitorController.class)
@AutoConfigureMockMvc
public class VisitorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitorService visitorService;

    @Autowired
    private ObjectMapper mapper;

    private final String BASE_PATH = "/v1/visitor";

    private CreateVisitorCommand validVisitorCommand() {
        return new CreateVisitorCommand("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContactCommand(),
                validAccessCommand(), validAddressesCommand());
    }

    private CreateContactCommand validContactCommand() {
        return new CreateContactCommand("6930138401", "9626759827", "1935794707", "name@email.com");
    }

    private CreateAccessCommand validAccessCommand() {
        return new CreateAccessCommand("userName", "PassworD123!");
    }

    private List<CreateAddressCommand> validAddressesCommand() {
        return new ArrayList<>() {{
            add(new CreateAddressCommand("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)", "13506517", "Rio Claro", BrazilState.SP));
            add(new CreateAddressCommand("Avenida 7", 773, "Jardim Claret", "13503255", "Rio Claro", BrazilState.SP));
        }};
    }

    private Visitor validVisitor() {
        return new Visitor("Augusto João da Rosa", "11309929939",
                LocalDate.of(1959, 3, 20), VisitorType.RELATED, validContact(), validAccess(),
                validAddresses());
    }

    private Contact validContact() {
        return new Contact("6930138401", "9626759827", "1935794707", "name@email.com");
    }

    private Access validAccess() {
        return new Access("userName", "PassworD123!");
    }

    private Set<Address> validAddresses() {
        return new HashSet<>() {{
            add(new Address("Rua 18 JB", 533, "Jardim Bandeirante (COHAB)", "13506517", "Rio Claro", BrazilState.SP));
            add(new Address("Avenida 7", 773, "Jardim Claret", "13503255", "Rio Claro", BrazilState.SP));
        }};
    }

    @Test
    void create_whenValidRequest_expectCreated() throws Exception {
        CreateVisitorCommand request = validVisitorCommand();
        Visitor expectedResponse = validVisitor();

        when(visitorService.create(any(CreateVisitorCommand.class))).thenReturn(expectedResponse);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));

        verify(visitorService).create(request);
    }

    @Test
    void create_whenInvalidPersonException_expectBadRequest() throws Exception {
        CreateVisitorCommand request = validVisitorCommand();

        when(visitorService.create(any(CreateVisitorCommand.class))).thenThrow(InvalidPersonException.class);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(visitorService).create(request);
    }

    @Test
    void create_whenInvalidContactException_expectBadRequest() throws Exception {
        CreateVisitorCommand request = validVisitorCommand();

        when(visitorService.create(any(CreateVisitorCommand.class))).thenThrow(InvalidContactException.class);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(visitorService).create(request);
    }

    @Test
    void create_whenInvalidAccessException_expectBadRequest() throws Exception {
        CreateVisitorCommand request = validVisitorCommand();

        when(visitorService.create(any(CreateVisitorCommand.class))).thenThrow(InvalidAccessException.class);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(visitorService).create(request);
    }

    @Test
    void create_whenInvalidAddressException_expectBadRequest() throws Exception {
        CreateVisitorCommand request = validVisitorCommand();

        when(visitorService.create(any(CreateVisitorCommand.class))).thenThrow(InvalidAddressException.class);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(visitorService).create(request);
    }

    @Test
    void getByDocumentNumber_whenSuccess_expectOk() throws Exception {
        String documentNumber = "11309929939";
        Visitor visitor = validVisitor();

        when(visitorService.getByDocumentNumber(anyString())).thenReturn(Optional.of(visitor));

        mockMvc.perform(get(BASE_PATH + "/documentNumber/" + documentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(visitor)));

        verify(visitorService).getByDocumentNumber(documentNumber);
    }

    @Test
    void getByDocumentNumber_whenNotFoundException_expectNotFound() throws Exception {
        String documentNumber = "11309929939";

        when(visitorService.getByDocumentNumber(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_PATH + "/documentNumber/" + documentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(visitorService).getByDocumentNumber(documentNumber);
    }

    @Test
    void getByDocumentNumber_whenInvalidDocumentNumberLength_expectBadRequest() throws Exception {
        String documentNumber = "11309929";

        mockMvc.perform(get(BASE_PATH + "/documentNumber/" + documentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(visitorService, never()).getByDocumentNumber(anyString());
    }

    @Test
    void getByDocumentNumber_whenInvalidDocumentNumberWithLetters_expectBadRequest() throws Exception {
        String documentNumber = "a130992993a";

        mockMvc.perform(get(BASE_PATH + "/documentNumber/" + documentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(visitorService, never()).getByDocumentNumber(anyString());
    }

    @Test
    void deleteByDocumentNumber_whenSuccess_expectNoContent() throws Exception {
        String documentNumber = "11309929939";

        mockMvc.perform(delete(BASE_PATH + "/documentNumber/" + documentNumber)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(visitorService).deleteByDocumentNumber(documentNumber);
    }

    @Test
    void deleteByDocumentNumber_whenNotFoundException_expectNotFound() throws Exception {
        String documentNumber = "11309929939";

        doThrow(NotFoundException.class).when(visitorService).deleteByDocumentNumber(anyString());

        mockMvc.perform(delete(BASE_PATH + "/documentNumber/" + documentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(visitorService).deleteByDocumentNumber(documentNumber);
    }

    @Test
    void deleteByDocumentNumber_whenInvalidDocumentNumberLength_expectBadRequest() throws Exception {
        String documentNumber = "11309929";

        mockMvc.perform(delete(BASE_PATH + "/documentNumber/" + documentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(visitorService, never()).deleteByDocumentNumber(anyString());
    }

    @Test
    void deleteByDocumentNumber_whenInvalidDocumentNumberWithLetters_expectBadRequest() throws Exception {
        String documentNumber = "a130992993a";

        mockMvc.perform(delete(BASE_PATH + "/documentNumber/" + documentNumber)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());

        verify(visitorService, never()).deleteByDocumentNumber(anyString());
    }

}
