package com.demo.budget.aid.app.service;

import java.util.ArrayList;
import java.util.List;

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

    public Registry recharge(String registryName, Double amount) {
        Registry currentRegistry = repository.findByRegistryName(registryName);
        Double newAmount = currentRegistry.getBalance() + amount;
        currentRegistry.setBalance(newAmount);
        return repository.save(currentRegistry);
    }

    public List<Registry> transfer(String fromRegistryName, String toRegistryName, Double amount) {
        Registry fromRegistry  = repository.findByRegistryName(fromRegistryName);
        Registry toRegistry = repository.findByRegistryName(toRegistryName);
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
