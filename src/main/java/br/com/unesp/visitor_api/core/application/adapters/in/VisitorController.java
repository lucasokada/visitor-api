package br.com.unesp.visitor_api.core.application.adapters.in;

import br.com.unesp.visitor_api.core.application.adapters.DefaultApiResponse;
import br.com.unesp.visitor_api.core.application.adapters.VisitorResponse;
import br.com.unesp.visitor_api.core.application.domain.entities.Visitor;
import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.in.AddVisitorUseCase;
import br.com.unesp.visitor_api.core.application.ports.in.GetVisitorUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/visitor")
@RequiredArgsConstructor
public class VisitorController {
    private final AddVisitorUseCase addVisitorUseCase;
    private final GetVisitorUseCase getVisitorUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DefaultApiResponse<VisitorResponse> add(@Valid @RequestBody VisitorDTO visitor) {
        Visitor newVisitor = addVisitorUseCase.addVisitor(visitor);
        return new DefaultApiResponse<>("create success", new VisitorResponse(newVisitor.getId(), newVisitor.getDocumentNumber()));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Visitor getById(@PathVariable("id") Long id) {
        return getVisitorUseCase.getById(id);
    }

    @GetMapping("/documentNumber/{documentNumber}")
    @ResponseStatus(HttpStatus.OK)
    public Visitor getById(@PathVariable("documentNumber") @Valid @Pattern(regexp = "^[0-9]{9,11}$") String documentNumber) {
        return getVisitorUseCase.getByDocumentNumber(documentNumber);
    }
}
