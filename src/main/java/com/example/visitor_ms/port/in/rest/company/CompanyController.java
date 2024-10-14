package com.example.visitor_ms.port.in.rest.company;

import com.example.visitor_ms.domain.Company;
import com.example.visitor_ms.domain.command.CreateCompanyCommand;
import com.example.visitor_ms.domain.service.CompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
import java.util.Set;

@RestController
@RequestMapping(path = "/v1/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody CreateCompanyCommand createCompanyCommand) {
        return companyService.create(createCompanyCommand);
    }

    @PostMapping("/documentNumber/{documentNumber}/visitors")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@PathVariable("documentNumber") @Valid @NotBlank @Pattern(regexp = "\\d{14}") String documentNumber,
                            @RequestBody @NotEmpty Set<@Pattern(regexp = "\\d{11}") String> visitorsDocumentNumbers) {
        companyService.associate(documentNumber, visitorsDocumentNumbers);
    }

    @GetMapping("/documentNumber/{documentNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Company> getByDocumentNumber(@PathVariable("documentNumber") @Valid @NotBlank @Pattern(regexp = "\\d{14}") String documentNubmer) {
        Optional<Company> company = companyService.getByDocumentNumber(documentNubmer);
        if(company.isPresent()) {
            return ResponseEntity.ok(company.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
