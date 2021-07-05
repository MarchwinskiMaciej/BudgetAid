package com.demo.budget.aid.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Registry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Double balance;
    private String registryName;
}
