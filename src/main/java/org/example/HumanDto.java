package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class HumanDto {
    private Integer id;

    private String name;

    private Passport passport;

    private List<Auto> cars;
}
