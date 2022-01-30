package com.voitenkov.entity;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

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

    @Embedded // аннотация не обязательная
    @AttributeOverride(name="birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    private Role role;

//    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonStringType")
    private String info;
}
