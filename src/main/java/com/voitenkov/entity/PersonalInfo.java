package com.voitenkov.entity;

import com.voitenkov.converter.BirthDayConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class PersonalInfo {

    private String firstname;
    private String lastname;

    @Convert(converter = BirthDayConverter.class)
    private BirthDay birthDate;
}
