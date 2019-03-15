package com.ecommerce.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection="Inventory")
public class Inventory {
	
	@Id
	private String productId;
	private String productName;
	private int productCost;
	private int availableProductCount;

}


