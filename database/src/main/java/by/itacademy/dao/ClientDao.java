package by.itacademy.dao;

import by.itacademy.enteties.Client;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public interface ClientDao {

    Logger LOGGER = Logger.getLogger(ClientDao.class);

    Integer createClient(final Client client);

    Optional<Client> getClientById(Integer id);

    boolean deleteClient(Integer id);

    boolean updateClient(Client client);

    List<Client> getAllClients();
}
