package com.voitenkov.util;

import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@UtilityClass
public class HibernateUtil {

    public static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
//        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy()); // либо ставим на полем аннотацию @Column
//        configuration.addAnnotatedClass(User.class); // так или в ресурсах прописываем в mapping
//        configuration.addAttributeConverter(new BirthDayConverter(), true);
        configuration.registerTypeOverride(new JsonStringType()); // подключили новый тип
        configuration.configure();
        return configuration.buildSessionFactory();
    }
}
