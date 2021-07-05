package com.demo.budget.aid.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@EnableConfigurationProperties
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class BudgetAidApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Order(1)
    public void postRegistryTransferTest() throws Exception {
        mockMvc.perform(post("/registry/transfer?fromRegistryName=Wallet&toRegistryName=Savings&amount=50.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"balance\":950.0,\"registryName\":\"Wallet\"},{\"id\":2,\"balance\":5050.0,\"registryName\":\"Savings\"}]"));
    }

    @Test
    @Order(2)
    public void postRegistryRechargeTest() throws Exception {
        mockMvc.perform(post("/registry/recharge?registryName=Wallet&amount=1234"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"balance\":2184.0,\"registryName\":\"Wallet\"}"));
    }

    @Test
    @Order(3)
    public void getBalancesTest() throws Exception {
        mockMvc.perform(get("/registry/balances"))
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":1,\"balance\":2184.0,\"registryName\":\"Wallet\"},{\"id\":2,\"balance\":5050.0,\"registryName\":\"Savings\"},{\"id\":3,\"balance\":0.0,\"registryName\":\"Insurance policy\"},{\"id\":4,\"balance\":0.0,\"registryName\":\"Food expenses\"}]"));
    }

    @Test
    @Order(4)
    public void getRegistryByIdTest() throws Exception {
        mockMvc.perform(get("/registry/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"balance\":2184.0,\"registryName\":\"Wallet\"}"));
    }

}
