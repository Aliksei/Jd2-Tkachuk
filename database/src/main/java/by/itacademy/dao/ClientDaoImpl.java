package by.itacademy.dao;

import by.itacademy.utils.SqlQueryManager;
import by.itacademy.connector.PostgresPoolManager;
import by.itacademy.enteties.Client;
import by.itacademy.exceptions.DaoLayerException;
import by.itacademy.utils.SqlQueryPrinter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientDaoImpl implements ClientDao {

    private static final ClientDaoImpl INSTANCE = new ClientDaoImpl();

    private static final String GET_CLIENT_BY_ID = "getClient.sql";
    private static final String CREATE_CLIENT = "createClient.sql";
    private static final String DELETE_CLIENT = "deleteClient.sql";
    private static final String UPDATE_CLIENT = "updateClient.sql";
    private static final String GET_ALL_CLIENTS = "getAllClients.sql";

    @Override
    public synchronized Integer createClient(Client client) {
        Integer id = null;
        Connection connection = PostgresPoolManager.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(SqlQueryManager.getSqlQuery(CREATE_CLIENT), 1)) {
            LOGGER.info("Creating new client with params : " + client);
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getSecondName());
            preparedStatement.setInt(3, client.getAge());

            SqlQueryPrinter.printQuery(preparedStatement);
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                id = generatedKeys.getInt(1);
            }
            return id;
        }catch (SQLException e){
            throw new DaoLayerException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public synchronized Optional<Client> getClientById(Integer id) {
        Client client = null;

        Connection connection = PostgresPoolManager.getConnection();
        String getClientQuery = SqlQueryManager.getSqlQuery(GET_CLIENT_BY_ID);

        try (PreparedStatement preparedStatement = connection.prepareStatement(getClientQuery)) {
            LOGGER.info("Getting Client from database by ID = : " + id);
            preparedStatement.setInt(1, id);
            SqlQueryPrinter.printQuery(preparedStatement);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                client = Client.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .secondName(resultSet.getString("second_name"))
                        .age(resultSet.getInt("age"))
                        .build();
            }
            return Optional.ofNullable(client);
        }catch (SQLException e){
            throw new DaoLayerException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public synchronized boolean deleteClient(Integer id) {
        boolean result;
        Connection connection = PostgresPoolManager.getConnection();
        String deleteQuery = SqlQueryManager.getSqlQuery(DELETE_CLIENT);

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            LOGGER.info("Deleting Client from database by ID = : " + id);
            preparedStatement.setInt(1, id);
            SqlQueryPrinter.printQuery(preparedStatement);

            result = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new DaoLayerException(e.getMessage(), e.getCause());
        }
        return result;
    }

    @Override
    public synchronized boolean updateClient(Client client) {
        boolean result;
        Connection connection = PostgresPoolManager.getConnection();
        String updateQuery = SqlQueryManager.getSqlQuery(UPDATE_CLIENT);

        try(PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)){
            LOGGER.info(String.format("Updating client with ID =[%d] with new data : [%s], [%s], [%d]", client.getId(), client.getFirstName(), client.getSecondName(), client.getAge()));
            preparedStatement.setString(1, client.getFirstName());
            preparedStatement.setString(2, client.getSecondName());
            preparedStatement.setInt(3, client.getAge());
            preparedStatement.setInt(4, client.getId());
            SqlQueryPrinter.printQuery(preparedStatement);

            result = preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){
            throw new DaoLayerException(e.getMessage(), e.getCause());
        }
        return result;
    }

    @Override
    public synchronized List<Client> getAllClients(){
        List<Client> clients = new ArrayList<>();

        Connection connection = PostgresPoolManager.getConnection();
        String getClients = SqlQueryManager.getSqlQuery(GET_ALL_CLIENTS);

        try (PreparedStatement preparedStatement = connection.prepareStatement(getClients)) {
            LOGGER.info("Getting all clients from database");
            SqlQueryPrinter.printQuery(preparedStatement);
            preparedStatement.executeQuery();

            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                Client client = Client.builder()
                        .id(resultSet.getInt("id"))
                        .firstName(resultSet.getString("first_name"))
                        .secondName(resultSet.getString("second_name"))
                        .age(resultSet.getInt("age"))
                        .build();

                clients.add(client);
            }
            return clients;
        }catch (SQLException e){
            throw new DaoLayerException(e.getMessage(),e.getCause());
        }
    }

    public static ClientDaoImpl getInstance(){
        return INSTANCE;
    }
}
