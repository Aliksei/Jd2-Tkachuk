package by.itacademy.connector;

import by.itacademy.utils.SqlQueryManager;
import by.itacademy.utils.SqlQueryPrinter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PostgresPoolManager {

    private final static Logger LOGGER = Logger.getLogger(PostgresPoolManager.class);

    private static SessionFactory sessionFactory;

    static {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        prepareDataBase();
    }

    private static void prepareDataBase() {
        final String initScript = SqlQueryManager.getSqlQuery("dbInitScript.sql");
        LOGGER.info("-------------------  Running [Database Init Script]  -------------------\n");

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery(initScript);
        nativeQuery.executeUpdate();
        SqlQueryPrinter.printQuery(initScript);

        session.createNativeQuery(initScript).executeUpdate();
        session.getTransaction().commit();
        LOGGER.info("Database connection passed successfully");
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getNewOpenedSession() {
        return sessionFactory.openSession();
    }

}
