package com.example.visitor_ms.domain.dto;

import java.io.Serializable;

public record CompanyVisitorAssociationDto(
        CompanyDto companyDto,
        VisitorDto visitorDto
) implements Serializable {
}
