package com.voitenkov;

import com.voitenkov.entity.PersonalInfo;
import com.voitenkov.entity.User;
import com.voitenkov.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@Slf4j
public class HibernateRunner {
    /*
     @Slf4j аннотация сконфигурирует эту строку.
     */
//    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        User user = User.builder()
                .username("petrov@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Petr")
                        .lastname("Petrov").build())
                .build();

        log.info("User entity is in transient state, object: {}", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();
                log.trace("Transaction is created, {}", transaction);
                session1.saveOrUpdate(user);
                log.trace("User is in persistent state {}, session {}", user, session1);
                session1.getTransaction().commit();
            }
            log.warn("User is in detached state {}, session is closed {}", user, session1);

        } catch (Exception exception) {
            log.error("Exception occurred", exception);
        }

    }
}