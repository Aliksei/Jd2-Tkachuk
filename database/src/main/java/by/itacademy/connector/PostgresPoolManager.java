package by.itacademy.connector;

import by.itacademy.exceptions.DaoLayerException;
import by.itacademy.exceptions.DatabaseConnectionException;
import by.itacademy.utils.SqlQueryManager;
import by.itacademy.utils.SqlQueryPrinter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.*;

import static by.itacademy.utils.DatabaseProperties.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PostgresPoolManager {

    private final static Logger LOGGER = Logger.getLogger(PostgresPoolManager.class);

    private static DataSource dataSource;

    static {
        initDataSource();
        prepareDataBase();
    }

    private static void initDataSource(){
        PoolProperties poolProperties = new PoolProperties();
        poolProperties.setUrl(DATABASE_BASE_URL);
        poolProperties.setUsername(BASE_USER);
        poolProperties.setPassword(BASE_USER_PASSWORD);
        poolProperties.setDriverClassName("org.postgresql.Driver");
        dataSource = new DataSource(poolProperties);
    }

    private static void prepareDataBase(){
        final String initScript = SqlQueryManager.getSqlQuery(DATABASE_INIT_SCRIPT);
        try {
            LOGGER.info("-------------------  Running [Database Init Script]  -------------------\n");
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement(initScript);
            SqlQueryPrinter.printQuery(preparedStatement);
            preparedStatement.executeUpdate();
            LOGGER.info("Database connection passed successfully");
        } catch (SQLException e) {
            throw new DaoLayerException("Can't init database demo table");
        }
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DatabaseConnectionException("Error during getting database connection" + e.getMessage(),e.getCause());
        }
    }

}
