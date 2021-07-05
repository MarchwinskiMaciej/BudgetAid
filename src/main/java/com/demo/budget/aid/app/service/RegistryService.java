package com.demo.budget.aid.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.budget.aid.app.model.Registry;
import com.demo.budget.aid.app.repository.RegistryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistryService {

    private final RegistryRepository repository;

    @Autowired
    public RegistryService(RegistryRepository repository) {
        this.repository = repository;
    }

    public Registry getRegistryById(long id) {
        Optional<Registry> result = repository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new IllegalArgumentException("Registry with id : " + id + "could not be found.");
        }
    }


    public Registry recharge(String registryName, Double amount) {
        Registry currentRegistry = repository.findByRegistryName(registryName);
        Double newAmount = currentRegistry.getBalance() + amount;
        currentRegistry.setBalance(newAmount);
        return repository.save(currentRegistry);
    }

    public List<Registry> transfer(String fromRegistryName, String toRegistryName, Double amount) {
        // Here could be also added logic for saving transactions, we could take current date, we have both registries and an amount
        // It would required additional Entity like Transaction which could be now created and pushed into db
        Registry fromRegistry = repository.findByRegistryName(fromRegistryName);
        Registry toRegistry = repository.findByRegistryName(toRegistryName);

        // For those operations with amount some additional checks would be required,
        // for example ensuring that fromRegistry has enough resources
        fromRegistry.setBalance(fromRegistry.getBalance() - amount);
        toRegistry.setBalance(toRegistry.getBalance() + amount);
        repository.save(fromRegistry);
        repository.save(toRegistry);
        return List.of(fromRegistry, toRegistry);
    }

    public List<Registry> balances() {
        List<Registry> result = new ArrayList<>();
        repository.findAll().forEach(result::add);
        return result;
    }
}
