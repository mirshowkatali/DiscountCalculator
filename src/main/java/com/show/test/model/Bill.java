package com.show.test.model;

import lombok.Data;

@Data
public class Bill {

	private double amount;
    private User user;
    private boolean isGrocery;
}
