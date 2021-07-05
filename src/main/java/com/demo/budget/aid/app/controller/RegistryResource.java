package com.demo.budget.aid.app.controller;

import java.util.List;

import com.demo.budget.aid.app.model.Registry;
import com.demo.budget.aid.app.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegistryResource {

    private final RegistryService registryService;

    @Autowired
    public RegistryResource(RegistryService registryService) {
        this.registryService = registryService;
    }

    @GetMapping("/registry/{id}")
    public ResponseEntity<Registry> getRegistryById(@PathVariable long id) {
        return ResponseEntity.ok(registryService.getRegistryById(id));
    }

    @PutMapping("/registry/recharge")
    public ResponseEntity<Registry> recharge(@RequestParam String registryName, @RequestParam Double amount) {
        return ResponseEntity.ok(registryService.recharge(registryName, amount));
    }

    @PostMapping("registry/transfer")
    public ResponseEntity<List<Registry>> transfer(@RequestParam String fromRegistryName, @RequestParam String toRegistryName, @RequestParam Double amount) {
        return ResponseEntity.ok(registryService.transfer(fromRegistryName, toRegistryName, amount));
    }

    @GetMapping("registry/balances")
    public ResponseEntity<List<Registry>> balances() {
        return ResponseEntity.ok(registryService.balances());
    }
}
