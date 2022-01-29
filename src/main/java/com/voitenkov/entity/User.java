package com.voitenkov.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import com.voitenkov.converter.BirthDayConverter;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name ="users", schema = "public")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class User {
    @Id
    private String username;
    private String firstname;
    private String lastname;

    @Convert(converter = BirthDayConverter.class)
    @Column(name = "birth_date")
    private BirthDay birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonStringType")
    private String info;
}
