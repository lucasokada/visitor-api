package br.com.unesp.visitor_api.integration.adapters;

import br.com.unesp.visitor_api.VisitorApiApplication;
import br.com.unesp.visitor_api.configuration.H2JpaConfig;
import br.com.unesp.visitor_api.core.application.adapters.DefaultApiResponse;
import br.com.unesp.visitor_api.core.application.adapters.VisitorResponse;
import br.com.unesp.visitor_api.core.application.adapters.in.configuration.JacksonConfig;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.in.AddVisitorUseCase;
import br.com.unesp.visitor_api.mocks.dto.VisitorDTOMock;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { VisitorApiApplication.class, H2JpaConfig.class, JacksonConfig.class})
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class VisitorControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AddVisitorUseCase useCase;

    @Autowired
    private ObjectMapper mapper;

    private final String BASE_PATH = "/v1/visitor";

    @Test
    @DirtiesContext
    public void addVisitor_whenValidRequest_expectCreated() throws Exception {
        VisitorDTO request = VisitorDTOMock.mock();
        DefaultApiResponse<VisitorResponse> expectedResponse = new DefaultApiResponse<>("create success", new VisitorResponse(1L, "37055587069"));
        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }

    @Test
    @DirtiesContext
    public void addVisitor_whenTryToSaveVisitorWithExistentDocumentNumber_expectConflict() throws Exception {
        VisitorDTO request = VisitorDTOMock.mock();
        DefaultApiResponse<Void> expectedResponse = new DefaultApiResponse<>("Document number 37055587069 already exists for person with id 1", null);

        useCase.addVisitor(request);

        mockMvc.perform(post(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isConflict())
                .andExpect(content().json(mapper.writeValueAsString(expectedResponse)));
    }
}
