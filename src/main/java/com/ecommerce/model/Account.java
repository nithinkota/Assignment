package com.ecommerce.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection="Account")
public class Account {
	
	
	private String accountantName;
	
	//created index on this variable
	private String accountantEmail;
	
	

}

