package com.example.visitor_ms.domain;

import com.example.visitor_ms.domain.exception.InvalidContactException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Contact {
    private String residentialPhone;
    private String commercialPhone;
    private String cellPhone;
    private String email;

    public Contact(String residentialPhone, String commercialPhone, String cellPhone, String email) {
        this.residentialPhone = validatePhone(residentialPhone);
        this.commercialPhone = validatePhone(commercialPhone);
        this.cellPhone = validatePhone(cellPhone);
        this.email = validateEmail(email);
    }

    private String validatePhone(String phoneNumber) {
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new InvalidContactException("Invalid phone: it must contain only numbers and have at a length of 10 or 11.");
        }
        return phoneNumber;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber == null || phoneNumber.matches("\\d{10,11}");
    }

    private String validateEmail(String email) {
        if(!isValidEmail(email)) {
            throw new InvalidContactException("Invalid email: not matches valid email pattern");
        }

        return email;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@[\\w-]+(\\.[\\w-]+)+$");
    }
}
