package com.example.visitor_ms.port.in.rest.visitor;

import com.example.visitor_ms.domain.Visitor;
import com.example.visitor_ms.domain.command.CreateVisitorCommand;
import com.example.visitor_ms.domain.service.VisitorService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/v1/visitor")
@RequiredArgsConstructor
public class VisitorController {
    private final VisitorService visitorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Visitor create(@Valid @RequestBody CreateVisitorCommand request) {
        return visitorService.create(request);
    }

    @GetMapping("/documentNumber/{documentNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Visitor> getById(@PathVariable("documentNumber") @Valid @Pattern(regexp = "^[0-9]{11}$") String documentNumber) {
        Optional<Visitor> visitor = visitorService.getByDocumentNumber(documentNumber);
        if(visitor.isPresent()) {
            return ResponseEntity.ok(visitor.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
