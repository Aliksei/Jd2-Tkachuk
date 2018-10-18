package by.itacademy.services;

import by.itacademy.enteties.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    Integer createClient(String firstName, String secondName, Integer age);

    Optional<Client> getClientById(Integer id);

    boolean deleteClient(Integer id);

    boolean updateClient(Integer id, String firstName, String secondName, Integer age);

    List<Client> getAllClients();
}
