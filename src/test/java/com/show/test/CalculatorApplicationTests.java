package com.show.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.show.test.controller.DiscountController;
import com.show.test.model.Bill;
import com.show.test.model.User;


@SpringBootTest
@AutoConfigureMockMvc

class CalculatorApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private DiscountController discountController;
    
    @Test
    void testCalculateNetPayableAmount() throws Exception {
        Bill bill = new Bill();
        bill.setAmount(990);
        User user = new User();
        user.setEmployee(true);
        bill.setUser(user);

        Mockito.when(discountController.calculateNetPayableAmount(Mockito.any(Bill.class)))
                .thenReturn(ResponseEntity.ok(945.0));

        mockMvc.perform(post("/api/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"amount\": 990, \"user\": { \"employee\": true } }"))
                .andExpect(status().isOk())
                .andExpect(content().json("945.0"));
    
    
    }

}
