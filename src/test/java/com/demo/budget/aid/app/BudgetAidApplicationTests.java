package com.demo.budget.aid.app;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
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
    public void getRegistryByIdTest() throws Exception {
        mockMvc.perform(get("/registry/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"balance\":1000.0,\"registryName\":\"Wallet\"}"));
    }

}
