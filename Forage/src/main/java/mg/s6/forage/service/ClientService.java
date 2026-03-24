package mg.s6.forage.service;

import mg.s6.forage.entity.Client;
import mg.s6.forage.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public Client save(Client client) {
        if (client.getDateCreation() == null) {
            client.setDateCreation(LocalDateTime.now());
        }
        return clientRepository.save(client);
    }

    public Client update(Long id, Client clientDetails) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            Client client = clientOpt.get();
            client.setNom(clientDetails.getNom());
            client.setContact(clientDetails.getContact());
            return clientRepository.save(client);
        }
        return null;
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}