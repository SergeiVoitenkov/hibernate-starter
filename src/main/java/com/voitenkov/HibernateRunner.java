package com.voitenkov;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import com.voitenkov.entity.BirthDay;
import com.voitenkov.entity.Role;
import com.voitenkov.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.SQLException;
import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) throws SQLException {
        Configuration configuration = new Configuration();
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy()); // либо ставим на полем аннотацию @Column
//        configuration.addAnnotatedClass(User.class); // так или в ресурсах прописываем в mapping
//        configuration.addAttributeConverter(new BirthDayConverter(), true);
        configuration.registerTypeOverride(new JsonStringType()); // подключили новый тип
        configuration.configure();

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("pavel2@mail.ru")
                    .firstname("Ivan")
                    .lastname("Ivanov")
                    .info("""
                            {
                            "id": 25,
                            "name": "Ivan"
                            }
                            """)
                    .birthDate(new BirthDay(LocalDate.of(2000, 1, 19)))
                    .role(Role.USER)
                    .build();

            session.save(user);

            session.getTransaction().commit();
        }
    }
}
