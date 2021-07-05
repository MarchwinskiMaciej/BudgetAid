package com.demo.budget.aid.app.service;

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
        return repository.findById(id).get();
    }

    public Registry saveRegistry(String registryName, double balance){
        Registry tmp = new Registry();
        tmp.setRegistryName(registryName);
        tmp.setBalance(balance);
        return repository.save(tmp);
    }
}
