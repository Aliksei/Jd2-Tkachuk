package by.itacademy.services;

import by.itacademy.dao.ClientDaoImpl;
import by.itacademy.enteties.Client;
import by.itacademy.exceptions.ServiceLayerException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientServiceImpl implements IClientService {

    private static final ClientServiceImpl INSTANCE = new ClientServiceImpl();

    private final ClientDaoImpl clientDao = ClientDaoImpl.getInstance();

    @Override
    public Integer createClient(final String firstName, final String secondName, final Integer age){
        if (firstName.isEmpty() || secondName.isEmpty() || age <= 0)
            throw new ServiceLayerException("Invalid parameters for creating new client");

        Client client = Client.builder()
                .firstName(firstName)
                .secondName(secondName)
                .age(age)
                .build();
        return clientDao.createClient(client);
    }

    @Override
    public Optional<Client> getClientById(final Integer id){
        if (id <= 0)
            throw new ServiceLayerException("Please enter valid id");
        return clientDao.getClientById(id);
    }

    @Override
    public boolean deleteClient(final Integer id){
        if (id <= 0)
            throw new ServiceLayerException("Please enter valid id");
        return clientDao.deleteClient(id);
    }

    @Override
    public boolean updateClient(final Integer id, final String firstName, final String secondName, final Integer age){
        if (firstName.isEmpty() || secondName.isEmpty() || age <= 0)
            throw new ServiceLayerException("Invalid parameters for creating new client");

        Client client = Client.builder()
                .id(id)
                .firstName(firstName)
                .secondName(secondName)
                .age(age)
                .build();
        return clientDao.updateClient(client);
    }

    @Override
    public List<Client> getAllClients(){
        return clientDao.getAllClients();
    }

    public static ClientServiceImpl getInstance(){
        return INSTANCE;
    }
}
