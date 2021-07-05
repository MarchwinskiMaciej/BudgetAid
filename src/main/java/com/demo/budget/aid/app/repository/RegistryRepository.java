package com.demo.budget.aid.app.repository;

import java.util.Optional;

import com.demo.budget.aid.app.model.Registry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryRepository extends CrudRepository<Registry, Long> {
    Optional<Registry> findById(Long id);
}
