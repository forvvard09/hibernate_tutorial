package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Passport implements Serializable {

    private static final long serialVersionUID = 6454045001343241068L;
    @Column(nullable = false)
    @NonNull
    private String passportCode;

    @Column(nullable = false)
    @NonNull
    private String passportNumber;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    @NonNull
    private PassportType passportType;
}
