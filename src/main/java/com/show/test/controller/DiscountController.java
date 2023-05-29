package com.show.test.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.show.test.model.Bill;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api")
@Api(tags = "Discount API")
public class DiscountController {
	
	 @PostMapping("/calculate")
	 @ApiOperation("Calculate net payable amount")
	    public ResponseEntity<Double> calculateNetPayableAmount(@RequestBody Bill bill) {
	        double totalAmount = bill.getAmount();
	        double netPayableAmount = totalAmount;
	        if (!bill.isGrocery()) {
	        // Apply discounts based on user information
	        if (bill.getUser().isEmployee()) {
	            netPayableAmount = applyPercentageDiscount(netPayableAmount, 30);
	        } else if (bill.getUser().isAffiliate()) {
	            netPayableAmount = applyPercentageDiscount(netPayableAmount, 10);
	        } else if (bill.getUser().isCustomerOverTwoYears()) {
	            netPayableAmount = applyPercentageDiscount(netPayableAmount, 5);
	        }
	        }
	        // Apply discount for every $100 on the bill
	        int discountAmount = (int) (totalAmount / 100) * 5;
	        netPayableAmount -= discountAmount;

	        return new ResponseEntity<>(netPayableAmount, HttpStatus.OK);
	    }

	    private double applyPercentageDiscount(double amount, int percentage) {
	        return amount - (amount * percentage / 100);
	    }
	

}
