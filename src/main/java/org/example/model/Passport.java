package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Passport {
    @Column
    @NonNull
    private String passportCode;

    @Column
    @NonNull
    private String passportNumber;

    @Column
    @NonNull
    //храним значение в бд в цифрах
    //@Enumerated(value = EnumType.ORDINAL)
    @Enumerated(value = EnumType.STRING)
    private PassportType passportType;
}
