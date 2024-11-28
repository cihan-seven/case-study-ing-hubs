package com.ing.casestudy.controller;

import com.ing.casestudy.config.SecurityConfig;
import com.ing.casestudy.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
@Import(SecurityConfig.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    @WithMockUser(username = "customer", password = "customer", roles = "CUSTOMER")
    public void shouldAccessWithMockUser() throws Exception {
        this.mockMvc.perform(get("/customer/find-all")).andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    public void shouldNotAccessWithAnonymousUser() throws Exception {
        this.mockMvc.perform(get("/customer/find-all")).andExpect(status().is4xxClientError());
    }

}
