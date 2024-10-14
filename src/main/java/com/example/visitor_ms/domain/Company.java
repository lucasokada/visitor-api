package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidCompanyVisitorTypeException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Company extends Legal {
    private Set<Visitor> serviceProviders = new HashSet<>();

    public Company(String name, String documentNumber, Set<Visitor> visitors) {
        super(name, documentNumber);
        this.serviceProviders = validateServiceProviderVisitors(visitors);
    }

    public Company(String name, String documentNumber) {
        super(name, documentNumber);
    }

    private Set<Visitor> validateServiceProviderVisitors(Set<Visitor> serviceProviders) {
        if(!serviceProviders.isEmpty()) {
            boolean hasOnlyServiceProviders = serviceProviders
                    .stream()
                    .allMatch(e -> e.getType().equals(VisitorType.SERVICE_PROVIDER));

            if (!hasOnlyServiceProviders) {
                throw new InvalidCompanyVisitorTypeException("Invalid service providers: All visitors associated with the company " +
                        "must be of type SERVICE_PROVIDER");
            }
        }

        return serviceProviders;
    }

    public void addAllServiceProviders(Set<Visitor> serviceProviders) {
        this.serviceProviders.addAll(validateServiceProviderVisitors(serviceProviders));
    }
}
