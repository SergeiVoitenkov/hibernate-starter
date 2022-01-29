package com.voitenkov.converter;

import com.voitenkov.entity.BirthDay;

import javax.persistence.AttributeConverter;
import javax.print.attribute.Attribute;
import java.sql.Date;
import java.util.Optional;

public class BirthDayConverter implements AttributeConverter<BirthDay, Date> {

    @Override
    public Date convertToDatabaseColumn(BirthDay attribute) {
        return Optional.ofNullable(attribute)
                .map(BirthDay::birthDate)
                .map(Date::valueOf)
                .orElse(null);
    }

    @Override
    public BirthDay convertToEntityAttribute(Date dbData) {
        return Optional.ofNullable(dbData)
                .map(Date::toLocalDate)
                .map(BirthDay::new)
                .orElse(null);
    }
}
