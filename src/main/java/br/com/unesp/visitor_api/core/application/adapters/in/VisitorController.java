package br.com.unesp.visitor_api.core.application.adapters.in;

import br.com.unesp.visitor_api.core.application.ports.dto.VisitorDTO;
import br.com.unesp.visitor_api.core.application.ports.in.AddVisitorUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void add(@Valid @RequestBody VisitorDTO visitor) {
        addVisitorUseCase.addVisitor(visitor);
    }
}
