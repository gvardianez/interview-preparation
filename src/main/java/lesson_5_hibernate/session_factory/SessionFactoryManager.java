package lesson_5_hibernate.session_factory;

import lesson_5_hibernate.entities.Student;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryManager {

    private static final SessionFactory sessionFactory;

    static {
         sessionFactory = new Configuration()
                 .addAnnotatedClass(Student.class)
                 .buildSessionFactory();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
