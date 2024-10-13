package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidAddressException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Address {
    private String street;
    private Integer number;
    private String neighborhood;
    private String zipCode;
    private String city;
    private BrazilState state;

    public Address(String street, Integer number, String neighborhood, String zipCode, String city, BrazilState state) {
        this.street = street;
        this.number = validateNumber(number);
        this.neighborhood = neighborhood;
        this.zipCode = validateZipCode(zipCode);
        this.city = validateCity(city);
        this.state = state;
    }

    private Integer validateNumber(Integer number) {
        if (number <= 0) {
            throw new InvalidAddressException("Invalid number: it must be a positive integer.");
        }
        return number;
    }

    private String validateZipCode(String zipCode) {
        if (!zipCode.matches("\\d{8}")) {
            throw new InvalidAddressException("Invalid zip code: it must follow the pattern '00000000'.");
        }
        return zipCode;
    }

    private String validateCity(String city) {
        if (city.matches(".*\\d.*")) {
            throw new InvalidAddressException("Invalid city: it must not contain any numbers.");
        }
        return city;
    }
}
