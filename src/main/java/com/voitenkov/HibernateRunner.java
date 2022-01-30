package com.voitenkov;

import com.voitenkov.entity.User;
import com.voitenkov.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateRunner {

    public static void main(String[] args) {
        User user = User.builder()
                .username("ivsanov@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .build();

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();
                session1.saveOrUpdate(user);
                session1.getTransaction().commit();
            }

            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                user.setFirstname("Sveta");
//                session2.delete(user);
//                refresh/merge
//                User freshUser = session2.get(User.class, user.getUsername());
//                freshUser.setLastname(user.getLastname());
//                freshUser.setFirstname(user.getFirstname());

                Object mergeUser = session2.merge(user);
//                session2.refresh(user);

                session2.getTransaction().commit();
            }

        }
    }
}
