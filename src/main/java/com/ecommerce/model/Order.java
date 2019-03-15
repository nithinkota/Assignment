package com.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@Document(collection="Order")
public class Order {
	
	@Id
	private String orderId;
	private String orderDate;
	private String orderDeliveryAddress;
	private double orderCost;
    private String accountantName;
	private String accountantEmail;
	private String contactNumber;
	private String productId;
	private String responseMessage;
	private int productQuantity;
	private String paymentType;
	@JsonInclude(value=Include.NON_NULL)
	private String paymentStatus;
	private String orderStatus;
	

}

