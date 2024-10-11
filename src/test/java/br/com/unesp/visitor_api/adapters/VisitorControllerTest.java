package br.com.unesp.visitor_api.adapters;

import br.com.unesp.visitor_api.core.application.adapters.DefaultApiResponse;
import br.com.unesp.visitor_api.core.application.adapters.VisitorResponse;
import br.com.unesp.visitor_api.core.application.adapters.in.VisitorController;
import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.in.AddVisitorUseCase;
import br.com.unesp.visitor_api.core.application.usecases.visitor.exceptions.DuplicateDocumentException;
import br.com.unesp.visitor_api.mocks.dto.VisitorDTOMock;
import br.com.unesp.visitor_api.mocks.entities.VisitorMock;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
    private AddVisitorUseCase useCase;

    @Autowired
    private ObjectMapper mapper;

    private final String BASE_PATH = "/v1/visitor";

    @Test
    public void addVisitor_whenValidRequest_expectCreated() throws Exception {
        VisitorDTO request = VisitorDTOMock.mock();
        Visitor visitor = VisitorMock.mockWithId();
        DefaultApiResponse<VisitorResponse> expectedResponse = new DefaultApiResponse<>("create success",
                new VisitorResponse(1L, "37055587069"));

        when(useCase.addVisitor(any(VisitorDTO.class))).thenReturn(visitor);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));

        verify(useCase).addVisitor(request);
    }

    @Test
    public void addVisitor_whenTryToSaveVisitorWithExistentDocumentNumber_expectConflict() throws Exception {
        VisitorDTO request = VisitorDTOMock.mock();
        DefaultApiResponse<Void> expectedResponse = new DefaultApiResponse<>("Document number 37055587069 " +
                "already exists for person with id 1", null);

        when(useCase.addVisitor(any(VisitorDTO.class))).thenThrow(new DuplicateDocumentException("Document number " +
                "37055587069 already exists for person with id 1"));

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));

        verify(useCase).addVisitor(request);
    }

    @Test
    public void addVisitor_whenInvalidRequest_expectBadRequest() throws Exception {
        VisitorDTO request = VisitorDTOMock.mock();
        request.setName(null);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());

        verify(useCase, never()).addVisitor(any(VisitorDTO.class));
    }
}
